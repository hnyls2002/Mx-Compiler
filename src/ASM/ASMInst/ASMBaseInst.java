package ASM.ASMInst;

import Share.Visitors.ASMInstVisitor;

public abstract class ASMBaseInst {
    public abstract void accept(ASMInstVisitor visitor);

    public abstract String format();
}
