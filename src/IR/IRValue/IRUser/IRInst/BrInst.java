package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRIntType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import Share.Lang.LLVMIR.CastType;
import Share.Visitors.IRInstVisitor;

public class BrInst extends IRBaseInst {

    // conditional jump
    public BrInst(IRBaseValue condition, IRBasicBlock trueBlock, IRBasicBlock falseBlock, IRBasicBlock curBlock) {
        super(new IRVoidType());

        assert condition.valueType instanceof IRIntType;
        if (condition.valueType instanceof IRIntType t && t.intLen != 1)
            condition = new CastInst(condition, new IRIntType(1), CastType.trunc, curBlock);

        appendOprand(condition);
        appendOprand(trueBlock);
        appendOprand(falseBlock);

        assert trueBlock != null && falseBlock != null;

        curBlock.setTerminal(this);
    }

    @Override
    public String defToString() {
        var ret = "br ";
        ret += getOprand(0).useToStringWithType() + ", ";
        ret += getOprand(1).useToStringWithType() + ", ";
        ret += getOprand(2).useToStringWithType();
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }
}
