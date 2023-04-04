package Backend;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.misc.Pair;

import ASM.ASMBlock;
import ASM.ASMModule;
import ASM.ASMInst.ASMBrInst;
import ASM.ASMInst.ASMCalcInst;
import ASM.ASMInst.ASMCallInst;
import ASM.ASMInst.ASMJInst;
import ASM.ASMInst.ASMLaInst;
import ASM.ASMInst.ASMLiInst;
import ASM.ASMInst.ASMLoadInst;
import ASM.ASMInst.ASMMoveInst;
import ASM.ASMInst.ASMRetInst;
import ASM.ASMInst.ASMStoreInst;
import ASM.ASMOprand.BaseOprand;
import ASM.ASMOprand.Immediate;
import ASM.ASMOprand.PhysicalReg;
import ASM.ASMOprand.Register;
import ASM.ASMOprand.VirtualOffset;
import ASM.ASMOprand.VirtualReg;
import ASM.ASMOprand.ASMGlobal.ASMGlobalData;
import IR.IRModule;
import IR.IRType.IRArrayType;
import IR.IRType.IRFnType;
import IR.IRType.IRStructType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.BinaryInst;
import IR.IRValue.IRUser.IRInst.BrInst;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.CastInst;
import IR.IRValue.IRUser.IRInst.GEPInst;
import IR.IRValue.IRUser.IRInst.IcmpInst;
import IR.IRValue.IRUser.IRInst.JumpInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.MoveInst;
import IR.IRValue.IRUser.IRInst.PhiInst;
import IR.IRValue.IRUser.IRInst.RetInst;
import IR.IRValue.IRUser.IRInst.StoreInst;
import Share.MyException;
import Share.Lang.RV32;
import Share.Lang.LLVMIR.ICMPOP;
import Share.Lang.RV32.BinaryImOp;
import Share.Lang.RV32.BinaryRegOp;
import Share.Lang.RV32.BinaryZeroOp;
import Share.Lang.RV32.BitWidth;
import Share.Lang.RV32.BranchOp;
import Share.Lang.RV32.SPLabel;
import Share.Pass.IRPass.IRBlockPass;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;
import Share.Visitors.IRInstVisitor;

public class ASMBuiler implements IRModulePass, IRFnPass, IRBlockPass, IRInstVisitor {

    public ASMCurrent cur = new ASMCurrent();
    public int fnCnt = 0;

    static final PhysicalReg sp = PhysicalReg.getPhyReg("sp");
    static final PhysicalReg ra = PhysicalReg.getPhyReg("ra");
    static final PhysicalReg zero = PhysicalReg.getPhyReg("zero");

    public ASMModule buildAsm(IRModule irModule) {
        cur.module = irModule.asmModule;
        runOnIRModule(irModule);
        return cur.module;
    }

    @Override
    public void runOnIRModule(IRModule irModule) {
        irModule.varInitFnList.forEach(this::runOnIRFn);
        irModule.globalFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn irfn) {
        cur.fn = irfn.asmFn;

        // imm unhandled until stack frame is built
        cur.block = (ASMBlock) irfn.blockList.get(0).asOprand;
        new ASMCalcInst(BinaryImOp.addi, sp, sp, null, new Immediate(0), cur.block);
        new ASMStoreInst(sp, ra, new VirtualOffset(SPLabel.ra, 0), BitWidth.w, cur.block);

        // first block, handle the parameters
        for (int i = 0; i < Math.min(irfn.paraList.size(), RV32.MAX_ARG_NUM); ++i) {
            var rd = (VirtualReg) irfn.paraList.get(i).asOprand;
            new ASMMoveInst(rd, PhysicalReg.getPhyReg("a" + i), cur.block);
        }
        for (int i = RV32.MAX_ARG_NUM; i < irfn.paraList.size(); ++i) {
            var rd = (VirtualReg) irfn.paraList.get(i).asOprand;
            VirtualOffset vOffset = new VirtualOffset(SPLabel.getSpilledArg, i - RV32.MAX_ARG_NUM);
            new ASMLoadInst(rd, sp, vOffset, BitWidth.w, cur.block);
        }

        // save callee-save registers
        HashMap<Register, Register> backupReg = new HashMap<>();
        for (var toSave : PhysicalReg.calleeSavedReg) {
            var savePos = new VirtualReg(cur.fn);
            backupReg.put(toSave, savePos);
            new ASMMoveInst(savePos, toSave, cur.block);
        }

        // runOnBlock
        irfn.blockList.forEach(this::runOnIRBlock);
        runOnIRBlock(irfn.retBlock);

        // backup callee-save registers
        for (var toSave : PhysicalReg.calleeSavedReg) {
            var savePos = backupReg.get(toSave);
            new ASMMoveInst(toSave, savePos, cur.block);
        }

        // ret block, load ra, upper sp
        new ASMLoadInst(ra, sp, new VirtualOffset(SPLabel.ra, 0), BitWidth.w, cur.block);
        new ASMCalcInst(BinaryImOp.addi, sp, sp, null, new Immediate(0), cur.block);
        new ASMRetInst(cur.block);
    }

