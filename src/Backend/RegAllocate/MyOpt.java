package Backend.RegAllocate;

import ASM.ASMBlock;
import ASM.ASMFn;
import ASM.ASMModule;
import ASM.ASMInst.ASMMoveInst;
import Share.Pass.ASMPass.ASMBlockPass;
import Share.Pass.ASMPass.ASMFnPass;
import Share.Pass.ASMPass.ASMModulePass;

// remove the coalesced moves
// merge the block

public class MyOpt implements ASMModulePass, ASMFnPass, ASMBlockPass {

    @Override
    public void runOnASMModule(ASMModule asmModule) {
        asmModule.fnList.forEach(this::runOnASMFn);
    }

    @Override
    public void runOnASMFn(ASMFn asmFn) {
        asmFn.blockList.forEach(this::runOnASMBlock);

    }

    @Override
    public void runOnASMBlock(ASMBlock asmBlock) {
        var it = asmBlock.instList.iterator();
        while (it.hasNext()) {
            var inst = it.next();
            if (inst instanceof ASMMoveInst mvInst && mvInst.rd.color == mvInst.rs.color)
                it.remove();
        }
    }

}
