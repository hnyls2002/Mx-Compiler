package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRPtType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import Share.Visitors.IRInstVisitor;

public class LoadInst extends IRBaseInst {
    public LoadInst(IRBaseValue srcAddr, IRBasicBlock block) {
        super(null);
        valueType = ((IRPtType) (srcAddr.valueType)).derefType();
        appendOprand(srcAddr);
        block.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = "load " + valueType.toString() + ", ";
        ret += getOprand(0).useToStringWithType();
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

}
