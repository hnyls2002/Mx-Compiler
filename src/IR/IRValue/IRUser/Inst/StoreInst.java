package IR.IRValue.IRUser.Inst;

import IR.IRType.IRVoidType;
import IR.IRValue.IRBaseValue;

public class StoreInst extends IRBaseInst {
    public IRBaseValue storedValue, destAddr;

    public StoreInst(IRBaseValue storedValue, IRBaseValue destAddr) {
        super(new IRVoidType());
        this.storedValue = storedValue;
        this.destAddr = destAddr;
    }

    @Override
    public String defToString() {
        var ret = "store ";
        ret += storedValue.useToString() + ", ";
        ret += destAddr.useToString();
        return ret;
    }

}
