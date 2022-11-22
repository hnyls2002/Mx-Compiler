package IR.IRValue.IRUser.ConsValue.GlobalValue;

import IR.IRType.IRType;
import IR.IRValue.IRUser.ConsValue.BaseConstValue;

// global variable is address!!!!

public class GlobalVariable extends BaseGlobalValue {
    public BaseConstValue initData;
    public boolean isInit;

    public GlobalVariable(IRType gVarType, Boolean initFlag, BaseConstValue initData) {
        super(gVarType);
        this.initData = initData;
        this.isInit = initFlag;
    }

}
