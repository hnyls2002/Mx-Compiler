package IR.IRValue.IRUser.ConsValue;

import Debug.MyException;
import IR.IRType.IRType;
import IR.IRValue.IRUser.IRBaseUser;

public abstract class BaseConstValue extends IRBaseUser {
    public String constName;

    public BaseConstValue(IRType consValueType) {
        super(consValueType);
    }

    @Override
    public String getName() {
        throw new MyException("constName doesn't exist!");
    }
}
