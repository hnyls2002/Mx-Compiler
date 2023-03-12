package Backend;

import java.util.ArrayList;
import java.util.Collections;

import org.antlr.v4.runtime.misc.Pair;

import ASM.ASMBlock;
import ASM.ASMFn;
import ASM.ASMModule;
import ASM.ASMInst.ASMBaseInst;
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
import ASM.ASMOprand.ASMGlobal.ASMConstStr;
import ASM.ASMOprand.ASMGlobal.ASMGlobalData;
import ASM.ASMOprand.ASMGlobal.ASMGlobalVar;
import IR.IRModule;
import IR.IRType.IRArrayType;
import IR.IRType.IRFnType;
import IR.IRType.IRIntType;
import IR.IRType.IRStructType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.AllocaInst;
import IR.IRValue.IRUser.IRInst.BinaryInst;
import IR.IRValue.IRUser.IRInst.BrInst;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.CastInst;
import IR.IRValue.IRUser.IRInst.GEPInst;
import IR.IRValue.IRUser.IRInst.IcmpInst;
import IR.IRValue.IRUser.IRInst.JumpInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.PhiInst;
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

    public IRModule irModule;
    public ASMModule asmModule;
    public ASMCurrent cur = new ASMCurrent();
    public int fnCnt = 0;

    public ASMModule buildAsm(IRModule irModule) {
        asmModule = new ASMModule();
        runOnIRModule(irModule);
        return asmModule;
    }

    @Override
    public void runOnIRModule(IRModule irModule) {
        irModule.constStrList.forEach(constStr -> {
            ASMConstStr t = new ASMConstStr(constStr);
            asmModule.constStrList.add(t);
            constStr.asOprand = t;
        });
        irModule.globalVarList.forEach(gVar -> {
            ASMGlobalVar t = new ASMGlobalVar(gVar);
            asmModule.globalVarList.add(t);
            gVar.asOprand = t;
        });
        irModule.varInitFnList.forEach(this::runOnIRFn);
        irModule.globalFnList.forEach(this::runOnIRFn);
    }

    // all phi node's register should be preload
    private void phiPreload(IRBasicBlock irblock) {
        irblock.instList.forEach(inst -> {
            if (inst instanceof PhiInst t)
                t.asOprand = new VirtualReg();
        });
    }

    @Override
    public void runOnIRFn(IRFn irfn) {
        ASMFn asmFn = new ASMFn(irfn);
        asmModule.fnList.add(asmFn);
        cur.fn = asmFn;

        // block preload : set oprand for every IRBasicBlock
        for (int i = 0; i < irfn.blockList.size(); ++i) {
            var irBlock = irfn.blockList.get(i);
            irBlock.asOprand = new ASMBlock(i, fnCnt, irBlock.loopDepth);
        }
        irfn.retBlock.asOprand = new ASMBlock(irfn.blockList.size(), fnCnt, 0);
        ++fnCnt;

        // reset the virtual register counter
        VirtualReg.virtualRegCnt = 0;

        // first block, lower sp, store ra
        cur.block = (ASMBlock) irfn.blockList.get(0).asOprand;
        PhysicalReg sp = PhysicalReg.getPhyReg("sp");
        PhysicalReg ra = PhysicalReg.getPhyReg("ra");

        // imm unhandled until stack frame is built
        new ASMCalcInst(BinaryImOp.addi, sp, sp, null, new Immediate(0), cur.block);
        new ASMStoreInst(sp, ra, new VirtualOffset(SPLabel.ra, 0), BitWidth.w, cur.block);

        // first block, handle the parameters
        for (int i = 0; i < Math.min(irfn.paraList.size(), RV32.MAX_ARG_NUM); ++i) {
            Register rd = new VirtualReg();
            new ASMMoveInst(rd, PhysicalReg.getPhyReg("a" + i), cur.block);
            irfn.paraList.get(i).asOprand = rd;
        }
        for (int i = RV32.MAX_ARG_NUM; i < irfn.paraList.size(); ++i) {
            VirtualOffset vOffset = new VirtualOffset(SPLabel.getSpilledArg, i - RV32.MAX_ARG_NUM);
            Register rd = new VirtualReg();
            irfn.paraList.get(i).asOprand = rd;
            new ASMLoadInst(rd, sp, vOffset, BitWidth.w, cur.block);
        }

        // save callee-save registers
        // HashMap<Register, Register> backupReg = new HashMap<>();
        // for (var toSave : PhysicalReg.calleeSavedReg) {
        // var savePos = new VirtualReg();
        // backupReg.put(toSave, savePos);
        // new ASMMoveInst(savePos, toSave, cur.block);
        // }

        // phi preload
        irfn.blockList.forEach(this::phiPreload);
        phiPreload(irfn.retBlock);

        // runOnBlock
        irfn.blockList.forEach(this::runOnIRBlock);
        runOnIRBlock(irfn.retBlock);

        // ret block, load ra, upper sp
        int siz = cur.block.instList.size();
        cur.block.instList.remove(siz - 1);
        if (!(((IRFnType) irfn.valueType).retType instanceof IRVoidType)) {
            ASMLoadInst ld = (ASMLoadInst) cur.block.instList.get(siz - 2);
            new ASMMoveInst(PhysicalReg.getPhyReg("a0"), ld.rd, cur.block);
        }

        // backup callee-save registers
        // for (var toSave : PhysicalReg.calleeSavedReg) {
        // var savePos = backupReg.get(toSave);
        // new ASMMoveInst(toSave, savePos, cur.block);
        // }

        new ASMLoadInst(ra, sp, new VirtualOffset(SPLabel.ra, 0), BitWidth.w, cur.block);
        new ASMCalcInst(BinaryImOp.addi, sp, sp, null, new Immediate(0), cur.block);
        new ASMRetInst(cur.block);
    }

    @Override
    public void runOnIRBlock(IRBasicBlock irBlock) {
        cur.block = (ASMBlock) irBlock.asOprand;
        cur.fn.blockList.add(cur.block);

        irBlock.instList.forEach(inst -> inst.accept(this));
        irBlock.getTerminal().accept(this);
    }

    @Override
    public void visit(AllocaInst inst) {
        inst.asOprand = new VirtualOffset(SPLabel.alloca, cur.fn.allocaCnt++);
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
        inst.asOprand = new VirtualReg();
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
        inst.oprandList.forEach(arg -> ifConstThenLoad(arg, cur.block));

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
            var sp = PhysicalReg.getPhyReg("sp");
            new ASMStoreInst(sp, value, vOffset, BitWidth.w, cur.block);
        }
        cur.fn.spilledArgMax = Math.max(cur.fn.spilledArgMax, Math.max(inst.getOprandNum() - RV32.MAX_ARG_NUM, 0));

        new ASMCallInst(inst.calledFnType.fnNameString, inst.getOprandNum(), cur.block);

        // get ret value
        if (!(inst.calledFnType.retType instanceof IRVoidType)) {
            PhysicalReg rs = PhysicalReg.getPhyReg("a0");
            Register rd = new VirtualReg();
            new ASMMoveInst(rd, rs, cur.block);
            inst.asOprand = rd;
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
            inst.asOprand = startAddr;
        } else if (inst.startType instanceof IRStructType) { // struct
            Register rd = new VirtualReg(), rs2 = new VirtualReg();
            Immediate imm = new Immediate(((IntConst) inst.getIdx(1)).constValue * 4);
            new ASMLiInst(rs2, imm, cur.block);
            new ASMCalcInst(BinaryRegOp.add, rd, startAddr, rs2, null, cur.block);
            inst.asOprand = rd;
        } else { // array
            Register rd = new VirtualReg(), rs2 = new VirtualReg();
            ifConstThenLoad(inst.getIdx(0), cur.block);
            Register id = (Register) inst.getIdx(0).asOprand;
            // ----------------- add twice = mul * 4 --------------------
            new ASMCalcInst(BinaryRegOp.add, rs2, id, id, null, cur.block);
            new ASMCalcInst(BinaryRegOp.add, rs2, rs2, rs2, null, cur.block);
            // ----------------- add twice = mul * 4 --------------------
            new ASMCalcInst(BinaryRegOp.add, rd, startAddr, rs2, null, cur.block);
            inst.asOprand = rd;
        }
    }

    @Override
    public void visit(IcmpInst inst) {

        ifConstThenLoad(inst.getOprand(0), cur.block);
        ifConstThenLoad(inst.getOprand(1), cur.block);
        Register rs1 = (Register) inst.getOprand(0).asOprand;
        Register rs2 = (Register) inst.getOprand(1).asOprand;
        inst.asOprand = new VirtualReg();
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
        Register rd = new VirtualReg();
        inst.asOprand = rd;
        new ASMLoadInst(rd, addr.a, addr.b, BitWidth.w, cur.block);
    }

    @Override
    public void visit(RetInst inst) {
        new ASMRetInst(cur.block);
    }

    private boolean judgeBJ(ASMBaseInst inst) {
        return inst instanceof ASMJInst || inst instanceof ASMBrInst;
    }

    private ASMBaseInst popBJ(ArrayList<ASMBaseInst> list) {
        if (list.isEmpty())
            return null;
        ASMBaseInst last = list.get(list.size() - 1);
        if (judgeBJ(last)) {
            list.remove(list.size() - 1);
            return last;
        }
        return null;
    }

    @Override
    public void visit(PhiInst inst) {
        VirtualOffset phiResult = new VirtualOffset(SPLabel.phi, cur.fn.phiStackCnt++);

        ASMBlock block1 = (ASMBlock) inst.block1.asOprand;
        ASMBlock block2 = (ASMBlock) inst.block2.asOprand;
        ArrayList<ASMBaseInst> t1 = new ArrayList<>();
        ArrayList<ASMBaseInst> t2 = new ArrayList<>();
        while (true) {
            ASMBaseInst i1 = popBJ(block1.instList);
            if (i1 == null)
                break;
            t1.add(i1);
        }
        while (true) {
            ASMBaseInst i2 = popBJ(block2.instList);
            if (i2 == null)
                break;
            t2.add(i2);
        }

        ifConstThenLoad(inst.res1, block1);
        ifConstThenLoad(inst.res2, block2);
        Register res1 = (Register) inst.res1.asOprand;
        Register res2 = (Register) inst.res2.asOprand;

        var sp = PhysicalReg.getPhyReg("sp");

        new ASMStoreInst(sp, res1, phiResult, BitWidth.w, block1);
        new ASMStoreInst(sp, res2, phiResult, BitWidth.w, block2);

        Collections.reverse(t1);
        Collections.reverse(t2);
        block1.instList.addAll(t1);
        block2.instList.addAll(t2);

        Register res = (Register) inst.asOprand;
        new ASMLoadInst(res, sp, phiResult, BitWidth.w, cur.block);
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
            Register rd = new VirtualReg();
            new ASMLaInst(rd, (ASMGlobalData) oprand, cur.block);
            return rd;
        } else if (oprand instanceof Register t)
            return t;
        else
            throw new MyException("if global then load wrong");
    }

    // when val could be intConst, null
    private void ifConstThenLoad(IRBaseValue val, ASMBlock loadBlock) {
        if (val.asOprand == null) {
            if (val instanceof IntConst t) {
                Register rd = new VirtualReg();
                new ASMLiInst(rd, new Immediate(t.constValue), loadBlock);
                val.asOprand = rd;
            } else if (val instanceof NullConst) {
                Register rd = new VirtualReg();
                new ASMLiInst(rd, new Immediate(0), loadBlock);
                val.asOprand = rd;
            } else
                throw new MyException("Unknow value (no oprand)");
        }
    }

    private Pair<Register, Immediate> toAddr(BaseOprand oprand) {
        if (oprand instanceof Register rs)
            return new Pair<>(rs, new Immediate(0));
        else if (oprand instanceof ASMGlobalData g) {
            Register rs = new VirtualReg();
            new ASMLaInst(rs, g, cur.block);
            return new Pair<>(rs, new Immediate(0));
        } else if (oprand instanceof VirtualOffset vOffset) {
            var sp = PhysicalReg.getPhyReg("sp");
            return new Pair<>(sp, vOffset);
        } else
            throw new MyException("trans to address error");
    }

}
