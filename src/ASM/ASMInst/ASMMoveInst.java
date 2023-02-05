package ASM.ASMInst;

import java.util.HashSet;

import ASM.ASMBlock;
import ASM.ASMOprand.Register;
import Share.Visitors.ASMInstVisitor;

public class ASMMoveInst extends ASMBaseInst {
    public Register rd, rs;

    public ASMMoveInst(Register rd, Register rs, ASMBlock block) {
        this.rd = rd;
        this.rs = rs;
        if (block != null)
            block.addInst(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\tmv\t%s, %s\n", rd.format(), rs.format());
    }

    public HashSet<Register> getDef() {
        HashSet<Register> ret = new HashSet<>();
        ret.add(rd);
        return ret;
    }

    @Override
    public HashSet<Register> getUse() {
        HashSet<Register> ret = new HashSet<>();
        ret.add(rs);
        return ret;
    }

    @Override
    public void replaceDef(Register oldDef, Register newDef) {
        if (rd == oldDef)
            rd = newDef;
    }

    @Override
    public void replaceUse(Register oldUse, Register newUse) {
        if (rs == oldUse)
            rs = newUse;
    }
}
