package ASM.ASMOprand;

import ASM.ASMFn;

public class VirtualReg extends Register {
    public int id;

    public VirtualReg(ASMFn asmFn) {
        this.id = asmFn.virRegCnt++;
    }

    @Override
    public String format() {
        return "v" + id;
    }
}
