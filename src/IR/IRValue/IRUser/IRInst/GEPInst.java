package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRPtType;
import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import Share.Visitors.IRInstVisitor;

public class GEPInst extends IRBaseInst {
    public IRType startType;

    // start pointer 0
    // indices 1 ~ n

    public GEPInst(IRBaseValue startPtr, IRType gepInstType, IRBasicBlock block, IRBaseValue... idxList) {
        super(gepInstType);
        appendOprand(startPtr);
        this.startType = ((IRPtType) startPtr.valueType).derefType();
        for (var idx : idxList)
            appendOprand(idx);
        if (block != null)
            block.addInst(this);
    }

    public void addIdx(int idx) {
        // i64 or i32 ? -> i32
        appendOprand(new IntConst(idx, 32));
    }

    public IRBaseValue getIdx(int pos) {
        return getOprand(pos + 1);
    }

    @Override
    public String formatDef() {
        var ret = "getelementptr " + startType.formatType() + ", ";
        ret += getOprand(0).valueType.formatType() + ' ' + getOprand(0).formatUse();
        for (int i = 1; i < getOprandNum(); ++i)
            ret += ", " + getOprand(i).formatUseWithType();
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

}
