package IR.IRValue.IRUser.ConsValue.ConsData;

import IR.IRType.IRArrayType;
import IR.IRType.IRIntType;

public class StrConst extends BaseConstData {
    public String strValue;

    public int constStrIdx;

    public StrConst(String litString, int len, int idx) {
        super(new IRArrayType(new IRIntType(8), len + 1)); // "hello world" -> "hello world\n"
        this.strValue = litString;
        this.constStrIdx = idx;
    }

    @Override
    public String toString() {
        var ret = "@.str." + constStrIdx;
        ret += " = ";
        ret += valueType.toString() + ' ' + 'c' + "\"" + strValue + "\\00" + "\"";
        return ret;
    }
}
