package ASM.ASMInst;

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

}
