package Backend;

import ASM.ASMBlock;
import ASM.ASMFn;
import ASM.ASMModule;
import ASM.ASMGlobal.ASMConstStr;
import ASM.ASMGlobal.ASMGlobalVar;
import ASM.ASMInst.ASMCallInst;
import ASM.ASMInst.ASMLiInst;
import ASM.ASMInst.ASMMoveInst;
import ASM.ASMInst.ASMStoreInst;
import ASM.ASMOprand.PhysicalReg;
import ASM.ASMOprand.Register;
import ASM.ASMOprand.StackOffset;
import ASM.ASMOprand.VirtualReg;
import ASM.ASMOprand.PhysicalReg.ABIType;
import IR.IRModule;
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
        // TODO Auto-generated method stub
        irModule.constStrList.forEach(constStr -> asmModule.constStrList.add(new ASMConstStr(constStr)));
        irModule.globalVarList.forEach(gVar -> asmModule.globalVarList.add(new ASMGlobalVar(gVar)));
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
        inst.oprand = new StackOffset(cur.fn.allocaCnt++);
    }

    @Override
    public void visit(BinaryInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(BrInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(CallInst inst) {
        // TODO Auto-generated method stub

        // all imm should be in register to pass arguments
        inst.argList.forEach(this::loadLitrl);

        // a0 - a7
        for (int i = 0; i < Math.min(inst.argList.size(), RV32.MAX_ARG_NUM); ++i) {
            var rd = new PhysicalReg(ABIType.arg, i);
            var rs = (Register) inst.argList.get(i).oprand;
            new ASMMoveInst(rd, rs, cur.block);
        }

        // on stack
        for (int i = RV32.MAX_ARG_NUM; i < inst.argList.size(); ++i) {
            var addr = new StackOffset(cur.fn.spilledArgCnt++);
            var rs = (Register) inst.argList.get(i).oprand;
            new ASMStoreInst(addr, rs, RV32.BitWidth.W, cur.block);
        }

        new ASMCallInst(inst.getName(), cur.block);
    }

    @Override
    public void visit(CastInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(GEPInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(IcmpInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(LoadInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(RetInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(PhiInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(StoreInst inst) {
        // TODO Auto-generated method stub

    }

    private void loadLitrl(IRBaseValue val) {
        if (val.oprand == null) {
            if (val instanceof IntConst t) {
                Register rd = new VirtualReg();
                new ASMLiInst(rd, t.constValue, cur.block);
                val.oprand = rd;
            } else if (val instanceof NullConst) {
                Register rd = new VirtualReg();
                new ASMLiInst(rd, 0, cur.block);
                val.oprand = rd;
            } else
                throw new MyException("Unknow value (no oprand)");
        }
    }
}
