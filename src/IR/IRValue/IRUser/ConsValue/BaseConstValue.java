package IR.IRValue.IRUser.ConsValue;

import IR.IRType.IRType;
import IR.IRValue.IRUser.IRBaseUser;

public abstract class BaseConstValue extends IRBaseUser {
    public BaseConstValue(IRType consValueType) {
        super(consValueType);
    }

    @Override
    public String formatUse() {
        return '@' + getName();
    }
}
