package IR.IRValue.IRUser.IRInst;

import java.util.ArrayList;

import IR.IRType.IRFnType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import Share.Visitors.IRInstVisitor;

public class CallInst extends IRBaseInst {
    public ArrayList<IRBaseValue> argList = new ArrayList<>();
    public IRFnType calledFnType = null;

    public CallInst(IRFnType calledFnType, ArrayList<IRBaseValue> argList, IRBasicBlock block) {
        super(calledFnType.retType);
        this.argList = argList;
        this.calledFnType = calledFnType;
        block.addInst(this);
    }

    public CallInst(IRFnType calledFnType, IRBasicBlock block, IRBaseValue... argList) {
        super(calledFnType.retType);
        this.calledFnType = calledFnType;
        for (var arg : argList)
            this.argList.add(arg);
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

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }
}
