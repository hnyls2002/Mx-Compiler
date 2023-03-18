package IR.IRValue.IRUser.ConsValue.ConsData;

import IR.IRType.IRType;
import IR.IRValue.IRUser.ConsValue.BaseConstValue;
import Share.MyException;

public abstract class BaseConstData extends BaseConstValue {
    public BaseConstData(IRType constDataType) {
        super(constDataType);
    }

    @Override
    public String formatDef() {
        throw new MyException("int and null have no def");
    }
}
