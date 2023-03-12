package Middleend.IROptimize;

import IR.IRModule;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class PhiElimination implements IRModulePass, IRFnPass {

    @Override
    public void runOnIRModule(IRModule irModule) {
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        splitCriticalEdge(fn);
    }

    public void splitCriticalEdge(IRFn fn) {
        for (var block : fn.blockList) {
        }
    }
}
