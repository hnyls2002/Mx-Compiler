package Backend.RegAllocate;

import ASM.ASMBlock;
import ASM.ASMFn;
import ASM.ASMModule;
import ASM.ASMInst.ASMCalcInst;
import ASM.ASMInst.ASMLoadInst;
import ASM.ASMInst.ASMStoreInst;
import ASM.ASMOprand.Immediate;
import ASM.ASMOprand.RegOffset;
import ASM.ASMOprand.StackOffset;
import Share.Pass.ASMPass.ASMBlockPass;
import Share.Pass.ASMPass.ASMFnPass;
import Share.Pass.ASMPass.ASMModulePass;

public class StackAllocator implements ASMModulePass, ASMFnPass, ASMBlockPass {

    /*
     * --------
     * ra
     * --------
     * phi
     * --------
     * alloc
     * --------
     * vreg
     * --------
     * arg
     * -------- <-- sp
     */

    private ASMFn curFn;
    int totStackUse = 0;

    private void stackAllocate(RegOffset regOffset) {
        if (regOffset instanceof StackOffset stackOff) {
            int immInt = switch (stackOff.kind) {
                case alloca -> curFn.spilledArgMax + curFn.spilledRegCnt + stackOff.id;
                case getArg -> totStackUse + stackOff.id;
                case putArg -> stackOff.id;
                case phi -> curFn.spilledArgMax + curFn.spilledRegCnt + curFn.allocaCnt + stackOff.id;
                case vReg -> curFn.spilledArgMax + stackOff.id;
                case ra -> totStackUse - 1;
            };
            stackOff.offset = new Immediate(immInt * 4);
        }
    }

    @Override
    public void runOnASMModule(ASMModule asmModule) {
        asmModule.fnList.forEach(this::runOnASMFn);
    }

    @Override
    public void runOnASMFn(ASMFn asmFn) {
        curFn = asmFn;
        totStackUse = curFn.spilledArgMax + curFn.spilledRegCnt + curFn.allocaCnt + curFn.phiStackCnt + 1;

        ASMCalcInst loSP = (ASMCalcInst) curFn.blockList.get(0).instList.get(0);

        int totBlock = curFn.blockList.size();
        int totInst = curFn.blockList.get(totBlock - 1).instList.size();
        ASMCalcInst upSP = (ASMCalcInst) curFn.blockList.get(totBlock - 1).instList.get(totInst - 2);

        loSP.imm = new Immediate(-totStackUse * 4);
        upSP.imm = new Immediate(totStackUse * 4);
        asmFn.blockList.forEach(this::runOnASMBlock);
    }

    @Override
    public void runOnASMBlock(ASMBlock asmBlock) {
        asmBlock.instList.forEach(inst -> {
            if (inst instanceof ASMLoadInst loadInst)
                stackAllocate(loadInst.addr);
            else if (inst instanceof ASMStoreInst storeInst)
                stackAllocate(storeInst.addr);
        });
    }

}
