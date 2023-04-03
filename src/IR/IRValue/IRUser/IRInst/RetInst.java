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
        super(new IRVoidType(), block);
        appendOprand(retValue);
        if (block != null)
            block.setTerminal(this);
    }

    public RetInst(IRBasicBlock block) {
        super(new IRVoidType(), block);
        if (block != null)
            block.setTerminal(this);
    }

    public static void createVoidRetInst(IRBasicBlock block) {
        var retInst = new RetInst(block);
        retInst.isVoid = true;
    }

    @Override
    public String formatDef() {
        if (isVoid())
            return "ret void";
        return "ret" + ' ' + getOprand(0).formatUseWithType();
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public IRBaseInst copy() {
        if (getOprandNum() == 0)
            return new RetInst(null);
        return new RetInst(getOprand(0), null);
    }
}
