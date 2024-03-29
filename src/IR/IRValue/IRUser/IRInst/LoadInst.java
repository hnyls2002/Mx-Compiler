package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRPtType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import Share.Visitors.IRInstVisitor;

public class LoadInst extends IRBaseInst {
    public LoadInst(IRBaseValue srcAddr, IRBasicBlock block) {
        super(null, block);
        valueType = ((IRPtType) (srcAddr.valueType)).derefType();
        appendOprand(srcAddr);
        if (block != null)
            block.addInst(this);
    }

    public LoadInst(IRBaseValue srcAddr, IRBasicBlock block, int insertIdx) {
        super(null, block);
        valueType = ((IRPtType) (srcAddr.valueType)).derefType();
        appendOprand(srcAddr);
        block.instList.add(insertIdx, this);
    }

    @Override
    public String formatDef() {
        var ret = "load " + valueType.formatType() + ", ";
        ret += getOprand(0).formatUseWithType();
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public IRBaseInst copy() {
        return new LoadInst(getOprand(0), null);
    }
}
