package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import Share.MyException;
import Share.Lang.LLVMIR.BOP;
import Share.Visitors.IRInstVisitor;

public class BinaryInst extends IRBaseInst {

    public BOP opCode;

    public BinaryInst(BOP opCode, IRBaseValue lhs, IRBaseValue rhs, IRBasicBlock block) {
        super(lhs.valueType);
        this.opCode = opCode;
        appendOprand(lhs);
        appendOprand(rhs);
        IRType lType = lhs.valueType, rType = rhs.valueType;
        if (!lType.equals(rType))
            throw new MyException(
                    "Binary " + this.opCode + " " + lType.toString() + " " + rType.toString() + " not match");
        block.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = opCode.toString() + ' ' + valueType.toString() + ' ';
        ret += getOprand(0).useToString() + ", " + getOprand(1).useToString();
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }
}
