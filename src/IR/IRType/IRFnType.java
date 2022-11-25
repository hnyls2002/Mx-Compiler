package IR.IRType;

import java.util.ArrayList;

import Debug.MyException;

// function signatures

public class IRFnType extends IRType {
    public IRType retType;
    public ArrayList<IRType> paraTypeList = new ArrayList<>();
    public IRStructType methodFrom = null;
    public boolean isVarInit = false;
    public String fnNameString = null;

    public IRFnType(String fnNamString) {
        super(IRTypeId.FnTypeId);
        this.fnNameString = fnNamString;
    }

    public static IRFnType getVarInitFnType(String initFnNameString) {
        var ret = new IRFnType(initFnNameString);
        ret.retType = new IRVoidType();
        ret.isVarInit = true;
        return ret;
    }

    public String getFnName() {
        return '@' + fnNameString;
    }

    @Override
    public String toString() {
        throw new MyException("You don't need to use the FnType toString() method");
    }
}
