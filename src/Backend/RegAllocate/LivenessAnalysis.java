package Backend.RegAllocate;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import ASM.ASMBlock;
import ASM.ASMFn;
import Share.Pass.ASMPass.ASMFnPass;

public class LivenessAnalysis implements ASMFnPass {
    // BFS method to find the fixed point of liveness
    Queue<ASMBlock> workList = new LinkedList<>();
    HashSet<ASMBlock> isInList = new HashSet<>();

    @Override
    public void runOnASMFn(ASMFn asmFn) {
        // reverse the block list
        workList.clear();
        isInList.clear();
        for (int i = asmFn.blockList.size() - 1; i >= 0; --i) {
            var curBlock = asmFn.blockList.get(i);
            workList.add(curBlock);
            isInList.add(curBlock);
        }
    }
}
