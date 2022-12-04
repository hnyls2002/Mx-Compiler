package ASM.ASMInst;

import ASM.ASMBlock;

public class ASMJInst extends ASMBaseInst {
    ASMBlock jumpBlock;

    public ASMJInst(ASMBlock jumpBlock,ASMBlock curBlock) {
        this.jumpBlock = jumpBlock;
        curBlock.addInst(this);
    }
}
