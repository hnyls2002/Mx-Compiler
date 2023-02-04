package Backend.RegAllocate;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import ASM.ASMBlock;
import ASM.ASMFn;
import ASM.ASMOprand.Register;
import Share.Pass.ASMPass.ASMFnPass;

public class LivenessAnalysis implements ASMFnPass {

    Queue<ASMBlock> workList = new LinkedList<>();
    HashSet<ASMBlock> inList = new HashSet<>();

    public void runOnASMFn(ASMFn asmFn) {
        // get the reversed block list as work-list
        workList.clear();
        inList.clear();
        for (int i = asmFn.blockList.size() - 1; i >= 0; --i) {
            var curBlock = asmFn.blockList.get(i);
            curBlock.liveIn.clear();
            curBlock.liveOut.clear();
            workList.add(curBlock);
            inList.add(curBlock);
        }

        // get a single block's def & use set
        asmFn.blockList.forEach(ASMBlock::getDefUseSet);

        while (!workList.isEmpty()) {
            var curBlock = workList.poll();
            inList.remove(curBlock);

            HashSet<Register> nLiveIn = new HashSet<>(), nLiveOut = new HashSet<>();

            // out[n] = U (s is suc) in[s]
            curBlock.sucList.forEach(sucBlock -> nLiveOut.addAll(sucBlock.liveIn));

            // in[n] = use[n] U (out[n] - def[n])
            // use the new liveOut !!!
            nLiveIn.addAll(nLiveOut);
            nLiveIn.removeAll(curBlock.defSet);
            nLiveIn.addAll(curBlock.useSet);

            // can update
            if (!nLiveIn.equals(curBlock.liveIn) || !nLiveOut.equals(curBlock.liveOut)) {
                curBlock.liveIn = nLiveIn;
                curBlock.liveOut = nLiveOut;
                curBlock.preList.forEach(preBlock -> {
                    if (!inList.contains(preBlock)) {
                        inList.add(preBlock);
                        workList.add(preBlock);
                    }
                });
            }
        }
    }

}
