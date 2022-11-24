package IR.IRValue;

import IR.IRType.IRType;

public abstract class IRBaseValue {
    public IRType valueType;

    public IRBaseValue(IRType valueType) {
        this.valueType = valueType;
    }

    @Override
    public String toString() {
        return "an abstract base value";
    }

    public String getName() {
        return "I don't have a name (⌣̩̩́_⌣̩̩̀)";
    }

    // we don't present the name, name was handled during IRPrinter
    // def means when the value is created
    public String defToString() {
        return "This is a unhandled def";
    }

    public String useToString() {
        return getName();
    }

    public String useToStringWithType() {
        return valueType.toString() + ' ' + getName();
    }

}
