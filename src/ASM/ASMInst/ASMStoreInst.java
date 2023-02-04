package ASM.ASMInst;

import java.util.HashSet;

import ASM.ASMBlock;
import ASM.ASMOprand.RegOffset;
import ASM.ASMOprand.Register;
import Share.Lang.RV32;
import Share.Visitors.ASMInstVisitor;

public class ASMStoreInst extends ASMBaseInst {
    public RV32.BitWidth bitWidth;
    public RegOffset addr;
    public Register rs;

    public ASMStoreInst(RegOffset addr, Register rs, RV32.BitWidth bitWidth, ASMBlock block) {
        this.bitWidth = bitWidth;
        this.addr = addr;
        this.rs = rs;
        block.addInst(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\ts%s\t%s, %s\n", bitWidth, rs.format(), addr.format());
    }

    @Override
    public HashSet<Register> getDef() {
        return new HashSet<>();
    }

    @Override
    public HashSet<Register> getUse() {
        HashSet<Register> ret = new HashSet<>();
        ret.add(rs);
        ret.add(addr.reg);
        return ret;
    }

    @Override
    public void replaceDef(Register oldDef, Register newDef) {
    }

    @Override
    public void replaceUse(Register oldUse, Register newUse) {
        if (rs == oldUse)
            rs = newUse;
        if (addr.reg == oldUse)
            addr.reg = newUse;
    }

}