    @Override
    public void runOnIRBlock(IRBasicBlock irBlock) {
        cur.block = (ASMBlock) irBlock.asOprand;
        irBlock.instList.forEach(inst -> inst.accept(this));
    }

    @Override
    public void visit(BinaryInst inst) {
        // if the opcode is commutative, swap the const to the second oprand
        inst.swapOprandForConst();

        Register val1 = loadVal(inst.getOprand(0).asOprand);
        var val2 = lazyLoadVal(inst.getOprand(1).asOprand);
        Register rd = (Register) inst.asOprand;

        var op = switch (inst.opCode) {
            case add -> val2 instanceof Immediate ? BinaryImOp.addi : BinaryRegOp.add;
            case or -> val2 instanceof Immediate ? BinaryImOp.ori : BinaryRegOp.or;
            case and -> val2 instanceof Immediate ? BinaryImOp.andi : BinaryRegOp.and;
            case xor -> val2 instanceof Immediate ? BinaryImOp.xori : BinaryRegOp.xor;
            case shl -> val2 instanceof Immediate ? BinaryImOp.slli : BinaryRegOp.sll;
            case ashr -> val2 instanceof Immediate ? BinaryImOp.srai : BinaryRegOp.sra;
            case sub -> {
                if (val2 instanceof Immediate imm && RV32.inLowerImmRange(-imm.immInt)) {
                    val2 = new Immediate(-imm.immInt);
                    yield BinaryImOp.addi;
                } else {
                    val2 = loadVal(val2);
                    yield BinaryRegOp.sub;
                }
            }
            default -> {
                val2 = loadVal(val2);
                yield switch (inst.opCode) {
                    case mul -> BinaryRegOp.mul;
                    case sdiv -> BinaryRegOp.div;
                    case srem -> BinaryRegOp.rem;
                    default -> throw new MyException("Hello World!");
                };
            }
        };
        if (val2 instanceof Immediate)
            new ASMCalcInst(op, rd, val1, null, (Immediate) val2, cur.block);
        else
            new ASMCalcInst(op, rd, val1, (Register) val2, null, cur.block);
    }

    @Override
    public void visit(BrInst inst) {
        var block1 = (ASMBlock) inst.getOprand(1).asOprand;
        var block2 = (ASMBlock) inst.getOprand(2).asOprand;

        // if the condition is constant, then jump to the corresponding block
        if (inst.getOprand(0).asOprand instanceof Immediate imm) {
            new ASMJInst(imm.immInt == 0 ? block2 : block1, cur.block);
            return;
        }

        if (inst.getOprand(0) instanceof IcmpInst icmpInst) {
            // two immediate -> constant folding : may be useless after IR's optimization
            if (icmpInst.getOprand(0).asOprand instanceof Immediate imm1
                    && icmpInst.getOprand(1).asOprand instanceof Immediate imm2) {
                boolean res = switch (icmpInst.opCode) {
                    case eq -> imm1.immInt == imm2.immInt;
                    case ne -> imm1.immInt != imm2.immInt;
                    case sge -> imm1.immInt >= imm2.immInt;
                    case sgt -> imm1.immInt > imm2.immInt;
                    case sle -> imm1.immInt <= imm2.immInt;
                    case slt -> imm1.immInt < imm2.immInt;
                };
                new ASMJInst(res ? block1 : block2, cur.block);
                return;
            }
            var rs1 = (Register) loadVal(icmpInst.getOprand(0).asOprand);
            var rs2 = (Register) loadVal(icmpInst.getOprand(1).asOprand);
            switch (icmpInst.opCode) {
                case eq -> new ASMBrInst(BranchOp.beq, rs1, rs2, block1, cur.block);
                case ne -> new ASMBrInst(BranchOp.bne, rs1, rs2, block1, cur.block);
                case slt -> new ASMBrInst(BranchOp.blt, rs1, rs2, block1, cur.block);
                case sgt -> new ASMBrInst(BranchOp.bgt, rs1, rs2, block1, cur.block);
                case sle -> new ASMBrInst(BranchOp.ble, rs1, rs2, block1, cur.block);
                case sge -> new ASMBrInst(BranchOp.bge, rs1, rs2, block1, cur.block);
            }
        } else
            new ASMBrInst(BranchOp.bne, (Register) inst.getOprand(0).asOprand, zero, block1, cur.block);

        new ASMJInst((ASMBlock) inst.getOprand(2).asOprand, cur.block);
    }

