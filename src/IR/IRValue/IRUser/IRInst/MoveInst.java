package IR.IRValue.IRUser.IRInst;

import IR.IRValue.IRBaseValue;
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

    public MoveInst(IRBaseValue dst, IRBaseValue src) {
        super(dst.valueType);
        appendOprand(dst);
        appendOprand(src);
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String defToString() {
        return " = " + getOprand(1).useToStringWithType();
    }
}
