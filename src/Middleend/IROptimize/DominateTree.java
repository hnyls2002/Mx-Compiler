package Middleend.IROptimize;

import java.util.HashSet;

import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.BrInst;
import Share.Pass.IRPass.IRFnPass;

public class DominateTree implements IRFnPass {

    @Override
    public void runOnIRFn(IRFn fn) {
        // System.err.println(fn.getName());
        // ignoring retBlock is ok
        for (var curBlock : fn.blockList) {
            if (curBlock.getTerminal() instanceof BrInst brInst) {
                if (brInst.condition == null) { // direct jump
                    curBlock.sucList.add(brInst.trueBlock);
                    brInst.trueBlock.preList.add(curBlock);
                } else {
                    curBlock.sucList.add(brInst.trueBlock);
                    brInst.trueBlock.preList.add(curBlock);
                    curBlock.sucList.add(brInst.falseBlock);
                    brInst.falseBlock.preList.add(curBlock);
                }

            }
        }

        // delete the never arrivable block
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
        }

        buildDominateTree(fn);
    }

    public static class DTreeNode {
        HashSet<IRBasicBlock> slaveSet = new HashSet<>();
        HashSet<IRBasicBlock> masterSet = new HashSet<>();
        HashSet<IRBasicBlock> frontier = new HashSet<>();
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

        // -------------- debug --------------------
        // for (var bb : root.dtNode.slaveSet) {
        // System.err.println(bb.getName());
        // System.err.println("slaveSet : ");
        // for (var slave : bb.dtNode.slaveSet)
        // System.err.println(slave.getName());
        // System.err.println("masterSet : ");
        // for (var master : bb.dtNode.masterSet)
        // System.err.println(master.getName());
        // System.err.println("\n-----------------------------------");
        // }

        // get the dominance frontier
        for (var curBlock : fn.blockList) {
            for (var suc : curBlock.sucList) {
                // if u -> v edge exist and u doesn't dominat v
                if (!curBlock.dtNode.slaveSet.contains(suc)) {
                    for (var master : curBlock.dtNode.masterSet)
                        master.dtNode.frontier.add(suc);
                }
            }
        }

        // -------------- debug --------------------
        // for (var bb : root.dtNode.slaveSet) {
        // System.err.println(bb.getName());
        // System.err.println("frontier : ");
        // for (var e : bb.dtNode.frontier)
        // System.err.println(e.getName());
        // System.err.println("\n-----------------------------------");
        // }
    }
}
