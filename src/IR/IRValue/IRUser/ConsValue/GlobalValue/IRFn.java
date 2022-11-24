package IR.IRValue.IRUser.ConsValue.GlobalValue;

import java.util.ArrayList;

import Debug.MyException;
import IR.IRType.IRFnType;
import IR.IRValue.IRArgument;
import IR.IRValue.IRBasicBlock;

public class IRFn extends BaseGlobalValue {
    public String fnNameString;
    public ArrayList<IRArgument> argList = new ArrayList<>();
    public ArrayList<IRBasicBlock> blockList = new ArrayList<>();

    public IRFn(String fnNamString, IRFnType fnType) {
        super(fnType);
        this.fnNameString = fnNamString;
    }

    public void addBlock(IRBasicBlock block) {
        blockList.add(block);
    }

    @Override
    public String defToString() {
        if (valueType instanceof IRFnType fnType) {
            var ret = "define ";
            ret += fnType.retType.toString() + ' ';
            ret += '@' + fnNameString + '(';
            for (var arg : argList)
                ret += arg.defToString() + ", ";
            if (!fnType.argumentList.isEmpty())
                ret = ret.substring(0, ret.length() - 2);
            ret += ')';
            return ret;
        } else
            throw new MyException("IRFn should have a FnType");
    }

}
