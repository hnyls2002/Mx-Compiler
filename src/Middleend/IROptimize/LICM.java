package Middleend.IROptimize;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import IR.IRModule;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import Middleend.IROptimize.Tools.AliasAnalyzer;
import Middleend.IROptimize.Tools.CallGraphAnalyzer;
import Middleend.IROptimize.Tools.IRLoop;
import Middleend.IROptimize.Tools.InfosRebuilder;
import Middleend.IROptimize.Tools.LoopAnalyzer;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRLoopPass;
import Share.Pass.IRPass.IRModulePass;

public class LICM implements IRModulePass, IRFnPass, IRLoopPass {
    // loop invariant code motion

    // checkInvariant requires AliasAnalyzer
    // chekcInvariant requires CallGraphAnalyze to get callInfo

    public int movableInstCnt = 0;
    public AliasAnalyzer aliasAnalyzer = new AliasAnalyzer();

    @Override
    public void runOnIRModule(IRModule irModule) {
        new CallGraphAnalyzer().runOnIRModule(irModule);
        new LoopAnalyzer().runOnIRModule(irModule);
        new InfosRebuilder().rebuildDefUse(irModule);
        new InfosRebuilder().rebuildCFG(irModule);
        aliasAnalyzer.runOnIRModule(irModule);
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
        System.err.println("LICM: \t" + movableInstCnt + " insts moved");
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        fn.topLoopList.forEach(this::runOnIRLoop);
    }

    private void collectMovables(IRLoop loop, IRBasicBlock block, Queue<IRBaseInst> movableInsts,
            HashSet<IRBasicBlock> visited) {
        if (visited.contains(block) || !loop.contents.contains(block))
            return;
        visited.add(block);
        for (var inst : block.instList) {
            if (loop.checkInvariant(inst, aliasAnalyzer))
                movableInsts.offer(inst);
        }
        for (var succ : block.sucList)
            collectMovables(loop, succ, movableInsts, visited);
    }

    @Override
    public void runOnIRLoop(IRLoop loop) {
        loop.createPreHeader();

        Queue<IRBaseInst> movableInsts = new LinkedList<>();
        HashSet<IRBasicBlock> visited = new HashSet<>();
        collectMovables(loop, loop.header, movableInsts, visited);

        movableInstCnt += movableInsts.size();

        while (!movableInsts.isEmpty()) {
            var inst = movableInsts.poll();
            inst.parentBlock.instList.remove(inst);
            loop.preHeader.addInstBeforeTerminal(inst);
            inst.parentBlock = loop.preHeader;
        }

        loop.subLoops.forEach(this::runOnIRLoop);
    }
}
