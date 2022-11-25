package IR.IRValue;

import IR.IRType.IRType;

public class IRParameter extends IRBaseValue {

    public String parameterName;

    public IRParameter(IRType parameterType) {
        super(parameterType);
    }

    @Override
    public String getName() {
        return "%" + (parameterName == null ? "para" : parameterName);
    }

    @Override
    public String defToString() {
        return valueType.toString() + ' ' + getName();
    }
}
