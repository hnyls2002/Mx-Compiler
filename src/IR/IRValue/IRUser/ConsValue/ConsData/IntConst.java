package IR.IRValue.IRUser.ConsValue.ConsData;

import IR.IRType.IRIntType;

public class IntConst extends BaseConstData {
    public int constValue;

    public IntConst(int ConsValue, IRIntType consIntType) {
        super(consIntType);
        this.constValue = ConsValue;
    }

    @Override
    public String toString() {
        return valueType.toString() + ' ' + constValue;
    }
}
