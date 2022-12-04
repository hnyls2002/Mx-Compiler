package ASM.ASMInst;

import ASM.ASMBlock;
import Share.Visitors.ASMInstVisitor;

public class ASMJInst extends ASMBaseInst {
    ASMBlock jumpBlock;

    public ASMJInst(ASMBlock jumpBlock,ASMBlock curBlock) {
        this.jumpBlock = jumpBlock;
        curBlock.addInst(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }
}
