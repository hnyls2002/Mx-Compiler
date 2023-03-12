package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRVoidType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import Share.Visitors.IRInstVisitor;

public class RetInst extends IRBaseInst {
    private boolean isVoid = false;

    public boolean isVoid() {
        return isVoid;
    }

    // "ret %3" is void type ?
    public RetInst(IRBaseValue retValue, IRBasicBlock block) {
        super(new IRVoidType());
        appendOprand(retValue);
        block.setTerminal(this);
    }

    public static void createVoidRetInst(IRBasicBlock block) {
        var retInst = new RetInst(null, block);
        retInst.isVoid = true;
    }

    @Override
    public String defToString() {
        if (isVoid())
            return "ret void";
        return "ret" + ' ' + getOprand(0).useToStringWithType();
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

}
