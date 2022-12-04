package ASM.ASMInst;

import ASM.ASMBlock;
import ASM.ASMOprand.Register;
import Share.Visitors.ASMInstVisitor;

public class ASMBrInst extends ASMBaseInst {
    public Register condition;
    public ASMBlock trueBlock;

    public ASMBrInst(Register condition, ASMBlock trueBlock, ASMBlock curBlock) {
        this.condition = condition;
        this.trueBlock = trueBlock;
        curBlock.addInst(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\tbeqz\t%s, %s\n", condition.format(), trueBlock.format());
    }

}
