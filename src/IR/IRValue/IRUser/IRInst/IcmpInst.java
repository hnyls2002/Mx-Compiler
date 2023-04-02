package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRIntType;
import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import Share.MyException;
import Share.Lang.LLVMIR.ICMPOP;
import Share.Visitors.IRInstVisitor;

public class IcmpInst extends IRBaseInst {

    public ICMPOP opCode;
    public boolean onlyInBranch = false;

    public IcmpInst(ICMPOP opCode, IRBaseValue lhs, IRBaseValue rhs, IRBasicBlock block) {
        super(new IRIntType(1), block);
        this.opCode = opCode;
        appendOprand(lhs);
        appendOprand(rhs);
        IRType lType = lhs.valueType, rType = rhs.valueType;
        if (!lType.equals(rType))
            throw new MyException(
                    "icmp " + this.opCode + " " + lType.formatType() + " " + rType.formatType() + " not match");
        block.addInst(this);
    }

    public IRBaseValue lhs() {
        return getOprand(0);
    }

    public IRBaseValue rhs() {
        return getOprand(1);
    }

    @Override
    public String formatDef() {
        var ret = "icmp " + opCode.name() + ' ' + getOprand(0).valueType.formatType() + ' ';
        ret += getOprand(0).formatUse() + ", " + getOprand(1).formatUse();
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }
}
