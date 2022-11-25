package IR.IRValue.IRUser.Inst;

import java.util.ArrayList;

import IR.IRType.IRFnType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;

public class CallInst extends IRBaseInst {
    public ArrayList<IRBaseValue> argList = null;
    public IRFnType calledFnType = null;

    public CallInst(IRFnType calledFnType, ArrayList<IRBaseValue> argList, IRBasicBlock block) {
        super(calledFnType.retType);
        this.argList = argList;
        this.calledFnType = calledFnType;
        block.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = "call " + valueType.toString() + ' ';
        ret += calledFnType.getFnName() + "(";
        for (var arg : argList)
            ret += arg.useToStringWithType() + ", ";
        if (!argList.isEmpty())
            ret = ret.substring(0, ret.length() - 2);
        ret += ")";
        return ret;
    }
}
