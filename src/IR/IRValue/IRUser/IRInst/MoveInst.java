package IR.IRValue.IRUser.IRInst;

import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import Share.Visitors.IRInstVisitor;

public class MoveInst extends IRBaseInst {
    /*
     * fake IR inst
     * the other kind of inst is a value itself
     * however the move inst doesn't generate a value,
     * instaed it's value is the destination
     * 
     * so the Move inst can't be printed traditionally
     * and it doesn't have "asOprand", "def"...
     */

    public MoveInst(IRBaseValue dst, IRBaseValue src, IRBasicBlock block, boolean atEnd) {
        super(dst.valueType, block);
        appendOprand(dst);
        appendOprand(src);
        if (atEnd)
            block.addInstBeforeTerminal(this);
        else
            block.instList.add(0, this);
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String formatDef() {
        return getOprand(1).formatUseWithType();
    }

    @Override
    public IRBaseInst copy() {
        throw new RuntimeException("MoveInst doesn't support copy");
    }
}
