package ASM.ASMGlobal;

import IR.IRType.IRArrayType;
import IR.IRValue.IRUser.ConsValue.ConsData.StrConst;

public class ASMConstStr {
    public String name, data;
    public int size;

    public ASMConstStr(StrConst irConstStr) {
        this.name = irConstStr.getName();
        this.data = irConstStr.data;
        this.size = ((IRArrayType) irConstStr.derefType).arrayLen;
    }
}
