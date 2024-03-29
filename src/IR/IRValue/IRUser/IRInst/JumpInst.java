package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRVoidType;
import IR.IRValue.IRBasicBlock;
import Share.Visitors.IRInstVisitor;

public class JumpInst extends IRBaseInst {
    // unconditional jump

    // 1. if retInst inside -> jump to fn's retBlock
    // 2. if not inside -> jump to target block
    public JumpInst(IRBasicBlock targetBlock, IRBasicBlock curBlock) {
        super(new IRVoidType(), curBlock);
        appendOprand(targetBlock);
        curBlock.setTerminal(this);
    }

    public JumpInst(IRBasicBlock targetBlock) {
        super(new IRVoidType(), null);
        appendOprand(targetBlock);
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String formatDef() {
        var ret = "br ";
        ret += getOprand(0).formatUseWithType();
        return ret;
    }

    @Override
    public IRBaseInst copy() {
        return new JumpInst((IRBasicBlock) getOprand(0));
    }
}
