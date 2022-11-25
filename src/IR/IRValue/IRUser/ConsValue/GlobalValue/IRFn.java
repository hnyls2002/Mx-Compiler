package IR.IRValue.IRUser.ConsValue.GlobalValue;

import java.util.ArrayList;

import Debug.MyException;
import Frontend.Util.Types.FuncInfo;
import IR.IRType.IRFnType;
import IR.IRValue.IRParameter;
import IR.IRValue.IRBasicBlock;
import IR.Util.Transfer;

public class IRFn extends BaseGlobalValue {
    public ArrayList<IRParameter> paraList = new ArrayList<>();
    public ArrayList<IRBasicBlock> blockList = new ArrayList<>();

    public IRFn(FuncInfo fnInfo) {
        // 1. build the IRFn
        super(Transfer.fnTypeTransfer(fnInfo));
        this.constName = fnInfo.funcName;
        if (fnInfo.inWhichClass != null)// put prefix
            this.constName = fnInfo.inWhichClass.structType.className + '.' + this.constName;

        // 2. add the fnValue to ast fnInfo
        fnInfo.fnValue = this;
    }

    private IRFn(IRFnType fnType, String fnNameString) {
        super(fnType);
        this.constName = fnNameString;
    }

    public static IRFn getInitFn(String initFnNameString) {
        var ret = new IRFn(IRFnType.getVarInitFnType(), initFnNameString);
        return ret;
    }

    public void addBlock(IRBasicBlock block) {
        blockList.add(block);
    }

    @Override
    public String defToString() {
        if (valueType instanceof IRFnType fnType) {
            var ret = "define ";
            ret += fnType.retType.toString() + ' ';
            ret += getName() + '(';
            for (var para : paraList)
                ret += para.defToString() + ", ";
            if (!fnType.paraTypeList.isEmpty())
                ret = ret.substring(0, ret.length() - 2);
            ret += ')';
            return ret;
        } else
            throw new MyException("IRFn should have a FnType");
    }

}
