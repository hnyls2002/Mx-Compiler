package ASM.ASMInst;

import java.util.HashSet;

import ASM.ASMBlock;
import ASM.ASMOprand.Register;
import Share.Visitors.ASMInstVisitor;

public class ASMRetInst extends ASMBaseInst {
    public ASMRetInst(ASMBlock block) {
        block.addInst(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return "\tret\n";
    }

    @Override
    public HashSet<Register> getDef() {
        return new HashSet<>();
    }

    @Override
    public HashSet<Register> getUse() {
        return new HashSet<>();
    }

    @Override
    public void replaceDef(Register oldDef, Register newDef) {
    }

    @Override
    public void replaceUse(Register oldUse, Register newUse) {
    }
}
