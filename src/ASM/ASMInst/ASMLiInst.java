package ASM.ASMInst;

import ASM.ASMBlock;
import ASM.ASMOprand.Register;

public class ASMLiInst extends ASMBaseInst {
    public Register rd;
    public int imm;

    public ASMLiInst(Register rd, int imm, ASMBlock block) {
        this.rd = rd;
        this.imm = imm;
        block.addInst(this);
    }
}
