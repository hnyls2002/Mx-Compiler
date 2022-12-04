package Middleend;

import IR.IRModule;
import IR.IRType.IRType.IRTypeId;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import Share.Pass.IRPass.IRBlockPass;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class IRRenamer implements IRModulePass, IRFnPass, IRBlockPass {
    private class Allocator {
        public int cnt = 0;

        public String getNewName() {
            return String.valueOf(cnt++);
        }
    }

    private Allocator allocator;

    @Override
    public void runOnIRModule(IRModule irModule) {
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        allocator = new Allocator();
        fn.paraList.forEach(para -> para.setName(allocator.getNewName()));
        fn.blockList.forEach(this::runOnIRBlock);
        runOnIRBlock(fn.retBlock);
    }

    @Override
    public void runOnIRBlock(IRBasicBlock block) {
        if (!block.isNamed())
            block.setName(allocator.getNewName());
        block.instList.forEach(this::renameInst);
    }

    private void renameInst(IRBaseInst inst) {
        if (inst.valueType.typeId != IRTypeId.VoidTypeId)
            inst.setName(allocator.getNewName());
    }

}
