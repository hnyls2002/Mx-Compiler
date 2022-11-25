package IR.IRValue.IRUser.Inst;

import IR.IRType.IRPtType;
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
        // stored value can be null
        ret += ((IRPtType) destAddr.valueType).derefType().toString() + ' ' + storedValue.useToString() + ", ";
        ret += destAddr.useToStringWithType();
        return ret;
    }

}
