package Middleend.IROptimize.Tools;

import IR.IRModule;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;

public class CFGSimplifier {
    public void simplify(IRModule irModule) {
        new InfosRebuilder().rebuildCFG(irModule);
        new InfosRebuilder().rebuildDefUse(irModule);
        irModule.globalFnList.forEach(irFn -> simplify(irFn));
        irModule.varInitFnList.forEach(irFn -> simplify(irFn));
    }

    private boolean mergeJudge(IRBasicBlock block, IRFn irFn) {
        // if the block has only one suc and the suc has only one pre
        if (block.sucList.size() == 1 && block.sucList.iterator().next().preList.size() == 1
                && block.sucList.iterator().next() != irFn.retBlock)
            return true;
        return false;
    }

    private void simplify(IRFn irFn) {
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
                if (phiInst.getOprandNum() == 2) {
                    // discard the phiInst
                    phiInst.replaceAllUseWith(phiInst.getOprand(1));
                    it.remove();
                }
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
}
