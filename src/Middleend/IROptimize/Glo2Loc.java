package Middleend.IROptimize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import IR.IRModule;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.AllocaInst;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.StoreInst;
import Middleend.IROptimize.Tools.CallGraphAnalyzer;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class Glo2Loc implements IRModulePass, IRFnPass {
    /*
     * for every function, every global var in it
     * localize it
     * if a call inst will use it, then store it before the call inst
     * if a call inst will def it, then load it after the call inst
     * 
     * always localize at the head and write back at the tail
     * do not consider the modification before call inst and usage after call inst
     */

    @Override
    public void runOnIRModule(IRModule irModule) {
        new CallGraphAnalyzer().runOnIRModule(irModule);
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
    }

    HashMap<CallInst, HashSet<GlobalVariable>> storeBeforeCall = new HashMap<>();
    HashMap<CallInst, HashSet<GlobalVariable>> loadAfterCall = new HashMap<>();
    HashMap<GlobalVariable, AllocaInst> glo2locMap = new HashMap<>();

    private void init() {
        storeBeforeCall.clear();
        loadAfterCall.clear();
        glo2locMap.clear();
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        init();
        for (var callInst : fn.callInfo.callInst) {
            var needStore = new HashSet<>(callInst.callee.callInfo.gloUses);
            var needLoad = new HashSet<>(callInst.callee.callInfo.gloDefs);
            needStore.retainAll(fn.callInfo.gloSet);
            needLoad.retainAll(fn.callInfo.gloSet);
            storeBeforeCall.put(callInst, needStore);
            loadAfterCall.put(callInst, needLoad);
        }

        // for debug
        // System.err.println("fn: " + fn.nameString);
        // fn.callInfo.callInst.forEach(call -> {
        // System.err.println("--------------------------------");
        // storeBeforeCall.get(call).forEach(glo -> System.err.println("store: " +
        // glo.nameString));
        // System.err.println(call.formatDef());
        // loadAfterCall.get(call).forEach(glo -> System.err.println("load: " +
        // glo.nameString));
        // System.err.println("--------------------------------\n");
        // });

        // create alloca for every global var in this function
        for (var glo : fn.callInfo.gloSet) {
            var alloca = new AllocaInst(glo.derefType, fn);
            glo2locMap.put(glo, alloca);
        }

        // replace all global var with alloca
        var blockList = new ArrayList<>(fn.blockList);
        blockList.add(fn.retBlock);
        for (var block : blockList) {
            for (var inst : block.instList) {
                if (inst instanceof LoadInst && glo2locMap.containsKey(inst.getOprand(0))) {
                    inst.setOprand(0, glo2locMap.get(inst.getOprand(0)));
                } else if (inst instanceof StoreInst && glo2locMap.containsKey(inst.getOprand(1))) {
                    inst.setOprand(1, glo2locMap.get(inst.getOprand(1)));
                }
            }
        }

        // load all global var into its alloca
        for (var glo : fn.callInfo.gloSet) {
            var alloca = glo2locMap.get(glo);
            var block = fn.blockList.get(0);
            var idx = fn.blockList.get(0).instList.indexOf(alloca);
            var tmp = new LoadInst(glo, block, idx + 1);
            new StoreInst(tmp, alloca, block, idx + 2);
        }

        // write back
        if (!fn.nameString.equals("main")) {
            for (var glo : fn.callInfo.gloSet) {
                if (!fn.callInfo.gloDefs.contains(glo))
                    continue;
                var alloca = glo2locMap.get(glo);
                var block = fn.retBlock;
                var idx = fn.retBlock.instList.size() - 1;
                var tmp = new LoadInst(alloca, block, idx);
                new StoreInst(tmp, glo, block, idx + 1);
            }
        }

        while (!storeBeforeCall.isEmpty()) {
            var it = storeBeforeCall.keySet().iterator();
            var callInst = it.next();
            var storeSet = storeBeforeCall.get(callInst);
            it.remove();
            var block = callInst.parentBlock;
            var idx = block.instList.indexOf(callInst);
            storeSet.forEach(glo -> {
                var alloca = glo2locMap.get(glo);
                var tmp = new LoadInst(alloca, block, idx);
                new StoreInst(tmp, glo, block, idx + 1);
            });
        }

        while (!loadAfterCall.isEmpty()) {
            var it = loadAfterCall.keySet().iterator();
            var callInst = it.next();
            var loadSet = loadAfterCall.get(callInst);
            it.remove();
            var block = callInst.parentBlock;
            var idx = block.instList.indexOf(callInst);
            loadSet.forEach(glo -> {
                var alloca = glo2locMap.get(glo);
                var tmp = new LoadInst(glo, block, idx + 1);
                new StoreInst(tmp, alloca, block, idx + 2);
            });
        }

    }
}
