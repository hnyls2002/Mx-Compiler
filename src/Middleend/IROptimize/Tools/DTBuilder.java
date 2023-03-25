package Middleend.IROptimize.Tools;

import java.util.ArrayList;
import java.util.HashSet;

import IR.IRModule;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class DTBuilder implements IRModulePass, IRFnPass {
    public static class DTreeNode {
        public HashSet<IRBasicBlock> slaveSet = new HashSet<>();
        public HashSet<IRBasicBlock> masterSet = new HashSet<>();
        public HashSet<IRBasicBlock> frontier = new HashSet<>();
        public IRBasicBlock idom = null;

        public void clear() {
            slaveSet.clear();
            masterSet.clear();
            frontier.clear();
            idom = null;
        }
    }

    boolean isPostDominate = false;

    public void buildDT(IRModule irModule, boolean isPostDominate) {
        this.isPostDominate = isPostDominate;
        runOnIRModule(irModule);
    }

    @Override
    public void runOnIRModule(IRModule irModule) {
        new CFGSimplifier().simplify(irModule);
        new InfosRebuilder().rebuildCFG(irModule);
        irModule.varInitFnList.forEach(this::runOnIRFn);
        irModule.globalFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {

        // clear all the DT infomation
        fn.blockList.forEach(block -> block.dtNode.clear());
        fn.retBlock.dtNode.clear();

        buildDominateTree(fn);
    }

    IRBasicBlock deleted;
    HashSet<IRBasicBlock> notVisited = new HashSet<>();

    void dfs(IRBasicBlock o) {
        if (o == deleted || !notVisited.contains(o))
            return;
        notVisited.remove(o);
        if (isPostDominate)
            for (var pre : o.preList)
                dfs(pre);
        else
            for (var suc : o.sucList)
                dfs(suc);
    }

    public void buildDominateTree(IRFn fn) {
        IRBasicBlock root;
        if (isPostDominate)
            root = fn.retBlock;
        else
            root = fn.blockList.get(0);

        root.dtNode.slaveSet.addAll(fn.blockList);
        root.dtNode.slaveSet.add(fn.retBlock);
        root.dtNode.slaveSet.forEach(s -> s.dtNode.masterSet.add(root));

        for (var curBlock : root.dtNode.slaveSet) {
            if (curBlock == root)
                continue;
            deleted = curBlock;
            notVisited.clear();
            notVisited.addAll(root.dtNode.slaveSet);
            dfs(root);
            curBlock.dtNode.slaveSet.addAll(notVisited);
            notVisited.forEach(slave -> slave.dtNode.masterSet.add(curBlock));
        }

        // get the dominance frontier
        var blockList = new ArrayList<>(fn.blockList);
        blockList.add(fn.retBlock);

        if (isPostDominate) {
            for (var curBlock : blockList) {
                for (var pre : curBlock.preList) {
                    // if u -> v edge exist and u doesn't dominat v
                    for (var master : curBlock.dtNode.masterSet)
                        if (master == pre || !pre.dtNode.masterSet.contains(master))
                            master.dtNode.frontier.add(pre);
                }
            }
        } else {
            for (var curBlock : blockList) {
                for (var suc : curBlock.sucList) {
                    // if u -> v edge exist and u doesn't dominat v
                    for (var master : curBlock.dtNode.masterSet)
                        if (master == suc || !suc.dtNode.masterSet.contains(master))
                            master.dtNode.frontier.add(suc);
                }
            }
        }

        // get the immediate dominator
        for (var block : blockList) {
            if (block == root)
                continue;
            int minSize = Integer.MAX_VALUE;
            for (var master : block.dtNode.masterSet) {
                if (master != block && master.dtNode.slaveSet.size() < minSize) {
                    minSize = master.dtNode.slaveSet.size();
                    block.dtNode.idom = master;
                }
            }
        }
    }

    public void debug(IRModule irModule, boolean isPostDominate) {
        // -------------- debug --------------------
        IRBasicBlock root;
        if (isPostDominate)
            root = irModule.globalFnList.get(0).retBlock;
        else
            root = irModule.globalFnList.get(0).blockList.get(0);
        for (var bb : root.dtNode.slaveSet) {
            System.err.println("block : " + bb.getName());

            System.err.println("slaveSet : ");
            for (var slave : bb.dtNode.slaveSet)
                System.err.println(slave.getName());

            System.err.println("masterSet : ");
            for (var master : bb.dtNode.masterSet)
                System.err.println(master.getName());

            System.err.println("frontier : ");
            for (var frontier : bb.dtNode.frontier)
                System.err.println(frontier.getName());

            if (bb.dtNode.idom != null)
                System.err.println("idom : " + bb.dtNode.idom.getName());

            System.err.println("\n-----------------------------------");
        }
    }
}
