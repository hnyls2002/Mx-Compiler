package Backend.RegAllocate;

import ASM.ASMBlock;
import ASM.ASMFn;
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
import Share.Pass.ASMPass.ASMBlockPass;
import Share.Pass.ASMPass.ASMFnPass;
import Share.Pass.ASMPass.ASMModulePass;
import Share.Visitors.ASMInstVisitor;

public class BfRegAllocator implements ASMModulePass, ASMFnPass, ASMBlockPass, ASMInstVisitor {

    @Override
    public void runOnASMModule(ASMModule asmModule) {
        // TODO Auto-generated method stub

    }

    @Override
    public void runOnASMFn(ASMFn asmFn) {
        // TODO Auto-generated method stub

    }

    @Override
    public void runOnASMBlock(ASMBlock asmBlock) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ASMBrInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ASMCalcInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ASMCallInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ASMJInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ASMLaInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ASMLiInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ASMLoadInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ASMStoreInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ASMMoveInst inst) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ASMRetInst inst) {
        // TODO Auto-generated method stub

    }

}
