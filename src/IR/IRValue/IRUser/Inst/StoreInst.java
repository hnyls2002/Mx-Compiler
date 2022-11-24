package IR.IRValue.IRUser.Inst;

import IR.IRType.IRVoidType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;

public class StoreInst extends IRBaseInst {
    public IRBaseValue storedValue, destAddr;

    public StoreInst(IRBaseValue storedValue, IRBaseValue destAddr, IRBasicBlock block) {
        super(new IRVoidType());
        this.storedValue = storedValue;
        this.destAddr = destAddr;
        block.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = "store ";
        ret += storedValue.useToStringWithType() + ", ";
        ret += destAddr.useToStringWithType();
        return ret;
    }

}
