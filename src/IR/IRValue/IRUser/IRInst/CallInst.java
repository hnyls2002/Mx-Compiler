package IR.IRValue.IRUser.IRInst;

import java.util.ArrayList;

import IR.IRType.IRFnType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import Share.Visitors.IRInstVisitor;

public class CallInst extends IRBaseInst {
    public IRFn callee = null;

    public CallInst(IRFn callee, ArrayList<IRBaseValue> argList, IRBasicBlock block) {
        super(((IRFnType) callee.valueType).retType, block);
        this.callee = callee;
        for (int i = 0; i < argList.size(); ++i)
            appendOprand(argList.get(i));
        if (block != null)
            block.addInst(this);
    }

    public CallInst(IRFn callee, IRBasicBlock block, IRBaseValue... argList) {
        super(((IRFnType) callee.valueType).retType, block);
        this.callee = callee;
        for (var arg : argList)
            appendOprand(arg);
        if (block != null)
            block.addInst(this);
    }

    @Override
    public String formatDef() {
        var ret = "call " + valueType.formatType() + ' ';
        ret += '@' + callee.getName() + "(";
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

    @Override
    public IRBaseInst copy() {
        var argList = new ArrayList<IRBaseValue>();
        for (int i = 0; i < getOprandNum(); ++i)
            argList.add(getOprand(i));
        return new CallInst(callee, argList, null);
    }
}
