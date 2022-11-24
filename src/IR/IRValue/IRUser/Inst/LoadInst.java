package IR.IRValue.IRUser.Inst;

import IR.IRType.IRPtType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;

public class LoadInst extends IRBaseInst {
    public IRBaseValue srcAddr;

    public LoadInst(IRBaseValue srcAddr, IRBasicBlock block) {
        super(null);
        valueType = ((IRPtType) (srcAddr.valueType)).derefType();
        this.srcAddr = srcAddr;
        block.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = "load " + valueType.toString() + ", ";
        ret += srcAddr.useToStringWithType();
        return ret;
    }

}
