package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRType;
import IR.IRValue.IRUser.IRBaseUser;

/// an instruction's type is its returntype
/// add, load... such instructions have a return value
/// store is void type!

public abstract class IRBaseInst extends IRBaseUser {
    public IRBaseInst(IRType insType) {
        super(insType);
    }

    @Override
    public String useToString() {
        return '%' + getName();
    }
}
