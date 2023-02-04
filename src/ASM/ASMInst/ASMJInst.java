package ASM.ASMInst;

import java.util.HashSet;

import ASM.ASMBlock;
import ASM.ASMOprand.Register;
import Share.Visitors.ASMInstVisitor;

public class ASMJInst extends ASMBaseInst {
    public ASMBlock jumpBlock;

    public ASMJInst(ASMBlock jumpBlock, ASMBlock curBlock) {
        this.jumpBlock = jumpBlock;
        curBlock.addInst(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\tj\t%s\n", jumpBlock.format());
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
