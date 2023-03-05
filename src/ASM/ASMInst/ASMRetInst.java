package ASM.ASMInst;

import ASM.ASMBlock;
import Share.Visitors.ASMInstVisitor;

public class ASMRetInst extends ASMBaseInst {
    public ASMRetInst(ASMBlock block) {
        block.instList.add(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return "\tret\n";
    }
}
