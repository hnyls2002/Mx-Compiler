package Backend;

import ASM.ASMBlock;
import ASM.ASMFn;
import ASM.ASMModule;
import ASM.ASMInst.ASMCalcInst;
import ASM.ASMInst.ASMCallInst;
import ASM.ASMInst.ASMLaInst;
import ASM.ASMInst.ASMLiInst;
import ASM.ASMInst.ASMLoadInst;
import ASM.ASMInst.ASMMoveInst;
import ASM.ASMInst.ASMRetInst;
import ASM.ASMInst.ASMStoreInst;
import ASM.ASMInst.ASMCalcInst.ASMBIOP;
import ASM.ASMInst.ASMCalcInst.ASMBOP;
import ASM.ASMInst.ASMCalcInst.ASMBZOP;
import ASM.ASMOprand.Immediate;
import ASM.ASMOprand.PhysicalReg;
import ASM.ASMOprand.RegOffset;
import ASM.ASMOprand.Register;
import ASM.ASMOprand.StackOffset;
import ASM.ASMOprand.VirtualReg;
import ASM.ASMOprand.ASMGlobal.ASMConstStr;
import ASM.ASMOprand.ASMGlobal.ASMGlobalData;
import ASM.ASMOprand.ASMGlobal.ASMGlobalVar;
import ASM.ASMOprand.PhysicalReg.ABIType;
import IR.IRModule;
import IR.IRType.IRArrayType;
import IR.IRType.IRPtType;
import IR.IRType.IRStructType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.AllocaInst;
import IR.IRValue.IRUser.IRInst.BinaryInst;
import IR.IRValue.IRUser.IRInst.BrInst;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.CastInst;
import IR.IRValue.IRUser.IRInst.GEPInst;
import IR.IRValue.IRUser.IRInst.IcmpInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.PhiInst;
import IR.IRValue.IRUser.IRInst.RetInst;
import IR.IRValue.IRUser.IRInst.StoreInst;
import Share.MyException;
import Share.Lang.RV32;
import Share.Pass.IRBlockPass;
import Share.Pass.IRFnPass;
import Share.Pass.IRModulePass;
import Share.Visitors.IRInstVisitor;

public class ASMBuiler implements IRModulePass, IRFnPass, IRBlockPass, IRInstVisitor {

    public IRModule irModule;
    public ASMModule asmModule;
    public ASMCurrent cur = new ASMCurrent();

    public ASMModule buildAsm(IRModule irModule) {
        asmModule = new ASMModule();
        runOnIRModule(irModule);
        return asmModule;
    }

