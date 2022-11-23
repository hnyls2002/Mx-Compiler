package IR.IRValue;

import IR.IRType.IRType;

public abstract class IRBaseValue {
    public IRType valueType;

    public IRBaseValue(IRType valueType) {
        this.valueType = valueType;
    }

    public String toStringWithType() {
        return "![this is created by a abstract class IRBaseValue]";
    }

}
