package IR.IRValue;

import ASM.ASMOprand.BaseOprand;
import IR.IRType.IRType;
import Share.MyException;

public abstract class IRBaseValue {
    public IRType valueType;
    protected String nameString;

    // for assembly
    public BaseOprand oprand;

    public IRBaseValue(IRType valueType) {
        this.valueType = valueType;
    }

    @Override
    public String toString() {
        throw new MyException("Value cannot toString");
    }

    public final String getName() {
        if (nameString == null)
            throw new MyException("NullName");
        return nameString;
    }

    public final void setName(String nameString) {
        this.nameString = nameString;
    }

    // we don't present the name, name was handled during IRPrinter
    // def means when the value is created
    public abstract String defToString();

    public abstract String useToString();

    public String useToStringWithType() {
        return valueType.toString() + ' ' + useToString();
    }

}
