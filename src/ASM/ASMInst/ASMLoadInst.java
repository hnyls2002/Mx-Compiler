package ASM.ASMInst;

import ASM.ASMBlock;
import ASM.ASMOprand.RegOffset;
import ASM.ASMOprand.Register;
import Share.Lang.RV32;

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
}
