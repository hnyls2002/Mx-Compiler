package IR.IRValue;

import IR.IRType.IRType;

public class IRParameter extends IRBaseValue {
    // The IRParameter represents a function's formal argument. When compiled from
    // IR to assembly, it just links to the real argument's register (?). However
    // inside a function, the parameter needs a space to be stored

    public String parameterName;
    public IRBaseValue storedAddr;

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
