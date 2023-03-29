package IR.IRValue.IRUser.ConsValue.GlobalValue;

import java.util.ArrayList;

import ASM.ASMFn;
import AST.Info.FuncInfo;
import IR.IRType.IRFnType;
import IR.IRValue.IRParameter;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.Util.Transfer;
import Middleend.IROptimize.Tools.IRLoop;
import Middleend.IROptimize.Tools.CallGraphAnalyzer.CallInfo;
import Share.MyException;

public class IRFn extends BaseGlobalValue {
    public boolean isBuiltIn = false;
    public ArrayList<IRParameter> paraList = new ArrayList<>();
    public ArrayList<IRBasicBlock> blockList = new ArrayList<>();
    public IRBaseValue retValueAddr = null;
    public IRBasicBlock retBlock = null;

    // for loop analysis
    public ArrayList<IRLoop> topLoopList = new ArrayList<>();

    // asm
    public ASMFn asmFn = null;

    // for call graph
    public CallInfo callInfo = new CallInfo();

    public IRFn(FuncInfo fnInfo) {
        // 1. build the IRFn
        super(Transfer.fnTypeTransfer(fnInfo));
        this.nameString = ((IRFnType) valueType).fnNameString;
        fnInfo.irFn = this;
    }

    public IRFn(IRFnType fnType, boolean isBuiltIn) {
        super(fnType);
        this.nameString = fnType.fnNameString;
        this.isBuiltIn = isBuiltIn;
    }

    private IRFn(IRFnType fnType, String fnNameString) {
        super(fnType);
        this.nameString = fnNameString;
    }

    public static IRFn getInitFn(String initFnNameString) {
        var ret = new IRFn(IRFnType.getVarInitFnType(initFnNameString), initFnNameString);
        return ret;
    }

    @Override
    public String formatDef() {
        if (valueType instanceof IRFnType fnType) {
            var ret = "define ";
            ret += fnType.retType.formatType() + ' ';
            ret += '@' + getName() + '(';
            for (var para : paraList)
                ret += para.formatDef() + ", ";
            if (!fnType.paraTypeList.isEmpty())
                ret = ret.substring(0, ret.length() - 2);
            ret += ')';
            return ret;
        } else
            throw new MyException("IRFn should have a FnType");
    }

}
