package ASM.ASMInst;

import java.util.HashSet;

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
        return String.format("\tbnez\t%s, %s\n", condition.format(), trueBlock.format());
    }

    @Override
    public HashSet<Register> getDef() {
        return new HashSet<>();
    }

    @Override
    public HashSet<Register> getUse() {
        HashSet<Register> ret = new HashSet<>();
        ret.add(condition);
        return ret;
    }

    @Override
    public void replaceDef(Register oldDef, Register newDef) {
    }

    @Override
    public void replaceUse(Register oldUse, Register newUse) {
        if (condition == oldUse)
            condition = newUse;
    }
}
