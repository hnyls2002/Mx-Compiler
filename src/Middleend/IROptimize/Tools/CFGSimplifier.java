package Middleend.IROptimize.Tools;

import java.util.ArrayList;
import java.util.HashSet;

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
        if (block != irFn.blockList.get(0) && block.sucList.size() == 1
                && block.sucList.iterator().next().preList.size() == 1)
            return true;
        return false;
    }

    private void accessibleBlockTagger(IRBasicBlock block, HashSet<IRBasicBlock> taged) {
        taged.add(block);
        for (var suc : block.sucList)
            if (!taged.contains(suc))
                accessibleBlockTagger(suc, taged);
    }

    private void simplify(IRFn irFn) {
        // delete the never accessed block
        // and remove the unaccessed predecessor in phiInst
        HashSet<IRBasicBlock> taged = new HashSet<>();
        ArrayList<IRBasicBlock> removeBlockList = new ArrayList<>();
        accessibleBlockTagger(irFn.blockList.get(0), taged);

        for (int i = 0; i < irFn.blockList.size(); ++i) {
            var block = irFn.blockList.get(i);
            if (!taged.contains(block)) {
                removeBlockList.add(block);
                for (var suc : block.sucList) {
                    for (var phi : suc.phiList)
                        phi.removePre(block);
                    suc.preList.remove(block);
                }
            }
        }

        irFn.blockList.removeAll(removeBlockList);

        // remove the phiInst if it has only one oprand
        var tempBlockList = new ArrayList<>(irFn.blockList);
        tempBlockList.add(irFn.retBlock);
        tempBlockList.forEach(block -> {
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
                next.phiList.forEach(phiInst -> phiInst.parentBlock = next);

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