    @Override
    public void visit(JumpInst inst) {
        new ASMJInst((ASMBlock) inst.getOprand(0).asOprand, cur.block);
    }

    @Override
    public void visit(CallInst inst) {
        // all imm should be in register to pass arguments
        ArrayList<Register> args = new ArrayList<>();
        for (int i = 0; i < inst.getOprandNum(); ++i)
            args.add(loadVal(inst.getOprand(i).asOprand));

        // a0 - a7
        for (int i = 0; i < Math.min(inst.getOprandNum(), RV32.MAX_ARG_NUM); ++i) {
            var rd = PhysicalReg.getPhyReg("a" + i);
            new ASMMoveInst(rd, args.get(i), cur.block);
        }

        // on stack
        for (int i = RV32.MAX_ARG_NUM; i < inst.getOprandNum(); ++i) {
            var vOffset = new VirtualOffset(SPLabel.putSpilledArg, i - RV32.MAX_ARG_NUM);
            new ASMStoreInst(sp, args.get(i), vOffset, BitWidth.w, cur.block);
        }
        cur.fn.spilledArgCnt = Math.max(cur.fn.spilledArgCnt, Math.max(inst.getOprandNum() - RV32.MAX_ARG_NUM, 0));

        new ASMCallInst(inst.callee.getName(), inst.getOprandNum(), cur.block);

        // get ret value
        var fnType = (IRFnType) inst.callee.valueType;
        if (!(fnType.retType instanceof IRVoidType)) {
            PhysicalReg rs = PhysicalReg.getPhyReg("a0");
            var rd = (Register) inst.asOprand;
            new ASMMoveInst(rd, rs, cur.block);
        }
    }

    @Override
    public void visit(GEPInst inst) {
        // Register startAddr = ifGlobalThenLoad(inst.getOprand(0).asOprand);
        Register startAddr = inst.getOprand(0) instanceof NullConst ? zero
                : ifGlobalThenLoad(inst.getOprand(0).asOprand);
        if (inst.startType instanceof IRArrayType) { // string
            new ASMMoveInst((Register) inst.asOprand, startAddr, cur.block);
        } else if (inst.startType instanceof IRStructType) { // struct
            Register rd = (Register) inst.asOprand, rs2 = new VirtualReg(cur.fn);
            Immediate imm = new Immediate(((IntConst) inst.getIdx(1)).constValue * 4);
            new ASMLiInst(rs2, imm, cur.block);
            new ASMCalcInst(BinaryRegOp.add, rd, startAddr, rs2, null, cur.block);
        } else { // array

            Register rd = (Register) inst.asOprand;
            if (inst.getIdx(0).asOprand instanceof Register idx) {
                var id = new VirtualReg(cur.fn);
                // id = (idx + idx) + (idx + idx) <==> id = idx * 4
                new ASMCalcInst(BinaryRegOp.add, id, idx, idx, null, cur.block);
                new ASMCalcInst(BinaryRegOp.add, id, id, id, null, cur.block);
                new ASMCalcInst(BinaryRegOp.add, rd, startAddr, id, null, cur.block);
            } else {
                int offset = ((IntConst) inst.getIdx(0)).constValue * 4;
                if (RV32.inLowerImmRange(offset))
                    new ASMCalcInst(BinaryImOp.addi, rd, startAddr, null, new Immediate(offset),
                            cur.block);
                else {
                    var id = new VirtualReg(cur.fn);
                    new ASMLiInst(id, new Immediate(offset), cur.block);
                    new ASMCalcInst(BinaryRegOp.add, rd, startAddr, id, null, cur.block);
                }
            }
        }
    }