    @Override
    public void runOnIRModule(IRModule irModule) {
        // TODO Auto-generated method stub
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

    @Override
    public void runOnIRFn(IRFn irfn) {
        // TODO Auto-generated method stub
        ASMFn asmFn = new ASMFn(irfn);
        asmModule.fnList.add(asmFn);
        cur.fn = asmFn;

        System.err.println(irfn.getName());

        for (int i = 0; i < irfn.blockList.size(); ++i) {
            cur.block = new ASMBlock(i);
            cur.fn.blockList.add(cur.block);
            runOnBlock(irfn.blockList.get(i));
        }

        cur.block = new ASMBlock(irfn.blockList.size());
        cur.fn.blockList.add(cur.block);
        runOnBlock(irfn.retBlock);
    }

    @Override
    public void runOnBlock(IRBasicBlock block) {
        // TODO Auto-generated method stub

        block.instList.forEach(inst -> inst.accept(this));
    }

    @Override
    public void visit(AllocaInst inst) {
        // TODO Auto-generated method stub
        inst.asOprand = new StackOffset(cur.fn.allocaCnt++);
    }

    @Override
    public void visit(BinaryInst inst) {
        // TODO Auto-generated method stub

        ASMBOP op = switch (inst.opCode) {
            case irADD -> ASMBOP.add;
            case irSUB -> ASMBOP.sub;
            case irMUL -> ASMBOP.mul;
            case irSDIV -> ASMBOP.div;
            case irSREM -> ASMBOP.rem;
            case irSHL -> ASMBOP.sll;
            case irASHR -> ASMBOP.sra;
            case irOR -> ASMBOP.or;
            case irAND -> ASMBOP.and;
            case irXOR -> ASMBOP.xor;
        };
        ifConstThenLoad(inst.lhs);
        ifConstThenLoad(inst.rhs);
        inst.asOprand = new VirtualReg();
        Register rd = (Register) inst.asOprand, rs1 = (Register) inst.lhs.asOprand, rs2 = (Register) inst.rhs.asOprand;
        new ASMCalcInst(op, rd, rs1, rs2, cur.block);
    }

    @Override
    public void visit(BrInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(CallInst inst) {
        // TODO Auto-generated method stub

        // all imm should be in register to pass arguments
        inst.argList.forEach(this::ifConstThenLoad);

        // a0 - a7
        for (int i = 0; i < Math.min(inst.argList.size(), RV32.MAX_ARG_NUM); ++i) {
            var rd = new PhysicalReg(ABIType.arg, i);
            var rs = (Register) inst.argList.get(i).asOprand;
            new ASMMoveInst(rd, rs, cur.block);
        }

        // on stack
        for (int i = RV32.MAX_ARG_NUM; i < inst.argList.size(); ++i) {
            var addr = new StackOffset(cur.fn.spilledArgCnt++);
            var rs = (Register) inst.argList.get(i).asOprand;
            new ASMStoreInst(addr, rs, RV32.BitWidth.W, cur.block);
        }

        new ASMCallInst(inst.calledFnType.fnNameString, cur.block);
    }

    @Override
    public void visit(CastInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(GEPInst inst) {
        if (inst.startType instanceof IRArrayType) {
        } else if (inst.startType instanceof IRPtType) {
            System.err.println("array index");
        } else if (inst.startType instanceof IRStructType) {
            System.err.println("struct member");
        } else
            throw new MyException("gep on unknow type");
    }

    @Override
    public void visit(IcmpInst inst) {

        ifConstThenLoad(inst.lhs);
        ifConstThenLoad(inst.rhs);
        Register rs1 = (Register) inst.lhs.asOprand;
        Register rs2 = (Register) inst.rhs.asOprand;
        inst.asOprand = new VirtualReg();
        Register rd = (Register) inst.asOprand;

        switch (inst.opCode) {
            case irSLT -> new ASMCalcInst(ASMBOP.slt, rd, rs1, rs2, cur.block);
            case irSGT -> new ASMCalcInst(ASMBOP.slt, rd, rs2, rs1, cur.block);
            case irSLE -> {
                new ASMCalcInst(ASMBOP.slt, rd, rs2, rs1, cur.block);
                new ASMCalcInst(ASMBIOP.xori, rd, rd, new Immediate(1), cur.block);
            }
            case irSGE -> {
                new ASMCalcInst(ASMBOP.slt, rd, rs1, rs2, cur.block);
                new ASMCalcInst(ASMBIOP.xori, rd, rd, new Immediate(1), cur.block);
            }
            case irEQ -> {
                new ASMCalcInst(ASMBOP.xor, rd, rs1, rs2, cur.block);
                new ASMCalcInst(ASMBZOP.seqz, rd, rd, cur.block);
            }
            case irNE -> {
                new ASMCalcInst(ASMBOP.xor, rd, rs1, rs2, cur.block);
                new ASMCalcInst(ASMBZOP.snez, rd, rd, cur.block);
            }
        }
        ;
    }

    @Override
    public void visit(LoadInst inst) {
        RegOffset addr = (RegOffset) inst.srcAddr.asOprand;
        Register rd = new VirtualReg();
        inst.asOprand = rd;
        new ASMLoadInst(addr, rd, RV32.BitWidth.W, cur.block);
    }

    @Override
    public void visit(RetInst inst) {
        new ASMRetInst(cur.block);
    }

    @Override
    public void visit(PhiInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(StoreInst inst) {
        // TODO Auto-generated method stub

    }

    private void ifConstThenLoad(IRBaseValue val) {
        // when val could be intConst, null, GlobalVar...
        if (val.asOprand == null) {
            if (val instanceof IntConst t) {
                Register rd = new VirtualReg();
                new ASMLiInst(rd, t.constValue, cur.block);
                val.asOprand = rd;
            } else if (val instanceof NullConst) {
                Register rd = new VirtualReg();
                new ASMLiInst(rd, 0, cur.block);
                val.asOprand = rd;
            } else if (val instanceof GlobalVariable t) {
                Register rd = new VirtualReg();
                new ASMLaInst(rd, (ASMGlobalData) t.asOprand, cur.block);
            } else
                throw new MyException("Unknow value (no oprand)");
        }
    }

}
