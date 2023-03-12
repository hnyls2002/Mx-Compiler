package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRVoidType;
import IR.IRValue.IRBasicBlock;
import Share.Visitors.IRInstVisitor;

public class JumpInst extends IRBaseInst {
    // unconditional jump

    // 1. if retInst inside -> jump to fn's retBlock
    // 2. if not inside -> jump to target block
    public JumpInst(IRBasicBlock targetBlock, IRBasicBlock curBlock) {
        super(new IRVoidType());
        appendOprand(targetBlock);
        curBlock.setTerminal(this);
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String defToString() {
        var ret = "br ";
        ret += getOprand(0).useToStringWithType();
        return ret;
    }

}
