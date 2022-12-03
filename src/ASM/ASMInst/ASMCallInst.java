package ASM.ASMInst;

import ASM.ASMBlock;

public class ASMCallInst extends ASMBaseInst {

    public String fnName;

    public ASMCallInst(String fnName, ASMBlock block) {
        this.fnName = fnName;
        block.addInst(this);
    }
}
