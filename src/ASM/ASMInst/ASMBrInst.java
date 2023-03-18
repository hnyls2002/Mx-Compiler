package ASM.ASMInst;

import ASM.ASMBlock;
import ASM.ASMOprand.Register;
import Share.Lang.RV32.BranchOp;
import Share.Visitors.ASMInstVisitor;

public class ASMBrInst extends ASMBaseInst {
    public ASMBlock trueBlock;
    public BranchOp opCode;

    public ASMBrInst(BranchOp opCode, Register rs1, Register rs2, ASMBlock trueBlock, ASMBlock curBlock) {
        this.opCode = opCode;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.trueBlock = trueBlock;
        curBlock.sucList.add(trueBlock);
        trueBlock.preList.add(curBlock);
        curBlock.instList.add(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\t%s\t%s, %s, %s\n", opCode.format(), rs1.format(), rs2.format(), trueBlock.format());
    }
}
