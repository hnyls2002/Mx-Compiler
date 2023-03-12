package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import Share.Visitors.IRInstVisitor;

public class MoveInst extends IRBaseInst {
    // fake IR inst

    public MoveInst(IRType insType, IRBaseValue dst, IRBaseValue src) {
        super(insType);
        appendOprand(dst);
        appendOprand(src);
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String defToString() {
        return null;
    }
}
