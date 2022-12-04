package ASM.ASMOprand.ASMGlobal;

import IR.IRType.IRArrayType;
import IR.IRValue.IRUser.ConsValue.ConsData.StrConst;

public class ASMConstStr extends ASMGlobalData {
    public String data;

    public ASMConstStr(StrConst irConstStr) {
        super(irConstStr.getName(), ((IRArrayType) irConstStr.derefType).arrayLen, false);
        this.data = irConstStr.data;
    }
}
