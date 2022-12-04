package ASM.ASMInst;

import ASM.ASMBlock;
import Share.Visitors.ASMInstVisitor;

public class ASMCallInst extends ASMBaseInst {

    public String fnName;

    public ASMCallInst(String fnName, ASMBlock block) {
        this.fnName = fnName;
        block.addInst(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }
}
