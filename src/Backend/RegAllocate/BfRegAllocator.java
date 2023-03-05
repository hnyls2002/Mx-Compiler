package Backend.RegAllocate;

import java.util.ArrayList;

import ASM.ASMBlock;
import ASM.ASMFn;
import ASM.ASMModule;
import ASM.ASMInst.ASMBaseInst;
import ASM.ASMInst.ASMBrInst;
import ASM.ASMInst.ASMCalcInst;
import ASM.ASMInst.ASMCallInst;
import ASM.ASMInst.ASMJInst;
import ASM.ASMInst.ASMLaInst;
import ASM.ASMInst.ASMLiInst;
import ASM.ASMInst.ASMLoadInst;
import ASM.ASMInst.ASMMoveInst;
import ASM.ASMInst.ASMRetInst;
import ASM.ASMInst.ASMStoreInst;
import ASM.ASMOprand.PhysicalReg;
import ASM.ASMOprand.Register;
import ASM.ASMOprand.StackOffset;
import ASM.ASMOprand.VirtualReg;
import ASM.ASMOprand.StackOffset.stackDataKind;
import Backend.ASMCurrent;
import Share.Lang.RV32;
import Share.Pass.ASMPass.ASMBlockPass;
import Share.Pass.ASMPass.ASMFnPass;
import Share.Pass.ASMPass.ASMModulePass;
import Share.Visitors.ASMInstVisitor;

public class BfRegAllocator implements ASMModulePass, ASMFnPass, ASMBlockPass, ASMInstVisitor {

    public ASMCurrent cur = new ASMCurrent();
    public final PhysicalReg tmp0 = PhysicalReg.getPhyReg("t0");
    public final PhysicalReg tmp1 = PhysicalReg.getPhyReg("t1");

    private PhysicalReg regAllocateRead(Register reg, PhysicalReg a) {
        if (reg instanceof VirtualReg t) {
            cur.fn.stackRegCnt = Math.max(cur.fn.stackRegCnt, t.id + 1);
            StackOffset mem = new StackOffset(t.id, stackDataKind.vReg);
            new ASMLoadInst(mem, a, RV32.BitWidth.w, cur.block);
            return a;
        }
        return (PhysicalReg) reg;
    }

    private PhysicalReg regAllocateWrite(Register reg, PhysicalReg a) {
        if (reg instanceof VirtualReg t) {
            cur.fn.stackRegCnt = Math.max(cur.fn.stackRegCnt, t.id + 1);
            StackOffset mem = new StackOffset(t.id, stackDataKind.vReg);
            new ASMStoreInst(mem, a, RV32.BitWidth.w, cur.block);
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
        rawList.forEach(inst -> inst.accept(this));
    }

    @Override
    public void visit(ASMBrInst inst) {
        inst.condition = regAllocateRead(inst.condition, tmp0);
        cur.block.addInst(inst);
    }

    @Override
    public void visit(ASMCalcInst inst) {
        inst.rs1 = regAllocateRead(inst.rs1, tmp0);
        inst.rs2 = regAllocateRead(inst.rs2, tmp1);
        cur.block.addInst(inst);
        inst.rd = regAllocateWrite(inst.rd, tmp0);
    }

    @Override
    public void visit(ASMLaInst inst) {
        cur.block.addInst(inst);
        inst.rd = regAllocateWrite(inst.rd, tmp0);
    }

    @Override
    public void visit(ASMLiInst inst) {
        cur.block.addInst(inst);
        inst.rd = regAllocateWrite(inst.rd, tmp0);
    }

    @Override
    public void visit(ASMLoadInst inst) {
        inst.addr.reg = regAllocateRead(inst.addr.reg, tmp0);
        cur.block.addInst(inst);
        inst.rd = regAllocateWrite(inst.rd, tmp0);
    }

    @Override
    public void visit(ASMStoreInst inst) {
        inst.rs = regAllocateRead(inst.rs, tmp0);
        inst.addr.reg = regAllocateRead(inst.addr.reg, tmp1);
        cur.block.addInst(inst);
    }

    @Override
    public void visit(ASMMoveInst inst) {
        inst.rs = regAllocateRead(inst.rs, tmp0);
        cur.block.addInst(inst);
        inst.rd = regAllocateWrite(inst.rd, tmp0);
    }

    @Override
    public void visit(ASMRetInst inst) {
        cur.block.addInst(inst);
    }

    @Override
    public void visit(ASMJInst inst) {
        cur.block.addInst(inst);
    }

    @Override
    public void visit(ASMCallInst inst) {
        cur.block.addInst(inst);
    }

}
