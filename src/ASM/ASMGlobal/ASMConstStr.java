package ASM.ASMGlobal;

import IR.IRValue.IRUser.ConsValue.ConsData.StrConst;

public class ASMConstStr {
    public String name, data;
    public int size;

    public ASMConstStr(StrConst irConstStr) {
        this.name = irConstStr.getName();
    }
}
