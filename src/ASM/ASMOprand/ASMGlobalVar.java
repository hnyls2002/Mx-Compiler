package ASM.ASMOprand;

import IR.IRType.IRPtType;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;
import Share.MyException;

public class ASMGlobalVar {
    public String name;
    public int data, size;
    public boolean isPtr = false;

    public ASMGlobalVar(GlobalVariable gVar) {
        this.name = gVar.getName();
        this.isPtr = gVar.derefType instanceof IRPtType;
        this.size = gVar.derefType.getSize() / (this.isPtr ? 2 : 1);

        if (gVar.initValue instanceof NullConst)
            this.data = 0;
        else if (gVar.initValue instanceof IntConst t)
            this.data = t.constValue;
        else
            throw new MyException("default value should be int");

    }

}
