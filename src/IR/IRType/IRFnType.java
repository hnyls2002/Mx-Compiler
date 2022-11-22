package IR.IRType;

import java.util.ArrayList;

// function signatures

public class IRFnType extends IRType {
    public String fnName;
    public IRType retType;
    public ArrayList<IRType> argumentList = new ArrayList<>();
    public IRStructType methodFrom = null;

    public IRFnType(String fnName) {
        super(IRTypeId.FnTypeId);
        this.fnName = fnName;
    }

    @Override
    public String toString() {
        var ret = "";
        ret += retType.toString() + ' ';
        ret += '@' + fnName + '(';
        for (var arg : argumentList)
            ret += arg.toString();
        ret += ')';
        return ret;
    }
}
