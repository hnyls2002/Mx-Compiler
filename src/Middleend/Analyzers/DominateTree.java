package Middleend.Analyzers;

import java.util.HashSet;

import IR.IRModule;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class DominateTree implements IRModulePass, IRFnPass {
    public static class DTreeNode {
        public HashSet<IRBasicBlock> slaveSet = new HashSet<>();
        public HashSet<IRBasicBlock> masterSet = new HashSet<>();
        public HashSet<IRBasicBlock> frontier = new HashSet<>();

        public void clear() {
            slaveSet.clear();
            masterSet.clear();
            frontier.clear();
        }
    }

    boolean isPostDominate = false;

    public void buildDT(IRModule irModule, boolean isPostDominate) {
        this.isPostDominate = isPostDominate;
        runOnIRModule(irModule);
    }

    @Override
    public void runOnIRModule(IRModule irModule) {
        irModule.varInitFnList.forEach(this::runOnIRFn);
        irModule.globalFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        // delete the never accessed block
        while (true) {
            boolean flag = false;
            for (int i = 1; i < fn.blockList.size(); ++i) {
                var blk = fn.blockList.get(i);
                if (blk.preList.isEmpty()) {
                    flag = true;
                    for (var suc : blk.sucList)
                        suc.preList.remove(blk);
                    fn.blockList.remove(blk);
                }
            }
            if (!flag)
                break;
        }

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

        if (isPostDominate) {
            for (var curBlock : fn.blockList) {
                for (var pre : curBlock.preList) {
                    // if u -> v edge exist and u doesn't dominat v
                    for (var master : curBlock.dtNode.masterSet)
                        if (master == pre || !pre.dtNode.masterSet.contains(master))
                            master.dtNode.frontier.add(pre);
                }
            }
        } else {
            for (var curBlock : fn.blockList) {
                for (var suc : curBlock.sucList) {
                    // if u -> v edge exist and u doesn't dominat v
                    for (var master : curBlock.dtNode.masterSet)
                        if ( master == suc || !suc.dtNode.masterSet.contains(master))
                            master.dtNode.frontier.add(suc);
                }
            }
        }

    }
}
