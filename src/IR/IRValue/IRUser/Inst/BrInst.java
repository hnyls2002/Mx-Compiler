package IR.IRValue.IRUser.Inst;

import IR.IRType.IRVoidType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;

public class BrInst extends IRBaseInst {
    public IRBaseValue condition;
    public IRBasicBlock trueBlock, falseBlock;

    public BrInst(IRBaseValue condition, IRBasicBlock trueBlock, IRBasicBlock falseBlock, IRBasicBlock curBlock) {
        super(new IRVoidType());
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
}
