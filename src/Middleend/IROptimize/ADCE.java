package Middleend.IROptimize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import IR.IRModule;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import IR.IRValue.IRUser.IRInst.JumpInst;
import IR.IRValue.IRUser.IRInst.PhiInst;
import IR.IRValue.IRUser.IRInst.RetInst;
import IR.IRValue.IRUser.IRInst.StoreInst;
import Middleend.IROptimize.Tools.CFGSimplifier;
import Middleend.IROptimize.Tools.CallGraphAnalyzer;
import Middleend.IROptimize.Tools.DTBuilder;
import Middleend.IROptimize.Tools.InfosRebuilder;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class ADCE implements IRModulePass, IRFnPass {
    /*
     * 1) live inst -> expands live oprands (not include block)
     * 2) live block (which has a live inst in it) -> expands branch inst
     * 
     * Special case:
     * 3) weak live block (which is a phi's oprand) -> expands branch inst
     * a phi's result comes from different predecessor blocks : bb1, bb2
     * we need to know which branch inst leads to different blocks
     * that is : bb1 or bb2 depends on br0 inst
     * 
     * directly find all bb1 and bb2's dependence blocks (post-dominator frontier)
     * 
     */

    /*
     * sample code
     * int main() {
     * int a = getInt();
     * int b;
     * if (a == 1) b = 1;
     * else {
     * b = 2;
     * int sum = 0;
     * for (int i = 1; i <= 100000; i++) sum = sum + i;
     * if (sum == 0) sum = 0;
     * else sum = 1;
     * }
     * printlnInt(b);
     * return 0;
     * }
     */

    @Override
    public void runOnIRModule(IRModule irModule) {
        new DTBuilder().buildDT(irModule, true);
        new CallGraphAnalyzer().runOnIRModule(irModule);
        new InfosRebuilder().rebuildCFG(irModule);
        new InfosRebuilder().rebuildDefUse(irModule);
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
        new CFGSimplifier().simplify(irModule);
        System.err.println("ADCE: " + totDeletedInst + " insts deleted");
    }

    HashSet<IRBasicBlock> liveBlock = new HashSet<>();
    HashSet<IRBaseInst> liveInst = new HashSet<>();
    HashSet<IRBasicBlock> weakLiveBlock = new HashSet<>();

    Queue<IRBaseValue> workList = new LinkedList<>();
    public int totDeletedInst = 0;

    private boolean isInitLive(IRBaseInst inst) {
        if (inst instanceof CallInst call) {
            // return true;
            return call.callee.callInfo.hasOutSideEffect();
        }
        if (inst instanceof StoreInst || inst instanceof RetInst)
            return true;
        return false;
    }

    public void initWorkList(IRFn irFn) {
        workList.clear();
        irFn.blockList.forEach(block -> {
            block.instList.forEach(inst -> {
                if (isInitLive(inst))
                    addInst(inst);
            });
        });
        irFn.retBlock.instList.forEach(inst -> {
            if (isInitLive(inst))
                addInst(inst);
        });
    }

    private void addInst(IRBaseInst inst) {
        if (!liveInst.contains(inst)) {
            liveInst.add(inst);
            workList.offer(inst);
            addBlock(inst.parentBlock);
        }
    }

    private void addBlock(IRBasicBlock block) {
        if (!liveBlock.contains(block)) {
            liveBlock.add(block);
            workList.offer(block);
        }
    }

    private void expandInst(IRBaseInst inst) {
        for (int i = 0; i < inst.getOprandNum(); ++i) {
            var oprand = inst.getOprand(i);
            if (oprand instanceof IRBaseInst freshInst)
                addInst(freshInst);
            else if (oprand instanceof IRBasicBlock block && inst instanceof PhiInst) {
                weakLiveBlock.add(block);
                expandBlock(block);
            }
        }
    }

    // a live block -> expands its frontiers (branch inst)
    private void expandBlock(IRBasicBlock block) {
        for (var ft : block.dtNode.frontier)
            addInst(ft.terminal);
    }

    @Override
    public void runOnIRFn(IRFn irFn) {
        initWorkList(irFn);
        while (!workList.isEmpty()) {
            var head = workList.poll();
            if (head instanceof IRBaseInst inst)
                expandInst(inst);
            if (head instanceof IRBasicBlock block)
                expandBlock(block);
        }

        // remove dead inst
        var tempBlockList = new ArrayList<>(irFn.blockList);
        tempBlockList.add(irFn.retBlock);

        for (var block : tempBlockList) {
            int size0 = block.instList.size() + block.phiList.size();
            block.instList.removeIf(inst -> !liveInst.contains(inst));
            block.phiList.removeIf(inst -> !liveInst.contains(inst));
            totDeletedInst += size0 - (block.instList.size() + block.phiList.size());

            var terminal = block.terminal;
            if (!liveInst.contains(terminal)) {
                IRBasicBlock newTarget = terminal instanceof JumpInst ? (IRBasicBlock) terminal.getOprand(0)
                        : (IRBasicBlock) terminal.getOprand(1);
                while (!weakLiveBlock.contains(newTarget) && !liveBlock.contains(newTarget))
                    newTarget = newTarget.dtNode.idom;
                block.removeTerminal();
                new JumpInst(newTarget, block);
                --totDeletedInst;
            }
        }
    }
}