    @Override
    public void visit(IcmpInst inst) {

        // just do cmp in branch inst
        if (inst.onlyInBranch)
            return;

        Register rd = (Register) inst.asOprand;
        var rs1 = loadVal(inst.getOprand(0).asOprand);
        var val2 = lazyLoadVal(inst.getOprand(1).asOprand);

        if (inst.opCode == ICMPOP.slt && val2 instanceof Immediate imm) {
            new ASMCalcInst(BinaryImOp.slti, rd, rs1, null, imm, cur.block);
            return;
        }

        var rs2 = loadVal(val2);

        // can do constant folding, but it's useless

        switch (inst.opCode) {
            case slt -> new ASMCalcInst(BinaryRegOp.slt, rd, rs1, rs2, null, cur.block);
            case sgt -> new ASMCalcInst(BinaryRegOp.slt, rd, rs2, rs1, null, cur.block);
            case sle -> {
                new ASMCalcInst(BinaryRegOp.slt, rd, rs2, rs1, null, cur.block);
                new ASMCalcInst(BinaryImOp.xori, rd, rd, null, new Immediate(1), cur.block);
            }
            case sge -> {
                new ASMCalcInst(BinaryRegOp.slt, rd, rs1, rs2, null, cur.block);
                new ASMCalcInst(BinaryImOp.xori, rd, rd, null, new Immediate(1), cur.block);
            }
            case eq -> {
                new ASMCalcInst(BinaryRegOp.xor, rd, rs1, rs2, null, cur.block);
                new ASMCalcInst(BinaryZeroOp.seqz, rd, rd, null, null, cur.block);
            }
            case ne -> {
                new ASMCalcInst(BinaryRegOp.xor, rd, rs1, rs2, null, cur.block);
                new ASMCalcInst(BinaryZeroOp.snez, rd, rd, null, null, cur.block);
            }
        }
    }

    @Override
    public void visit(LoadInst inst) {
        var addr = toAddr(inst.getOprand(0).asOprand);
        Register rd = (Register) inst.asOprand;
        new ASMLoadInst(rd, addr.a, addr.b, BitWidth.w, cur.block);
    }

    @Override
    public void visit(RetInst inst) {
        if (inst.getOprandNum() != 0) {
            var reg = loadVal(inst.getOprand(0).asOprand);
            new ASMMoveInst(PhysicalReg.getPhyReg("a0"), reg, cur.block);
        }
    }

    @Override
    public void visit(StoreInst inst) {
        Register rs = loadVal(inst.getOprand(0).asOprand);
        var addr = toAddr(inst.getOprand(1).asOprand);
        new ASMStoreInst(addr.a, rs, addr.b, BitWidth.w, cur.block);
    }

    // when the addr is a global, in IR we directly use it address
    private Register ifGlobalThenLoad(BaseOprand oprand) {
        if (oprand instanceof ASMGlobalData) {
            Register rd = new VirtualReg(cur.fn);
            new ASMLaInst(rd, (ASMGlobalData) oprand, cur.block);
            return rd;
        } else if (oprand instanceof Register t)
            return t;
        else
            throw new MyException("if global then load wrong");
    }

    // when in range of imm, return the imm, else load the imm to a register
    private BaseOprand lazyLoadVal(BaseOprand oprand) {
        if (oprand instanceof Register)
            return oprand;
        if (oprand instanceof Immediate imm) {
            if (imm.immInt == 0)
                return zero;
            if (RV32.inLowerImmRange(imm.immInt))
                return oprand;
            VirtualReg rd = new VirtualReg(cur.fn);
            new ASMLiInst(rd, imm, cur.block);
            return rd;
        } else
            throw new MyException("lazy load val error");

    }

    // direct load the const value to a register
    private Register loadVal(BaseOprand oprand) {
        if (oprand instanceof Register)
            return (Register) oprand;
        if (oprand instanceof Immediate imm) {
            if (imm.immInt == 0)
                return zero;
            VirtualReg ret = new VirtualReg(cur.fn);
            if (RV32.inLowerImmRange(imm.immInt))
                new ASMCalcInst(BinaryImOp.addi, ret, zero, null, imm, cur.block);
            else
                new ASMLiInst(ret, imm, cur.block);
            return ret;
        } else
            throw new MyException("load val error");
    }

    private Pair<Register, Immediate> toAddr(BaseOprand oprand) {
        if (oprand instanceof Register rs)
            return new Pair<>(rs, new Immediate(0));
        else if (oprand instanceof ASMGlobalData g) {
            Register rs = new VirtualReg(cur.fn);
            new ASMLaInst(rs, g, cur.block);
            return new Pair<>(rs, new Immediate(0));
        } else if (oprand instanceof VirtualOffset vOffset) {
            return new Pair<>(sp, vOffset);
        } else
            throw new MyException("trans to address error");
    }

    @Override
    public void visit(MoveInst inst) {
        Register des = (Register) inst.getOprand(0).asOprand;
        Register src = loadVal(inst.getOprand(1).asOprand);
        new ASMMoveInst(des, src, cur.block);
    }

    @Override
    public void visit(PhiInst inst) {
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(CastInst inst) {
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }
}
