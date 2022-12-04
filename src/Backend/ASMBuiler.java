package Backend;

import java.util.ArrayList;
import java.util.Collections;

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
import ASM.ASMOprand.StackOffset.stackDataKind;
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
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.PhiInst;
import IR.IRValue.IRUser.IRInst.RetInst;
import IR.IRValue.IRUser.IRInst.StoreInst;
import Share.MyException;
import Share.Lang.RV32;
import Share.Lang.RV32.BitWidth;
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

    @Override
    public void runOnIRFn(IRFn irfn) {
        ASMFn asmFn = new ASMFn(irfn);
        asmModule.fnList.add(asmFn);
        cur.fn = asmFn;

        System.err.println(irfn.getName());

        // block preload : set oprand for every IRBasicBlock
        for (int i = 0; i < irfn.blockList.size(); ++i)
            irfn.blockList.get(i).asOprand = new ASMBlock(i, fnCnt);
        irfn.retBlock.asOprand = new ASMBlock(irfn.blockList.size(), fnCnt);
        ++fnCnt;

        // reset the virtual register counter
        VirtualReg.virtualRegCnt = 0;

        // first block, lower sp, store ra
        cur.block = (ASMBlock) irfn.blockList.get(0).asOprand;
        PhysicalReg sp = new PhysicalReg(ABIType.sp, 0);
        PhysicalReg ra = new PhysicalReg(ABIType.ra, 0);
        new ASMCalcInst(ASMBIOP.addi, sp, sp, new Immediate(0), cur.block);
        new ASMStoreInst(new StackOffset(0, stackDataKind.ra), ra, BitWidth.w, cur.block);

        // first block, handle the parameters
        for (int i = 0; i < Math.min(irfn.paraList.size(), RV32.MAX_ARG_NUM); ++i) {
            Register rd = new VirtualReg();
            new ASMMoveInst(rd, new PhysicalReg(ABIType.arg, i), cur.block);
            irfn.paraList.get(i).asOprand = rd;
        }
        for (int i = RV32.MAX_ARG_NUM; i < irfn.paraList.size(); ++i) {
            StackOffset stackPos = new StackOffset(i - RV32.MAX_ARG_NUM, stackDataKind.getArg);
            Register rd = new VirtualReg();
            irfn.paraList.get(i).asOprand = rd;
            new ASMLoadInst(stackPos, rd, RV32.BitWidth.w, cur.block);
        }

        // runOnBlock
        irfn.blockList.forEach(this::runOnIRBlock);
        runOnIRBlock(irfn.retBlock);

        // ret block, load ra, upper sp
        int siz = cur.block.instList.size();
        cur.block.instList.remove(siz - 1);
        if (!(((IRFnType) irfn.valueType).retType instanceof IRVoidType)) {
            ASMLoadInst ld = (ASMLoadInst) cur.block.instList.get(siz - 2);
            new ASMMoveInst(new PhysicalReg(ABIType.arg, 0), ld.rd, cur.block);
        }

        new ASMLoadInst(new StackOffset(0, stackDataKind.ra), ra, BitWidth.w, cur.block);
        new ASMCalcInst(ASMBIOP.addi, sp, sp, new Immediate(0), cur.block);
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
        inst.asOprand = new StackOffset(cur.fn.allocaCnt++, stackDataKind.alloca);
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
        ifConstThenLoad(inst.lhs, cur.block);
        ifConstThenLoad(inst.rhs, cur.block);
        inst.asOprand = new VirtualReg();
        Register rd = (Register) inst.asOprand, rs1 = (Register) inst.lhs.asOprand, rs2 = (Register) inst.rhs.asOprand;
        new ASMCalcInst(op, rd, rs1, rs2, cur.block);
    }

    @Override
    public void visit(BrInst inst) {
        if (inst.falseBlock == null)
            new ASMJInst((ASMBlock) inst.trueBlock.asOprand, cur.block);
        else {
            assert inst.condition.valueType instanceof IRIntType t && t.intLen == 1;
            ifConstThenLoad(inst.condition, cur.block);
            new ASMBrInst((Register) inst.condition.asOprand, (ASMBlock) inst.trueBlock.asOprand, cur.block);
            new ASMJInst((ASMBlock) inst.falseBlock.asOprand, cur.block);
        }
    }

    @Override
    public void visit(CallInst inst) {
        // all imm should be in register to pass arguments
        inst.argList.forEach(arg -> ifConstThenLoad(arg, cur.block));

        // a0 - a7
        for (int i = 0; i < Math.min(inst.argList.size(), RV32.MAX_ARG_NUM); ++i) {
            var rd = new PhysicalReg(ABIType.arg, i);
            var rs = (Register) inst.argList.get(i).asOprand;
            new ASMMoveInst(rd, rs, cur.block);
        }

        // on stack
        for (int i = RV32.MAX_ARG_NUM; i < inst.argList.size(); ++i) {
            var addr = new StackOffset(i - RV32.MAX_ARG_NUM, stackDataKind.putArg);
            var rs = (Register) inst.argList.get(i).asOprand;
            new ASMStoreInst(addr, rs, RV32.BitWidth.w, cur.block);
        }
        cur.fn.spilledArgMax = Math.max(cur.fn.spilledArgMax, Math.max(inst.argList.size() - RV32.MAX_ARG_NUM, 0));

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
        ifConstThenLoad(inst.srcValue, cur.block);
        inst.asOprand = inst.srcValue.asOprand;
    }

    @Override
    public void visit(GEPInst inst) {
        Register startAddr = ifGlobalThenLoad(inst.startPtr.asOprand);
        if (inst.startType instanceof IRArrayType) { // string
            inst.asOprand = startAddr;
        } else if (inst.startType instanceof IRStructType) { // struct
            Register rd = new VirtualReg(), rs2 = new VirtualReg();
            new ASMLiInst(rs2, ((IntConst) inst.indices.get(1)).constValue * 4, cur.block);
            new ASMCalcInst(ASMBOP.add, rd, startAddr, rs2, cur.block);
            inst.asOprand = rd;
        } else { // array
            Register rd = new VirtualReg(), rs2 = new VirtualReg();
            ifConstThenLoad(inst.indices.get(0), cur.block);
            Register id = (Register) inst.indices.get(0).asOprand;
            // ----------------- add twice = mul * 4 --------------------
            new ASMCalcInst(ASMBOP.add, rs2, id, id, cur.block);
            new ASMCalcInst(ASMBOP.add, rs2, rs2, rs2, cur.block);
            // ----------------- add twice = mul * 4 --------------------
            new ASMCalcInst(ASMBOP.add, rd, startAddr, rs2, cur.block);
            inst.asOprand = rd;
        }
    }

    @Override
    public void visit(IcmpInst inst) {

        ifConstThenLoad(inst.lhs, cur.block);
        ifConstThenLoad(inst.rhs, cur.block);
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
        RegOffset addr = getAddrOffset(inst.srcAddr.asOprand);
        Register rd = new VirtualReg();
        inst.asOprand = rd;
        new ASMLoadInst(addr, rd, RV32.BitWidth.w, cur.block);
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
        StackOffset phiResult = new StackOffset(cur.fn.phiStackCnt++, stackDataKind.phi);

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

        new ASMStoreInst(phiResult, res1, RV32.BitWidth.w, block1);
        new ASMStoreInst(phiResult, res2, RV32.BitWidth.w, block2);

        Collections.reverse(t1);
        Collections.reverse(t2);
        block1.instList.addAll(t1);
        block2.instList.addAll(t2);

        Register res = new VirtualReg();
        new ASMLoadInst(phiResult, res, RV32.BitWidth.w, cur.block);

        inst.asOprand = res;
    }

    @Override
    public void visit(StoreInst inst) {
        ifConstThenLoad(inst.storedValue, cur.block);
        RegOffset addr = getAddrOffset(inst.destAddr.asOprand);
        Register rs = (Register) inst.storedValue.asOprand;
        new ASMStoreInst(addr, rs, RV32.BitWidth.w, cur.block);
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
    private void ifConstThenLoad(IRBaseValue val, ASMBlock loadBlock) {
        if (val.asOprand == null) {
            if (val instanceof IntConst t) {
                Register rd = new VirtualReg();
                new ASMLiInst(rd, t.constValue, loadBlock);
                val.asOprand = rd;
            } else if (val instanceof NullConst) {
                Register rd = new VirtualReg();
                new ASMLiInst(rd, 0, loadBlock);
                val.asOprand = rd;
            } else
                throw new MyException("Unknow value (no oprand)");
        }
    }

}
