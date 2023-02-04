package ASM.ASMInst;

import java.util.HashSet;

import ASM.ASMOprand.Register;
import Share.Visitors.ASMInstVisitor;

public abstract class ASMBaseInst {
    public abstract void accept(ASMInstVisitor visitor);

    public abstract String format();

    public abstract HashSet<Register> getDef();

    public abstract HashSet<Register> getUse();

    public abstract void replaceDef(Register oldDef, Register newDef);

    public abstract void replaceUse(Register oldUse, Register newUse);
}
