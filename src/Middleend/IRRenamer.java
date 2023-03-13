package Middleend;

import java.util.HashMap;

import IR.IRModule;
import IR.IRType.IRType.IRTypeId;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import IR.IRValue.IRUser.IRInst.MoveInst;
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

    HashMap<IRFn, Allocator> fnAllocatorMap = new HashMap<>();
    Allocator curAllocator;

    @Override
    public void runOnIRModule(IRModule irModule) {
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        if (!fnAllocatorMap.containsKey(fn))
            fnAllocatorMap.put(fn, new Allocator());
        curAllocator = fnAllocatorMap.get(fn);
        fn.paraList.forEach(para -> para.setName(curAllocator.getNewName()));
        fn.blockList.forEach(this::runOnIRBlock);
        runOnIRBlock(fn.retBlock);
    }

    @Override
    public void runOnIRBlock(IRBasicBlock block) {
        if (block.nameString == null)
            block.setName(curAllocator.getNewName());
        block.phiList.forEach(this::renameInst);
        block.instList.forEach(this::renameInst);
    }

    private void renameInst(IRBaseInst inst) {
        if (inst.valueType.typeId == IRTypeId.VoidTypeId)
            return;
        if (inst instanceof MoveInst mvInst) {
            if (mvInst.getOprand(0).nameString == null)
                mvInst.getOprand(0).setName(curAllocator.getNewName());
            return;
        }

        if (inst.nameString == null)
            inst.setName(curAllocator.getNewName());
    }

}
