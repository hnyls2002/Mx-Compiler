package Backend;

import ASM.ASMModule;
import ASM.ASMGlobal.ASMConstStr;
import IR.IRModule;
import IR.IRValue.IRBasicBlock;
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
import Share.Pass.IRBlockPass;
import Share.Pass.IRFnPass;
import Share.Pass.IRModulePass;
import Share.Visitors.IRInstVisitor;

public class ASMBuiler implements IRModulePass, IRFnPass, IRBlockPass, IRInstVisitor {

    public IRModule irModule;
    public ASMModule asmModule;

    public ASMModule buildAsm(IRModule irModule) {
        asmModule = new ASMModule();
        runOnIRModule(irModule);
        return asmModule;
    }

    @Override
    public void runOnIRModule(IRModule irModule) {
        // TODO Auto-generated method stub
        irModule.constStrList.forEach(constStr -> asmModule.constStrList.add(new ASMConstStr(constStr)));
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        // TODO Auto-generated method stub

    }

    @Override
    public void runOnBlock(IRBasicBlock block) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(AllocaInst inst) {
        // TODO Auto-generated method stub

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
}
