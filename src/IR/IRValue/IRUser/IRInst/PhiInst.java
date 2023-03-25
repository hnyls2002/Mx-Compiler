package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import Share.Visitors.IRInstVisitor;

public class PhiInst extends IRBaseInst {

    public AllocaInst belongAlloca = null;

    public PhiInst(IRType valuType, IRBasicBlock curBlock) {
        super(valuType, curBlock);
        curBlock.phiList.add(this);
    }

    public void addBranch(IRBasicBlock block, IRBaseValue res) {
        appendOprand(block);
        appendOprand(res);
    }

    @Override
    public String formatDef() {
        var ret = "phi " + valueType.formatType() + " ";
        for (int i = 0; i < getOprandNum(); i += 2) {
            ret += "[ " + getOprand(i + 1).formatUse() + ", " + getOprand(i).formatUse() +
                    (i == getOprandNum() - 2 ? " ]" : " ], ");
        }
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }
}
