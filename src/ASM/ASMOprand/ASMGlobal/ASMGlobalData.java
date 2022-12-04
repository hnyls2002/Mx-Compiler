package ASM.ASMOprand.ASMGlobal;

import ASM.ASMOprand.BaseOprand;

public abstract class ASMGlobalData extends BaseOprand {
    public String name;
    public int size;
    // dereference type
    public boolean isContainsPtr;

    public ASMGlobalData(String name, int size, boolean isContainsPtr) {
        this.name = name;
        this.size = size;
        this.isContainsPtr = isContainsPtr;
    }

}
