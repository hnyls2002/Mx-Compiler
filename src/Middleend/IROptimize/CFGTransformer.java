package Middleend.IROptimize;

import java.util.ArrayList;

import IR.IRModule;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.JumpInst;

public class CFGTransformer {
    public void simplify(IRModule irModule) {
        irModule.globalFnList.forEach(irFn -> simplify(irFn));
        irModule.varInitFnList.forEach(irFn -> simplify(irFn));
    }

    public void splitCriticalEdge(IRModule irModule) {
        irModule.globalFnList.forEach(this::splitCriticalEdge);
        irModule.varInitFnList.forEach(this::splitCriticalEdge);
    }

    private boolean mergeJudge(IRBasicBlock block, IRFn irFn) {
        // if the block has only one suc and the suc has only one pre
        if (block.sucList.size() == 1 && block.sucList.iterator().next().preList.size() == 1
                && block.sucList.iterator().next() != irFn.retBlock)
            return true;
        return false;
    }

    public void simplify(IRFn irFn) {
        // delete the never accessed block
        while (true) {
            boolean flag = false;
            for (int i = 1; i < irFn.blockList.size(); ++i) {
                var blk = irFn.blockList.get(i);
                if (blk.preList.isEmpty()) {
                    flag = true;
                    for (var suc : blk.sucList)
                        suc.preList.remove(blk);
                    irFn.blockList.remove(blk);
                }
            }
            if (!flag)
                break;
        }

        // remove the phiInst if it has only one oprand
        irFn.blockList.forEach(block -> {
            var it = block.phiList.iterator();
            while (it.hasNext()) {
                var phiInst = it.next();
                if (phiInst.getOprandNum() == 2)
                    it.remove();
            }
        });

        // merge the block into its successor
        while (true) {
            boolean flag = false;
            var it = irFn.blockList.iterator();
            while (it.hasNext()) {
                var block = it.next();
                if (!mergeJudge(block, irFn))
                    continue;
                flag = true;

                var next = block.sucList.iterator().next();

                block.replaceAllUseWith(next);

                // merge inst into next
                block.instList.remove(block.terminal);
                block.instList.forEach(inst -> inst.parentBlock = next);
                block.instList.addAll(next.instList);
                next.instList = block.instList;
                next.phiList = block.phiList;

                // modify CFG graph
                block.preList.forEach(pre -> pre.sucList.replaceAll(x -> x == block ? next : x));
                next.preList = block.preList;

                it.remove();
            }

            if (!flag)
                break;
        }
    }

    public void splitCriticalEdge(IRFn fn) {
        ArrayList<IRBasicBlock> tmpAddList = new ArrayList<>();
        for (var fromBlock : fn.blockList) {
            if (fromBlock.sucList.size() <= 1)
                continue;
            for (var toBlock : fromBlock.sucList) {
                if (toBlock.preList.size() <= 1)
                    continue;
                // we now find a critical edge fromBlock -> toBlock
                assert fromBlock.sucList.size() == 2 && toBlock.preList.size() == 2;

                var insertBlock = new IRBasicBlock(toBlock.loopDepth);
                tmpAddList.add(insertBlock);

                // replace CFG graph
                for (int i = 0; i < fromBlock.sucList.size(); ++i)
                    if (fromBlock.sucList.get(i) == toBlock) {
                        fromBlock.sucList.set(i, insertBlock);
                        insertBlock.preList.add(fromBlock);
                    }
                for (int i = 0; i < toBlock.preList.size(); ++i)
                    if (toBlock.preList.get(i) == fromBlock) {
                        toBlock.preList.set(i, insertBlock);
                        insertBlock.sucList.add(toBlock);
                    }

                // modify phi inst
                for (var phiInst : toBlock.phiList)
                    for (int i = 0; i < phiInst.getOprandNum(); ++i)
                        if (phiInst.getOprand(i) == fromBlock)
                            phiInst.setOprand(i, insertBlock);

                // modify terminator
                for (int i = 0; i < fromBlock.terminal.getOprandNum(); ++i)
                    if (fromBlock.terminal.getOprand(i) == toBlock)
                        fromBlock.terminal.setOprand(i, insertBlock);

                insertBlock.terminal = new JumpInst(toBlock);
                insertBlock.terminal.parentBlock = insertBlock;
                insertBlock.instList.add(insertBlock.terminal);
            }
        }

        fn.blockList.addAll(tmpAddList);
    }
}
