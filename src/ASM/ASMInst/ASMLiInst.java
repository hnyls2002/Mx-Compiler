package ASM.ASMInst;

import java.util.HashSet;

import ASM.ASMBlock;
import ASM.ASMOprand.Register;
import Share.Visitors.ASMInstVisitor;

public class ASMLiInst extends ASMBaseInst {
    public Register rd;
    public int imm;

    public ASMLiInst(Register rd, int imm, ASMBlock block) {
        this.rd = rd;
        this.imm = imm;
        block.addInst(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\tli\t%s, %d\n", rd.format(), imm);
    }

    public HashSet<Register> getDef() {
        HashSet<Register> ret = new HashSet<>();
        ret.add(rd);
        return ret;
    }

    @Override
    public HashSet<Register> getUse() {
        return new HashSet<>();
    }

    @Override
    public void replaceDef(Register oldDef, Register newDef) {
        if (rd == oldDef)
            rd = newDef;
    }

    @Override
    public void replaceUse(Register oldUse, Register newUse) {
    }
}
