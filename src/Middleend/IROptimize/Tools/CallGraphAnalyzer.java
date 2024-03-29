package Middleend.IROptimize.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import IR.IRModule;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.StoreInst;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class CallGraphAnalyzer implements IRModulePass, IRFnPass {
    public static class CallInfo {
        // caller and callee : direct edge
        // defs and uses : recursive until find a fixed point
        public IRFn parentFn;
        public HashSet<IRFn> callees = new HashSet<>(), callers = new HashSet<>();
        public HashSet<GlobalVariable> gloSet = new HashSet<>();
        public HashSet<GlobalVariable> gloDefs = new HashSet<>(), gloUses = new HashSet<>();
        public HashSet<CallInst> callInst = new HashSet<>();

        static final List<String> outSideEffectFn = new ArrayList<String>(
                Arrays.asList("print", "println", "printInt", "printlnInt"));
        static public List<String> variantFn = List.of("__malloc", "print", "println", "getString", "getInt",
                "printInt", "printlnInt");

        // store , output
        // : for ADCE
        private boolean outSideEffectFlag = false;

        // load, store, input, output, malloc
        // : for loop variant code motion
        private boolean mayVariantFlag = false;

        private boolean willModifyMemory = false;

        public boolean hasOutSideEffect() {
            return outSideEffectFlag || outSideEffectFn.contains(parentFn.nameString);
        }

        public boolean mayVariant() {
            return mayVariantFlag || variantFn.contains(parentFn.nameString);
        }

        public boolean willModifyMemory() {
            return willModifyMemory;
        }

        public CallInfo(IRFn fn) {
            this.parentFn = fn;
        }
    }

    public IRModule irModule;

    @Override
    public void runOnIRModule(IRModule irModule) {
        this.irModule = irModule;
        irModule.globalFnList.forEach(fn -> fn.callInfo = new CallInfo(fn));
        irModule.varInitFnList.forEach(fn -> fn.callInfo = new CallInfo(fn));
        // builtin function should have callInfo
        irModule.builtinFnList.forEach(fn -> fn.callInfo = new CallInfo(fn));

        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);

        Queue<IRFn> workList = new LinkedList<>();
        workList.addAll(irModule.globalFnList);
        workList.addAll(irModule.varInitFnList);
        workList.addAll(irModule.builtinFnList);

        // find the fixed point
        while (!workList.isEmpty()) {
            var fn = workList.poll();
            for (var caller : fn.callInfo.callers) {
                int size0 = caller.callInfo.gloDefs.size();
                int size1 = caller.callInfo.gloUses.size();
                caller.callInfo.gloDefs.addAll(fn.callInfo.gloDefs);
                caller.callInfo.gloUses.addAll(fn.callInfo.gloUses);
                if (size0 != caller.callInfo.gloDefs.size() || size1 != caller.callInfo.gloUses.size())
                    if (!workList.contains(caller))
                        workList.offer(caller);
                if (fn.callInfo.hasOutSideEffect() && !caller.callInfo.hasOutSideEffect()) {
                    caller.callInfo.outSideEffectFlag = true;
                    if (!workList.contains(caller))
                        workList.offer(caller);
                }
                if (fn.callInfo.mayVariant() && !caller.callInfo.mayVariant()) {
                    caller.callInfo.mayVariantFlag = true;
                    if (!workList.contains(caller))
                        workList.offer(caller);
                }
                if (fn.callInfo.willModifyMemory() && !caller.callInfo.willModifyMemory()) {
                    caller.callInfo.willModifyMemory = true;
                    if (!workList.contains(caller))
                        workList.offer(caller);
                }
            }
        }
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        var blockList = new ArrayList<>(fn.blockList);
        blockList.add(fn.retBlock);
        for (var block : blockList) {
            for (var inst : block.instList) {
                if (inst instanceof CallInst call) {
                    fn.callInfo.callInst.add(call);
                    fn.callInfo.callees.add(call.callee);
                    call.callee.callInfo.callers.add(fn);
                } else if (inst instanceof LoadInst load && load.getOprand(0) instanceof GlobalVariable glo) {
                    fn.callInfo.gloUses.add(glo);
                    fn.callInfo.gloSet.add(glo);
                } else if (inst instanceof StoreInst store && store.getOprand(1) instanceof GlobalVariable glo) {
                    fn.callInfo.gloDefs.add(glo);
                    fn.callInfo.gloSet.add(glo);
                }

                if (inst instanceof StoreInst) {
                    fn.callInfo.outSideEffectFlag = true;
                    fn.callInfo.willModifyMemory = true;
                }
                if (inst instanceof StoreInst || inst instanceof LoadInst)
                    fn.callInfo.mayVariantFlag = true;
            }
        }
    }
}
