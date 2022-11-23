package IR.IRValue.IRUser.ConsValue.GlobalValue;

import Debug.MyException;
import Frontend.Util.TypeName;
import IR.IRType.IRPtType;
import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRUser.ConsValue.ConsData.BaseConstData;
import IR.IRValue.IRUser.ConsValue.ConsData.StrConst;
import IR.Util.Transfer;

// global variable is address!!!!

public class GlobalVariable extends BaseGlobalValue {
    public String varNameString;
    public boolean isInit = false;
    public IRBaseValue initData = null;

    // varType in , addr type built
    public GlobalVariable(String varNameString, TypeName gVarTypeName) { // from typename
        super(new IRPtType(Transfer.typeTransfer(gVarTypeName), 1));
        this.varNameString = varNameString;
    }

    public GlobalVariable(String varNameString, IRType gVarType) { // from ir type
        super(new IRPtType(gVarType, 1));
        this.varNameString = varNameString;
    }

    @Override
    public String toString() {
        var ret = '@' + varNameString;
        ret += " = global ";
        if (valueType instanceof IRPtType tmp) {
            ret += tmp.derefType().toString() + ' ';
            if (!isInit)// use default value
                ret += tmp.derefType().defaultValue().toString();
            else if (initData instanceof BaseConstData constData) {
                if (constData instanceof StrConst constStr) { // strconst should define a global str first
                    // TODO
                } else { // constInt or constNull
                    ret += constData.toString();
                }
            }
        } else
            throw new MyException("global var should be a pointer type");
        return ret;
    }
}
