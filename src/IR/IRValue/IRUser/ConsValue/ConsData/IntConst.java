package IR.IRValue.IRUser.ConsValue.ConsData;

import IR.IRType.IRIntType;

public class IntConst extends BaseConstData {
    public int constValue;

    public IntConst(int constValue, int intLen) {
        super(new IRIntType(intLen));
        this.constValue = constValue;
    }

    @Override
    public String useToString() {
        return valueType.toString() + ' ' + constValue;
    }

}
