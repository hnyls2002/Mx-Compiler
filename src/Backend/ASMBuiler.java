package Backend;

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
import IR.IRType.IRIntType;
import IR.IRType.IRStructType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRBaseValue;
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
import IR.IRValue.IRUser.IRInst.RetInst;
import IR.IRValue.IRUser.IRInst.StoreInst;
import Share.MyException;
import Share.Lang.RV32;
import Share.Lang.RV32.ASMOp;
import Share.Lang.RV32.BinaryImOp;
import Share.Lang.RV32.BinaryRegOp;
import Share.Lang.RV32.BinaryZeroOp;
import Share.Lang.RV32.BitWidth;
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
        ASMOp op = switch (inst.opCode) {
            case add -> BinaryRegOp.add;
            case sub -> BinaryRegOp.sub;
            case mul -> BinaryRegOp.mul;
            case sdiv -> BinaryRegOp.div;
            case srem -> BinaryRegOp.rem;
            case shl -> BinaryRegOp.sll;
            case ashr -> BinaryRegOp.sra;
            case or -> BinaryRegOp.or;
            case and -> BinaryRegOp.and;
            case xor -> BinaryRegOp.xor;
        };
        ifConstThenLoad(inst.getOprand(0), cur.block);
        ifConstThenLoad(inst.getOprand(1), cur.block);
        Register rd = (Register) inst.asOprand, rs1 = (Register) inst.getOprand(0).asOprand,
                rs2 = (Register) inst.getOprand(1).asOprand;
        new ASMCalcInst(op, rd, rs1, rs2, null, cur.block);
    }

    @Override
    public void visit(BrInst inst) {
        assert inst.getOprand(0).valueType instanceof IRIntType t && t.intLen == 1;
        ifConstThenLoad(inst.getOprand(0), cur.block);
        new ASMBrInst((Register) inst.getOprand(0).asOprand, (ASMBlock) inst.getOprand(1).asOprand, cur.block);
        new ASMJInst((ASMBlock) inst.getOprand(2).asOprand, cur.block);
    }

    @Override
    public void visit(JumpInst inst) {
        new ASMJInst((ASMBlock) inst.getOprand(0).asOprand, cur.block);
    }

    @Override
    public void visit(CallInst inst) {
        // all imm should be in register to pass arguments
        for (int i = 0; i < inst.getOprandNum(); ++i) {
            var arg = inst.getOprand(i);
            ifConstThenLoad(arg, cur.block);
        }

        // a0 - a7
        for (int i = 0; i < Math.min(inst.getOprandNum(), RV32.MAX_ARG_NUM); ++i) {
            var rd = PhysicalReg.getPhyReg("a" + i);
            var rs = (Register) inst.getOprand(i).asOprand;
            new ASMMoveInst(rd, rs, cur.block);
        }

        // on stack
        for (int i = RV32.MAX_ARG_NUM; i < inst.getOprandNum(); ++i) {
            var vOffset = new VirtualOffset(SPLabel.putSpilledArg, i - RV32.MAX_ARG_NUM);
            var value = (Register) inst.getOprand(i).asOprand;
            new ASMStoreInst(sp, value, vOffset, BitWidth.w, cur.block);
        }
        cur.fn.spilledArgCnt = Math.max(cur.fn.spilledArgCnt, Math.max(inst.getOprandNum() - RV32.MAX_ARG_NUM, 0));

        new ASMCallInst(inst.calledFnType.fnNameString, inst.getOprandNum(), cur.block);

        // get ret value
        if (!(inst.calledFnType.retType instanceof IRVoidType)) {
            PhysicalReg rs = PhysicalReg.getPhyReg("a0");
            var rd = (Register) inst.asOprand;
            new ASMMoveInst(rd, rs, cur.block);
        }
    }

    @Override
    public void visit(CastInst inst) {
        ifConstThenLoad(inst.getOprand(0), cur.block);
        inst.asOprand = inst.getOprand(0).asOprand;
    }

    @Override
    public void visit(GEPInst inst) {
        Register startAddr = ifGlobalThenLoad(inst.getOprand(0).asOprand);
        if (inst.startType instanceof IRArrayType) { // string
            new ASMMoveInst((Register) inst.asOprand, startAddr, cur.block);
        } else if (inst.startType instanceof IRStructType) { // struct
            Register rd = (Register) inst.asOprand, rs2 = new VirtualReg(cur.fn);
            Immediate imm = new Immediate(((IntConst) inst.getIdx(1)).constValue * 4);
            new ASMLiInst(rs2, imm, cur.block);
            new ASMCalcInst(BinaryRegOp.add, rd, startAddr, rs2, null, cur.block);
        } else { // array
            Register rd = (Register) inst.asOprand, rs2 = new VirtualReg(cur.fn);
            ifConstThenLoad(inst.getIdx(0), cur.block);
            Register id = (Register) inst.getIdx(0).asOprand;
            // ----------------- add twice = mul * 4 --------------------
            new ASMCalcInst(BinaryRegOp.add, rs2, id, id, null, cur.block);
            new ASMCalcInst(BinaryRegOp.add, rs2, rs2, rs2, null, cur.block);
            // ----------------- add twice = mul * 4 --------------------
            new ASMCalcInst(BinaryRegOp.add, rd, startAddr, rs2, null, cur.block);
        }
    }

    @Override
    public void visit(IcmpInst inst) {

        ifConstThenLoad(inst.getOprand(0), cur.block);
        ifConstThenLoad(inst.getOprand(1), cur.block);
        Register rs1 = (Register) inst.getOprand(0).asOprand;
        Register rs2 = (Register) inst.getOprand(1).asOprand;
        Register rd = (Register) inst.asOprand;

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
        ;
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
            ifConstThenLoad(inst.getOprand(0), cur.block);
            new ASMMoveInst(PhysicalReg.getPhyReg("a0"), (Register) inst.getOprand(0).asOprand, cur.block);
        }
    }

    @Override
    public void visit(StoreInst inst) {
        ifConstThenLoad(inst.getOprand(0), cur.block);
        var addr = toAddr(inst.getOprand(1).asOprand);
        Register rs = (Register) inst.getOprand(0).asOprand;
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

    // when val could be intConst, null
    private void ifConstThenLoad(IRBaseValue val, ASMBlock loadBlock) {
        // if (val.asOprand == null) {
        // if (val instanceof IntConst t) {
        // Register rd = new VirtualReg(cur.fn);
        // new ASMLiInst(rd, new Immediate(t.constValue), loadBlock);
        // val.asOprand = rd;
        // } else if (val instanceof NullConst) {
        // Register rd = new VirtualReg(cur.fn);
        // new ASMLiInst(rd, new Immediate(0), loadBlock);
        // val.asOprand = rd;
        // } else
        // throw new MyException("Unknow value (no oprand)");
        // }

        /*
         * When several const value are actually referring to the same location,
         * then the asOprand is the same, which means
         * at position 1, the constant value is loaded into a register,
         * at position 2, I guess the register containing the constant value,
         * however, position 2 may run before position 1.
         * 
         * remove the null check
         */

        if (val instanceof IntConst t) {
            Register rd = new VirtualReg(cur.fn);
            new ASMLiInst(rd, new Immediate(t.constValue), loadBlock);
            val.asOprand = rd;
        } else if (val instanceof NullConst) {
            Register rd = new VirtualReg(cur.fn);
            new ASMLiInst(rd, new Immediate(0), loadBlock);
            val.asOprand = rd;
        }
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
        ifConstThenLoad(inst.getOprand(0), cur.block);
        ifConstThenLoad(inst.getOprand(1), cur.block);
        Register des = (Register) inst.getOprand(0).asOprand;
        Register src = (Register) inst.getOprand(1).asOprand;
        new ASMMoveInst(des, src, cur.block);
    }

}
