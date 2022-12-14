package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRIntType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.IRInst.CastInst.castType;
import Share.Visitors.IRInstVisitor;

public class BrInst extends IRBaseInst {
    public IRBaseValue condition;
    public IRBasicBlock trueBlock, falseBlock;

    public BrInst(IRBaseValue condition, IRBasicBlock trueBlock, IRBasicBlock falseBlock, IRBasicBlock curBlock) {
        super(new IRVoidType());

        assert condition.valueType instanceof IRIntType;
        if (condition.valueType instanceof IRIntType t && t.intLen != 1)
            condition = new CastInst(condition, new IRIntType(1), castType.TRUNC, curBlock);

        this.condition = condition;
        this.trueBlock = trueBlock;
        this.falseBlock = falseBlock;
        curBlock.setTerminal(this);
    }

    // 1. if retInst inside -> jump to fn's retBlock
    // 2. if not inside -> jump to target block
    public BrInst(IRBasicBlock targetBlock, IRBasicBlock curBlock) {
        super(new IRVoidType());
        this.condition = null;
        this.trueBlock = targetBlock;
        curBlock.setTerminal(this);
    }

    @Override
    public String defToString() {
        var ret = "br ";
        if (condition == null)
            ret += trueBlock.useToStringWithType();
        else {
            ret += condition.useToStringWithType() + ", ";
            ret += trueBlock.useToStringWithType() + ", ";
            ret += falseBlock.useToStringWithType();
        }
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }
}
