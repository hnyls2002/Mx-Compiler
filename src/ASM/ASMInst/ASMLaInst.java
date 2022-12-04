package ASM.ASMInst;

import ASM.ASMBlock;
import ASM.ASMOprand.Register;
import ASM.ASMOprand.ASMGlobal.ASMGlobalData;

public class ASMLaInst extends ASMBaseInst {
    public Register rd;
    public ASMGlobalData symbol;

    public ASMLaInst(Register rd, ASMGlobalData symbol, ASMBlock block) {
        this.rd = rd;
        this.symbol = symbol;
        block.addInst(this);
    }
}
