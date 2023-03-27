package Middleend.IROptimize.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.antlr.v4.runtime.misc.Pair;

import IR.IRModule;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class LoopAnalyzer implements IRModulePass, IRFnPass {
    public class Loop {
        public IRBasicBlock header, preHeader;
        public HashSet<IRBasicBlock> tails = new HashSet<>();
        public HashSet<IRBasicBlock> contents = new HashSet<>();
        public HashSet<Loop> subLoops = new HashSet<>();
        public Loop parentLoop = null;

        public Loop(IRBasicBlock header) {
            this.header = header;
        }
    }

    @Override
    public void runOnIRModule(IRModule irModule) {
        new DTBuilder().buildDT(irModule, false);
        new InfosRebuilder().rebuildCFG(irModule);
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
    }

    HashSet<Pair<IRBasicBlock, IRBasicBlock>> backEdgeList = new HashSet<>();
    HashMap<IRBasicBlock, Loop> headerLoopMap = new HashMap<>();
    Stack<Loop> loopStack = new Stack<>();
    HashSet<IRBasicBlock> visited = new HashSet<>();

    @Override
    public void runOnIRFn(IRFn fn) {
        // find back edges : block -> suc && suc dominates block
        // System.err.println("<" + fn.getName() + ">");

        var tempBlockList = new ArrayList<IRBasicBlock>(fn.blockList);
        tempBlockList.add(fn.retBlock);

        backEdgeList.clear();
        headerLoopMap.clear();
        loopStack.clear();
        visited.clear();

        for (var block : tempBlockList)
            for (var suc : block.sucList)
                if (suc.dtNode.slaveSet.contains(block))
                    backEdgeList.add(new Pair<>(block, suc));

        for (var backEdge : backEdgeList)
            buildNaturalLoop(backEdge.a, backEdge.b);

        buildLoopTree(tempBlockList.get(0), fn);

        // headerLoopMap.forEach((h, l) -> {
        // System.err.println("block " + h.getName());
        // System.err.print("subloop : ");
        // l.subLoops.forEach(sl -> System.err.print(sl.header.getName() + ", "));
        // System.err.println();
        // });
    }

    private void buildNaturalLoop(IRBasicBlock tail, IRBasicBlock head) {
        headerLoopMap.putIfAbsent(head, new Loop(head));
        var loop = headerLoopMap.get(head);
        loop.contents.add(head);
        loop.contents.add(tail);
        loop.tails.add(tail);

        Queue<IRBasicBlock> workList = new LinkedList<>();
        workList.offer(tail);
        while (!workList.isEmpty()) {
            var bb = workList.poll();
            for (var pre : bb.preList)
                if (!loop.contents.contains(pre)) {
                    loop.contents.add(pre);
                    workList.offer(pre);
                }
        }
    }

    // dfs the CFG to build the loop tree
    private void buildLoopTree(IRBasicBlock block, IRFn fn) {
        if (visited.contains(block))
            return;
        visited.add(block);

        // remove the loop which does not contain the block
        while (!loopStack.isEmpty() && !loopStack.peek().contents.contains(block))
            loopStack.pop();

        Loop parentLoop = loopStack.isEmpty() ? null : loopStack.peek();

        if (headerLoopMap.containsKey(block)) {
            var loop = headerLoopMap.get(block);
            loop.parentLoop = parentLoop;
            if (parentLoop != null)
                parentLoop.subLoops.add(loop);
            else
                fn.topLoopList.add(loop);
            loopStack.push(loop);
        }

        block.loopDepth = loopStack.size();

        for (var suc : block.sucList)
            buildLoopTree(suc, fn);
    }

}
