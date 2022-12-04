package ASM.ASMInst;

import ASM.ASMBlock;

public class ASMRetInst extends ASMBaseInst {
    public ASMRetInst(ASMBlock block) {
        block.addInst(this);
    }
}
