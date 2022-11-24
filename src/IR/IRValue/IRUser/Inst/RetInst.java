package IR.IRValue.IRUser.Inst;

import IR.IRType.IRVoidType;
import IR.IRValue.IRBaseValue;

public class RetInst extends IRBaseInst {
    public IRBaseValue retValue;
    private boolean isVoid = false;

    public boolean isVoid() {
        return isVoid;
    }

    // "ret %3" is void type ?
    public RetInst(IRBaseValue retValue) {
        super(new IRVoidType());
        this.retValue = retValue;
    }

    public static RetInst createVoidRetInst() {
        var ret = new RetInst(null);
        ret.isVoid = true;
        return ret;
    }

    @Override
    public String defToString() {
        if (isVoid())
            return "ret void";
        return "ret" + ' ' + retValue.useToStringWithType();
    }

}
