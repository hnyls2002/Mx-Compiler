package IR.IRType;

import java.util.ArrayList;

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

    public IRFnType(String fnNameString, IRType retType, IRType... ParaList) {
        super(IRTypeId.FnTypeId);
        this.fnNameString = fnNameString;
        this.retType = retType;
        for (var para : ParaList)
            this.paraTypeList.add(para);
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
        var ret = "declare " + retType.toString() + ' ' + getFnName() + '(';
        for (var para : paraTypeList)
            ret += para.toString() + ", ";
        if (!paraTypeList.isEmpty())
            ret = ret.substring(0, ret.length() - 2);
        ret += ')';
        return ret;
    }
}
