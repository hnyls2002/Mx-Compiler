package Middleend.Analyzers;

import java.util.HashSet;

import IR.IRModule;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class DominateTree implements IRModulePass, IRFnPass {
    @Override
    public void runOnIRModule(IRModule irModule) {
        irModule.varInitFnList.forEach(this::runOnIRFn);
        irModule.globalFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        // int cnt = 0;
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
                    break;
                }
            }
            if (!flag)
                break;
            // System.err.println("Delete " + (++cnt) + " times");
        }

        buildDominateTree(fn);
    }

    public static class DTreeNode {
        public HashSet<IRBasicBlock> slaveSet = new HashSet<>();
        public HashSet<IRBasicBlock> masterSet = new HashSet<>();
        public HashSet<IRBasicBlock> frontier = new HashSet<>();
    }

    IRBasicBlock deleted;
    HashSet<IRBasicBlock> notVisited = new HashSet<>();

    void dfs(IRBasicBlock o) {
        if (o == deleted || !notVisited.contains(o))
            return;
        notVisited.remove(o);
        for (var suc : o.sucList)
            dfs(suc);
    }

    public void buildDominateTree(IRFn fn) {
        var root = fn.blockList.get(0);
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
        for (var curBlock : fn.blockList) {
            for (var suc : curBlock.sucList) {
                // if u -> v edge exist and u doesn't dominat v
                for (var master : curBlock.dtNode.masterSet)
                    if (!suc.dtNode.masterSet.contains(master)) {
                        master.dtNode.frontier.add(suc);
                    }
            }
        }

    }
}
