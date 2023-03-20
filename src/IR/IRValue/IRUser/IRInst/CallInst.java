package IR.IRValue.IRUser.IRInst;

import java.util.ArrayList;

import IR.IRType.IRFnType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import Share.Visitors.IRInstVisitor;

public class CallInst extends IRBaseInst {
    public IRFnType calledFnType = null;

    public CallInst(IRFnType calledFnType, ArrayList<IRBaseValue> argList, IRBasicBlock block) {
        super(calledFnType.retType, block);
        for (int i = 0; i < argList.size(); ++i)
            appendOprand(argList.get(i));
        this.calledFnType = calledFnType;
        block.addInst(this);
    }

    public CallInst(IRFnType calledFnType, IRBasicBlock block, IRBaseValue... argList) {
        super(calledFnType.retType, block);
        this.calledFnType = calledFnType;
        for (var arg : argList)
            appendOprand(arg);
        block.addInst(this);
    }

    @Override
    public String formatDef() {
        var ret = "call " + valueType.formatType() + ' ';
        ret += calledFnType.getFnName() + "(";
        for (int i = 0; i < getOprandNum(); ++i) {
            var arg = getOprand(i);
            ret += arg.formatUseWithType() + ", ";
        }
        if (getOprandNum() != 0)
            ret = ret.substring(0, ret.length() - 2);
        ret += ")";
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }
}
