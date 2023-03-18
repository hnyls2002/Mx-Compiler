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
        super(new IRIntType(1));
        this.opCode = opCode;
        appendOprand(lhs);
        appendOprand(rhs);
        IRType lType = lhs.valueType, rType = rhs.valueType;
        if (!lType.equals(rType))
            throw new MyException(
                    "icmp " + this.opCode + " " + lType.toString() + " " + rType.toString() + " not match");
        block.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = "icmp " + opCode.toString() + ' ' + getOprand(0).valueType.toString() + ' ';
        ret += getOprand(0).useToString() + ", " + getOprand(1).useToString();
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }
}
