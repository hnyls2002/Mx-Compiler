package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRType;
import Share.Visitors.IRInstVisitor;

public class JumpInst extends IRBaseInst {
    // unconditional jump
    public JumpInst(IRType insType) {
        super(insType);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }

    @Override
    public String defToString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'defToString'");
    }

}
