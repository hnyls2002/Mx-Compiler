package ASM.ASMOprand.ASMGlobal;

import IR.IRType.IRPtType;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;
import Share.MyException;

public class ASMGlobalVar extends ASMGlobalData {
    public int data;

    public ASMGlobalVar(GlobalVariable gVar) {
        // global ? int, bool, struct, [] ----> all four bytes
        super(gVar.getName(), 4, gVar.derefType instanceof IRPtType);

        if (gVar.initValue instanceof NullConst)
            this.data = 0;
        else if (gVar.initValue instanceof IntConst t)
            this.data = t.constValue;
        else
            throw new MyException("default value should be int");
    }

    @Override
    public String format() {
        return name;
    }
}
