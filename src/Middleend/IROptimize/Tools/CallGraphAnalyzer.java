package Middleend.IROptimize.Tools;

import java.util.ArrayList;
import java.util.HashSet;

import IR.IRModule;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class CallGraphAnalyzer implements IRModulePass, IRFnPass {
    public static class CallInfo {
        HashSet<IRFn> calleeList = new HashSet<>(), callerList = new HashSet<>();
        HashSet<IRBaseInst> gloDefs = new HashSet<>(), gloUses = new HashSet<>();
        HashSet<IRBaseInst> callInstList = new HashSet<>();

        public void clear() {
            calleeList.clear();
            calleeList.clear();
            gloDefs.clear();
            gloUses.clear();
        }
    }

    @Override
    public void runOnIRModule(IRModule irModule) {
        // irModule.globalFnList.forEach(fn -> fn.callInfo.clear());
        // irModule.varInitFnList.forEach(fn -> fn.callInfo.clear());
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        var blockList = new ArrayList<>(fn.blockList);
        blockList.add(fn.retBlock);
        for (var block : blockList) {
            for (var inst : block.instList) {
            }
        }
    }
}
