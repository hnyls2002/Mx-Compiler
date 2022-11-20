package IR.IRValue.IRUser.ConsValue.GlobalValue;

import IR.IRType.IRBaseType;
import IR.IRValue.IRUser.ConsValue.BaseConstValue;

public class GlobalVariable extends BaseGlobalValue {
    public IRBaseType ty;
    public BaseConstValue initData;

    public GlobalVariable(IRBaseType ty, BaseConstValue initData) {
        this.ty = ty;
        this.initData = initData;
    }

}
