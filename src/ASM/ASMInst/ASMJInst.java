package ASM.ASMInst;

import ASM.ASMBlock;
import Share.Visitors.ASMInstVisitor;

public class ASMJInst extends ASMBaseInst {
    ASMBlock jumpBlock;

    public ASMJInst(ASMBlock jumpBlock, ASMBlock block) {
        this.jumpBlock = jumpBlock;
        block.instList.add(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\tj\t%s\n", jumpBlock.format());
    }
}
