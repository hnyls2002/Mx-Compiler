package IR.IRValue.IRUser.Inst;

import java.util.ArrayList;

import IR.IRType.IRFnType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;

public class CallInst extends IRBaseInst {
    public ArrayList<IRBaseValue> argList = null;
    public IRFn calledFn = null;

    public CallInst(IRFn calledFn, ArrayList<IRBaseValue> argList, IRBasicBlock block) {
        super(((IRFnType) calledFn.valueType).retType);
        this.argList = argList;
        this.calledFn = calledFn;
        block.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = "call " + valueType.toString() + ' ';
        ret += calledFn.getName() + "(";
        for (var arg : argList)
            ret += arg.useToStringWithType() + ", ";
        if (!argList.isEmpty())
            ret = ret.substring(0, ret.length() - 2);
        ret += ")";
        return ret;
    }
}
