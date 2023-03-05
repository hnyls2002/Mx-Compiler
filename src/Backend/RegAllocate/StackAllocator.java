package Backend.RegAllocate;

import ASM.ASMFn;
import ASM.ASMModule;
import ASM.ASMInst.ASMCalcInst;
import ASM.ASMInst.ASMLoadInst;
import ASM.ASMInst.ASMStoreInst;
import ASM.ASMOprand.Immediate;
import ASM.ASMOprand.VirtualOffset;
import Share.Pass.ASMPass.ASMFnPass;
import Share.Pass.ASMPass.ASMModulePass;

public class StackAllocator implements ASMModulePass, ASMFnPass {

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

    private int stackAllocate(Immediate imm) {
        if (imm instanceof VirtualOffset vOffset) {
            int immInt = switch (vOffset.label) {
                case alloca -> curFn.spilledArgMax + curFn.stackRegCnt + vOffset.rank;
                case getSpilledArg -> totStackUse + vOffset.rank;
                case putSpilledArg -> vOffset.rank;
                case phi -> curFn.spilledArgMax + curFn.stackRegCnt + curFn.allocaCnt + vOffset.rank;
                case virtualReg -> curFn.spilledArgMax + vOffset.rank;
                case ra -> totStackUse - 1;
            };
            return immInt * 4;
        }
        return imm.immInt;
    }

    @Override
    public void runOnASMModule(ASMModule asmModule) {
        asmModule.fnList.forEach(this::runOnASMFn);
    }

    @Override
    public void runOnASMFn(ASMFn asmFn) {
        curFn = asmFn;
        totStackUse = curFn.spilledArgMax + curFn.stackRegCnt + curFn.allocaCnt + curFn.phiStackCnt + 1;

        ASMCalcInst loSP = (ASMCalcInst) curFn.blockList.get(0).instList.get(0);

        int totBlock = curFn.blockList.size();
        int totInst = curFn.blockList.get(totBlock - 1).instList.size();
        ASMCalcInst upSP = (ASMCalcInst) curFn.blockList.get(totBlock - 1).instList.get(totInst - 2);

        loSP.imm = new Immediate(-totStackUse * 4);
        upSP.imm = new Immediate(totStackUse * 4);

        asmFn.blockList.forEach(asmBlock -> {
            asmBlock.instList.forEach(inst -> {
                // after stack allocation, all virtual offset will be replaced by immediate
                if (inst instanceof ASMLoadInst ldInst && ldInst.imm instanceof VirtualOffset) {
                    ldInst.imm = new Immediate(stackAllocate(ldInst.imm));
                } else if (inst instanceof ASMStoreInst stInst && stInst.imm instanceof VirtualOffset)
                    stInst.imm = new Immediate(stackAllocate(stInst.imm));
            });
        });
    }
}
