package IR.IRValue;

import IR.IRType.IRType;

public class IRArgument extends IRBaseValue {

    public String argumentName;

    public IRArgument(IRType argumentType) {
        super(argumentType);
    }

    @Override
    public String getName() {
        return "%" + (argumentName == null ? "arg" : argumentName);
    }

    @Override
    public String defToString() {
        return valueType.toString() + ' ' + getName();
    }
}
