package Backend.RegAllocate;

import java.util.ArrayList;

import ASM.ASMBlock;
import ASM.ASMFn;
import ASM.ASMModule;
import ASM.ASMInst.ASMBaseInst;
import ASM.ASMInst.ASMLoadInst;
import ASM.ASMInst.ASMStoreInst;
import ASM.ASMOprand.PhysicalReg;
import ASM.ASMOprand.Register;
import ASM.ASMOprand.VirtualOffset;
import ASM.ASMOprand.VirtualReg;
import Backend.ASMCurrent;
import Share.Lang.RV32.BitWidth;
import Share.Lang.RV32.SPLabel;
import Share.Pass.ASMPass.ASMBlockPass;
import Share.Pass.ASMPass.ASMFnPass;
import Share.Pass.ASMPass.ASMModulePass;

public class BfRegAllocator implements ASMModulePass, ASMFnPass, ASMBlockPass {

    public ASMCurrent cur = new ASMCurrent();
    public final PhysicalReg tmp0 = PhysicalReg.getPhyReg("t0");
    public final PhysicalReg tmp1 = PhysicalReg.getPhyReg("t1");
    public final PhysicalReg sp = PhysicalReg.getPhyReg("sp");

    private PhysicalReg regAllocateRead(Register reg, PhysicalReg a) {
        if (reg instanceof VirtualReg t) {
            cur.fn.spilledRegCnt = Math.max(cur.fn.spilledRegCnt, t.id + 1);
            new ASMLoadInst(a, sp, new VirtualOffset(SPLabel.spilledReg, t.id), BitWidth.w, cur.block);
            return a;
        }
        return (PhysicalReg) reg;
    }

    private PhysicalReg regAllocateWrite(Register reg, PhysicalReg a) {
        if (reg instanceof VirtualReg t) {
            cur.fn.spilledRegCnt = Math.max(cur.fn.spilledRegCnt, t.id + 1);
            new ASMStoreInst(sp, a, new VirtualOffset(SPLabel.spilledReg, t.id), BitWidth.w, cur.block);
            return a;
        }
        return (PhysicalReg) reg;
    }

    @Override
    public void runOnASMModule(ASMModule asmModule) {
        asmModule.fnList.forEach(this::runOnASMFn);
    }

    @Override
    public void runOnASMFn(ASMFn asmFn) {
        cur.fn = asmFn;
        asmFn.blockList.forEach(this::runOnASMBlock);
    }

    @Override
    public void runOnASMBlock(ASMBlock asmBlock) {
        cur.block = asmBlock;
        ArrayList<ASMBaseInst> rawList = asmBlock.instList;
        asmBlock.instList = new ArrayList<>();
        rawList.forEach(inst -> {
            if (inst.rs1 != null)
                inst.rs1 = regAllocateRead(inst.rs1, tmp0);
            if (inst.rs2 != null)
                inst.rs2 = regAllocateRead(inst.rs2, tmp1);
            cur.block.instList.add(inst);
            if (inst.rd != null)
                inst.rd = regAllocateWrite(inst.rd, tmp0);
        });
    }

}
