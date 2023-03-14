package Backend.RegAllocate;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import ASM.ASMBlock;
import ASM.ASMFn;
import ASM.ASMOprand.Register;
import Share.Pass.ASMPass.ASMFnPass;

public class LivenessAnalysis implements ASMFnPass {
    // BFS method to find the fixed point of liveness
    Queue<ASMBlock> workList = new LinkedList<>();
    HashSet<ASMBlock> isInList = new HashSet<>();

    @Override
    public void runOnASMFn(ASMFn asmFn) {
        workList.clear();
        isInList.clear();

        // reverse the block list into workList
        for (int i = asmFn.blockList.size() - 1; i >= 0; --i) {
            var curBlock = asmFn.blockList.get(i);
            workList.add(curBlock);
            isInList.add(curBlock);
            curBlock.liveInSet.clear();
            curBlock.liveOutSet.clear();
        }

        // initial every block's def and use set
        asmFn.blockList.forEach(ASMBlock::getDefUseSet);

        // calculate block's liveness
        while (workList.isEmpty()) {
            var curBlock = workList.poll();
            isInList.remove(curBlock);

            HashSet<Register> nLiveIn = new HashSet<>(), nLiveOut = new HashSet<>();

            // out = U suc.in
            curBlock.sucList.forEach(sucBlock -> nLiveOut.addAll(sucBlock.liveInSet));

            // in = use U (out - def)
            nLiveIn.addAll(nLiveOut);
            nLiveIn.removeAll(curBlock.defSet);
            nLiveIn.addAll(curBlock.useSet);

            // expand the workList
            if (!nLiveIn.equals(curBlock.liveInSet) || !nLiveOut.equals(curBlock.liveOutSet)) {
                curBlock.liveInSet = nLiveIn;
                curBlock.liveOutSet = nLiveOut;
                curBlock.preList.forEach(preBlock -> {
                    if (!isInList.contains(preBlock)) {
                        isInList.add(preBlock);
                        workList.add(preBlock);
                    }
                });
            }
        }
    }
}
