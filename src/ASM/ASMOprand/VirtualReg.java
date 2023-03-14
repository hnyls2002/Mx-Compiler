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

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof VirtualReg t && t.id == id;
    }
}
