package IR.IRValue.IRUser.IRInst;

import ASM.ASMOprand.Immediate;
import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import Share.MyException;
import Share.Lang.LLVMIR;
import Share.Lang.LLVMIR.BOP;
import Share.Visitors.IRInstVisitor;

public class BinaryInst extends IRBaseInst {

    public BOP opCode;

    public BinaryInst(BOP opCode, IRBaseValue lhs, IRBaseValue rhs, IRBasicBlock block) {
        super(lhs.valueType, block);
        this.opCode = opCode;
        appendOprand(lhs);
        appendOprand(rhs);
        IRType lType = lhs.valueType, rType = rhs.valueType;
        if (!lType.equals(rType))
            throw new MyException(
                    "Binary " + this.opCode + " " + lType.formatType() + " " + rType.formatType() + " not match");
        if (block != null)
            block.addInst(this);
    }

    public IRBaseValue lhs() {
        return getOprand(0);
    }

    public IRBaseValue rhs() {
        return getOprand(1);
    }

    public void swapOprand() {
        var tmp = getOprand(0);
        setOprand(0, getOprand(1));
        setOprand(1, tmp);
    }

    public void swapOprandForConst() {
        if (LLVMIR.commutative.contains(opCode) && getOprand(0).asOprand instanceof Immediate) {
            var tmp = getOprand(0);
            setOprand(0, getOprand(1));
            setOprand(1, tmp);
        }
    }

    @Override
    public String formatDef() {
        var ret = opCode.name() + ' ' + valueType.formatType() + ' ';
        ret += getOprand(0).formatUse() + ", " + getOprand(1).formatUse();
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }
}
