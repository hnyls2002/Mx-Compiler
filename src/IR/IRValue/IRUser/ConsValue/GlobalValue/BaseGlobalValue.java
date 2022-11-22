package IR.IRValue.IRUser.ConsValue.GlobalValue;

import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRUser.ConsValue.BaseConstValue;

public abstract class BaseGlobalValue extends BaseConstValue {
    public BaseGlobalValue(IRType globalValueType) {
        super(globalValueType);
    }
}
