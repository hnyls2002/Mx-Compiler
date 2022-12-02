package Middleend;

import IR.IRModule;
import IR.IRType.IRType.IRTypeId;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.Inst.IRBaseInst;

public class IRRenamer {
    IRModule topModule;

    private class Allocator {
        public int cnt = 0;

        public String getNewName() {
            return String.valueOf(cnt++);
        }
    }

    private Allocator allocator;

    public IRRenamer(IRModule topModule) {
        this.topModule = topModule;
    }

    public void renameIR() {
        topModule.globalFnList.forEach(fn -> renameFn(fn));
        topModule.varInitFnList.forEach(fn -> renameFn(fn));
    }

    public void renameFn(IRFn fn) {
        allocator = new Allocator();
        fn.paraList.forEach(para -> para.parameterName = allocator.getNewName());
        fn.blockList.forEach(block -> renameBB(block));
        renameBB(fn.retBlock);
    }

    public void renameBB(IRBasicBlock block) {
        if (block.entryString == null)
            block.entryString = allocator.getNewName();
        block.instList.forEach(inst -> renameInst(inst));
    }

    public void renameInst(IRBaseInst inst) {
        if (inst.valueType.typeId != IRTypeId.VoidTypeId)
            inst.reName = allocator.getNewName();
    }
}
