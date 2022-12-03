package ASM.ASMOprand;

import IR.IRValue.IRUser.ConsValue.ConsData.StrConst;

public class ASMConstStr {
    public String name, data;
    public int size;

    public ASMConstStr(StrConst irConstStr) {
        this.name = irConstStr.getName();
        this.data = irConstStr.data;
        this.size = irConstStr.derefType.getSize();
    }
}
