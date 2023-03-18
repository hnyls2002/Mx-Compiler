package IR.IRType;

import IR.IRValue.IRBaseValue;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;

public class IRIntType extends IRType {

    public final int intLen;

    public IRIntType(int intLen) {
        super(IRTypeId.IntTypeId);
        this.intLen = intLen;
    }

    @Override
    public String formatType() {
        return "i" + intLen;
    }

    @Override
    public IRBaseValue defaultValue() {
        return new IntConst(0, intLen);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IRIntType t && t.intLen == intLen)
            return true;
        return false;
    }
}
