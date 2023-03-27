package IR.IRValue.IRUser.ConsValue.GlobalValue;

import java.util.ArrayList;

import ASM.ASMFn;
import AST.Info.FuncInfo;
import IR.IRType.IRFnType;
import IR.IRValue.IRParameter;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.Util.Transfer;
import Middleend.IROptimize.Tools.LoopAnalyzer.Loop;
import Share.MyException;

public class IRFn extends BaseGlobalValue {
    public ArrayList<IRParameter> paraList = new ArrayList<>();
    public ArrayList<IRBasicBlock> blockList = new ArrayList<>();
    public IRBaseValue retValueAddr = null;
    public IRBasicBlock retBlock = null;

    // for loop analysis
    public ArrayList<Loop> topLoopList = new ArrayList<>();

    // asm
    public ASMFn asmFn = null;

    public IRFn(FuncInfo fnInfo) {
        // 1. build the IRFn
        super(Transfer.fnTypeTransfer(fnInfo));
        this.nameString = fnInfo.fnType.fnNameString;
        fnInfo.fnType = (IRFnType) this.valueType;
    }

    public IRFn(IRFnType constructorType) {
        super(constructorType);
        this.nameString = constructorType.fnNameString;
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
