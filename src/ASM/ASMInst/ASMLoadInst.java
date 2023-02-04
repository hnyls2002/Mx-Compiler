package ASM.ASMInst;

import java.util.HashSet;

import ASM.ASMBlock;
import ASM.ASMOprand.RegOffset;
import ASM.ASMOprand.Register;
import Share.Lang.RV32;
import Share.Visitors.ASMInstVisitor;

public class ASMLoadInst extends ASMBaseInst {
    public RV32.BitWidth bitWidth;
    public RegOffset addr;
    public Register rd;

    public ASMLoadInst(RegOffset addr, Register rd, RV32.BitWidth bitWidth, ASMBlock block) {
        this.bitWidth = bitWidth;
        this.addr = addr;
        this.rd = rd;
        block.addInst(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\tl%s\t%s, %s\n", bitWidth, rd.format(), addr.format());
    }

    public HashSet<Register> getDef() {
        HashSet<Register> ret = new HashSet<>();
        ret.add(rd);
        return ret;
    }

    @Override
    public HashSet<Register> getUse() {
        HashSet<Register> ret = new HashSet<>();
        ret.add(addr.reg);
        return ret;
    }

    @Override
    public void replaceDef(Register oldDef, Register newDef) {
        if (rd == oldDef)
            rd = newDef;
    }

    @Override
    public void replaceUse(Register oldUse, Register newUse) {
        if (addr.reg == oldUse)
            addr.reg = newUse;
    }
}
