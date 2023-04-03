package Middleend.IROptimize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import IR.IRModule;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.BrInst;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import IR.IRValue.IRUser.IRInst.JumpInst;
import IR.IRValue.IRUser.IRInst.PhiInst;
import IR.IRValue.IRUser.IRInst.RetInst;
import Middleend.IROptimize.Tools.InfosRebuilder;
import Share.Pass.IRPass.IRModulePass;

public class FnInlining implements IRModulePass {

    public static final int MAX_INST_NUM = 500;
    public static final int MAX_BLOCK_NUM = 300;

    public boolean inlineCyclic = false;
    public HashMap<IRFn, Integer> fnInstNum = new HashMap<>();

    public FnInlining(boolean inlineCyclic) {
        this.inlineCyclic = inlineCyclic;
    }

    private class PendingCall {
        CallInst inst;
        IRFn caller, callee;

        public PendingCall(CallInst inst, IRFn caller, IRFn callee) {
            this.inst = inst;
            this.caller = caller;
            this.callee = callee;
        }

        private boolean fnIsInlinable(IRFn fn) {
            return !fn.isBuiltIn && !fn.nameString.equals("main");
        }

        public boolean isInlinable() {
            return fnIsInlinable(callee)
                    && caller.blockList.size() <= MAX_BLOCK_NUM
                    && callee.blockList.size() <= MAX_BLOCK_NUM
                    && fnInstNum.get(callee) <= MAX_INST_NUM
                    && fnInstNum.get(caller) <= MAX_INST_NUM
                    && ((!inlineCyclic && callee.callInfo.callInst.size() == 0 || inlineCyclic));
        }
    }

    private HashSet<PendingCall> collectInlinableCall(IRModule irModule) {
        HashSet<PendingCall> ret = new HashSet<>();

        var tempFnList = new HashSet<IRFn>(irModule.varInitFnList);
        tempFnList.addAll(irModule.globalFnList);
        for (var fn : tempFnList) {
            var tempBlockList = new HashSet<IRBasicBlock>(fn.blockList);
            tempBlockList.add(fn.retBlock);
            for (var block : tempBlockList) {
                for (var inst : block.instList) {
                    if (inst instanceof CallInst call) {
                        var potentialCall = new PendingCall(call, fn, call.callee);
                        if (potentialCall.isInlinable())
                            ret.add(potentialCall);
                    }
                }
            }
        }

        return ret;
    }

    private void replaceToCopies(IRBaseInst inst, HashMap<IRBaseValue, IRBaseValue> replaceMap) {
        for (int i = 0; i < inst.getOprandNum(); ++i) {
            var oprand = inst.getOprand(i);
            if (replaceMap.containsKey(oprand)) {
                if (replaceMap.get(oprand) == null)
                    System.err.println("null");
                inst.setOprand(i, replaceMap.get(oprand));
            }
        }
    }

