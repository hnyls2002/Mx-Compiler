package IR.IRValue.IRUser.ConsValue;

import IR.IRType.IRType;
import IR.IRValue.IRUser.IRBaseUser;
import Share.MyException;

public abstract class BaseConstValue extends IRBaseUser {
    public String constName;

    public BaseConstValue(IRType consValueType) {
        super(consValueType);
    }

    @Override
    public String getName() {
        if (constName == null)
            throw new MyException("const name is null");
        return '@' + constName;
    }

}
