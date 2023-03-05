package ASM.ASMInst;

import ASM.ASMBlock;
import ASM.ASMOprand.Register;
import Share.Visitors.ASMInstVisitor;

public class ASMBrInst extends ASMBaseInst {
    // the condition is rs1
    public ASMBlock trueBlock;

    public ASMBrInst(Register condition, ASMBlock trueBlock, ASMBlock curBlock) {
        this.rs1 = condition;
        this.trueBlock = trueBlock;
        curBlock.instList.add(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\tbnez\t%s, %s\n", rs1.format(), trueBlock.format());
    }
}