    private void inline(PendingCall call) {
        if (!call.isInlinable())
            return;
        var caller = call.caller;
        var callee = call.callee;
        var callBlock = call.inst.parentBlock;

        fnInstNum.put(caller, fnInstNum.get(caller) + fnInstNum.get(callee));

        var tempBlockList = new ArrayList<>(callee.blockList);
        tempBlockList.add(callee.retBlock);

        var inlinedBlockList = new ArrayList<IRBasicBlock>();

        // for every value create a copy to replace it for avoiding some problems
        HashMap<IRBaseValue, IRBaseValue> replaceMap = new HashMap<>();

        // replace parameters with actual arguments
        for (int i = 0; i < callee.paraList.size(); ++i) {
            var para = callee.paraList.get(i);
            var arg = call.inst.getOprand(i);
            replaceMap.put(para, arg);
        }

        // create copies for all instructions
        for (var block : tempBlockList) {
            var inlinedBlock = new IRBasicBlock(-1);
            inlinedBlockList.add(inlinedBlock);
            replaceMap.put(block, inlinedBlock);
            for (var phi : block.phiList) {
                var newPhi = phi.copy();
                newPhi.parentBlock = inlinedBlock;
                inlinedBlock.phiList.add((PhiInst) newPhi);
                replaceMap.put(phi, newPhi);
            }
            for (var inst : block.instList) {
                if (inst instanceof RetInst ret) {
                    if (!ret.isVoid())
                        replaceMap.put(call.inst, replaceMap.get(ret.getOprand(0)));
                } else {
                    var newInst = inst.copy();
                    newInst.parentBlock = inlinedBlock;

                    if (newInst instanceof BrInst || newInst instanceof JumpInst)
                        inlinedBlock.setTerminal(newInst);
                    else
                        inlinedBlock.instList.add(newInst);

                    replaceMap.put(inst, newInst);
                }
            }
        }

        // split the caller block
        var cutBlock = new IRBasicBlock(-1);
        var callBlockTerminal = callBlock.terminal;
        callBlock.removeTerminal();

        int callInstIdx = callBlock.instList.indexOf(call.inst);
        while (callBlock.instList.size() > callInstIdx + 1) {
            var inst = callBlock.instList.remove(callInstIdx + 1);
            inst.parentBlock = cutBlock;
            cutBlock.instList.add(inst);
        }
        callBlock.instList.remove(callInstIdx);

        // cutblock -> successor block
        cutBlock.setTerminal(callBlockTerminal);
        for (var sucBlock : cutBlock.sucList) {
            for (var phi : sucBlock.phiList)
                phi.replaceOprand(callBlock, cutBlock);
        }

        // add cut block to the caller blockList
        if (callBlock == caller.retBlock) {
            caller.blockList.add(callBlock);
            caller.retBlock = cutBlock;
        } else {
            var idx = caller.blockList.indexOf(callBlock);
            caller.blockList.add(idx + 1, cutBlock);
        }

        // insert the inlined blocks between the caller block and the cut block
        var callBlockIdx = caller.blockList.indexOf(callBlock);
        for (int i = 0; i < inlinedBlockList.size(); ++i) {
            var inlinedBlock = inlinedBlockList.get(i);
            caller.blockList.add(callBlockIdx + i + 1, inlinedBlock);
        }

        // call block -> first inlined block
        new JumpInst(inlinedBlockList.get(0), callBlock);

        // last inlined block -> cut block
        var lastInlinedBlock = inlinedBlockList.get(inlinedBlockList.size() - 1);
        new JumpInst(cutBlock, lastInlinedBlock);

        // replace all the values
        var newBlockList = new ArrayList<>(caller.blockList);
        newBlockList.add(caller.retBlock);
        for (var block : newBlockList) {
            block.instList.forEach(inst -> replaceToCopies(inst, replaceMap));
            block.phiList.forEach(phi -> replaceToCopies(phi, replaceMap));
        }

        new InfosRebuilder().rebuildCFG(caller);
    }

    private void initInstNum(IRModule irModule) {
        // initialize fnInstNum
        for (var fn : irModule.builtinFnList)
            fnInstNum.put(fn, 0);
        for (var fn : irModule.globalFnList) {
            int instNum = 0;
            for (var block : fn.blockList) {
                instNum += block.instList.size();
                instNum += block.phiList.size();
            }
            instNum += fn.retBlock.instList.size();
            instNum += fn.retBlock.phiList.size();
            fnInstNum.put(fn, instNum);
        }
        for (var fn : irModule.varInitFnList) {
            int instNum = 0;
            for (var block : fn.blockList) {
                instNum += block.instList.size();
                instNum += block.phiList.size();
            }
            instNum += fn.retBlock.instList.size();
            instNum += fn.retBlock.phiList.size();
            fnInstNum.put(fn, instNum);
        }
    }

    @Override
    public void runOnIRModule(IRModule irModule) {
        new InfosRebuilder().rebuildCFG(irModule);
        new InfosRebuilder().rebuildDefUse(irModule);

        initInstNum(irModule);

        while (true) {
            var inlinableCall = collectInlinableCall(irModule);

            if (inlinableCall.isEmpty())
                break;

            for (var call : inlinableCall) {
                System.err.println("inline from " + call.caller.nameString + " to " + call.callee.nameString);
                inline(call);
            }

            inlinableCall.clear();
        }
    }
}
