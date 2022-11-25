package IR.IRType;

import java.util.ArrayList;

import Debug.MyException;

// function signatures

public class IRFnType extends IRType {
    public IRType retType;
    public ArrayList<IRType> paraTypeList = new ArrayList<>();
    public IRStructType methodFrom = null;
    public boolean isVarInit = false;

    public IRFnType() {
        super(IRTypeId.FnTypeId);
    }

    public static IRFnType getVarInitFnType() {
        var ret = new IRFnType();
        ret.retType = new IRVoidType();
        ret.isVarInit = true;
        return ret;
    }

    @Override
    public String toString() {
        throw new MyException("You don't need to use the FnType toString() method");
    }
}
