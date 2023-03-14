package Middleend;

import java.util.HashSet;

import IR.IRModule;
import IR.IRType.IRType.IRTypeId;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import IR.IRValue.IRUser.IRInst.MoveInst;
import Share.Pass.IRPass.IRBlockPass;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class IRRenamer implements IRModulePass, IRFnPass, IRBlockPass {
    private HashSet<IRBaseValue> renamed = new HashSet<>();

    private class Allocator {
        public int cnt = 0;

        public void rename(IRBaseValue value) {
            if (!renamed.contains(value)) {
                value.setName(String.valueOf(cnt++));
                renamed.add(value);
            }
        }
    }

    Allocator curAllocator;

    @Override
    public void runOnIRModule(IRModule irModule) {
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        curAllocator = new Allocator();
        fn.paraList.forEach(curAllocator::rename);
        fn.blockList.forEach(this::runOnIRBlock);
        runOnIRBlock(fn.retBlock);
    }

    @Override
    public void runOnIRBlock(IRBasicBlock block) {
        curAllocator.rename(block);
        block.phiList.forEach(this::renameInst);
        block.instList.forEach(this::renameInst);
    }

    private void renameInst(IRBaseInst inst) {
        if (inst.valueType.typeId == IRTypeId.VoidTypeId)
            return;
        if (inst instanceof MoveInst mvInst)
            curAllocator.rename(mvInst.getOprand(0));
        else
            curAllocator.rename(inst);
    }

}
