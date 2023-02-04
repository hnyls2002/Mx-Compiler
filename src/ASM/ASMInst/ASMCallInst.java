package ASM.ASMInst;

import java.util.HashSet;

import ASM.ASMBlock;
import ASM.ASMOprand.Register;
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

    @Override
    public String format() {
        return String.format("\tcall\t%s\n", fnName);
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
