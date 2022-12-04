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
import ASM.ASMOprand.BaseOprand;
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
        block.instList.forEach(inst -> inst.accept(this));
    }

    @Override
    public void visit(AllocaInst inst) {
        inst.asOprand = new StackOffset(cur.fn.allocaCnt++);
    }

    @Override
    public void visit(BinaryInst inst) {
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

        // get ret value
        if (!(inst.calledFnType.retType instanceof IRVoidType)) {
            PhysicalReg rs = new PhysicalReg(ABIType.arg, 0);
            Register rd = new VirtualReg();
            new ASMMoveInst(rd, rs, cur.block);
            inst.asOprand = rd;
        }
    }

    @Override
    public void visit(CastInst inst) {
        inst.asOprand = inst.srcValue.asOprand;
    }

    @Override
    public void visit(GEPInst inst) {
        Register startAddr = ifGlobalThenLoad(inst.startPtr.asOprand);
        inst.indices.forEach(this::ifConstThenLoad);
        if (inst.startType instanceof IRArrayType) {
            inst.asOprand = startAddr;
        } else if (inst.startType instanceof IRPtType) {
            Register rd = new VirtualReg();
            Register rs2 = (Register) inst.indices.get(0).asOprand;
            inst.asOprand = rd;
            new ASMCalcInst(ASMBOP.add, rd, startAddr, rs2, cur.block);
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
        System.err.println(inst.defToString());
        ifConstThenLoad(inst.storedValue);
        RegOffset addr = getAddrOffset(inst.destAddr.asOprand);
        Register rs = (Register) inst.storedValue.asOprand;
        new ASMStoreInst(addr, rs, RV32.BitWidth.W, cur.block);
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

    // When IRPtType, just set the register, no offset
    private RegOffset getAddrOffset(BaseOprand oprand) {
        if (oprand instanceof RegOffset t)
            return t;
        else if (oprand instanceof Register t)
            return new RegOffset(t, new Immediate(0));
        else if (oprand instanceof ASMGlobalData) {
            Register rd = new VirtualReg();
            new ASMLaInst(rd, (ASMGlobalData) oprand, cur.block);
            return new RegOffset(rd, new Immediate(0));
        } else
            throw new MyException("address should be RegOffset or GlobalVar");
    }

    // when val could be intConst, null
    private void ifConstThenLoad(IRBaseValue val) {
        if (val.asOprand == null) {
            if (val instanceof IntConst t) {
                Register rd = new VirtualReg();
                new ASMLiInst(rd, t.constValue, cur.block);
                val.asOprand = rd;
            } else if (val instanceof NullConst) {
                Register rd = new VirtualReg();
                new ASMLiInst(rd, 0, cur.block);
                val.asOprand = rd;
            } else
                throw new MyException("Unknow value (no oprand)");
        }
    }

}
