package IR.IRValue.IRUser.Inst;

import IR.IRType.IRIntType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;

public class IcmpInst extends IRBaseInst {
    public enum icmpOperator {
        irEQ("eq"), irNE("ne"), irSGT("sgt"), irSGE("sge"), irSLT("slt"), irSLE("sle");

        String insStr;

        private icmpOperator(String insStr) {
            this.insStr = insStr;
        }

        @Override
        public String toString() {
            return insStr;
        }
    }

    public icmpOperator opCode;
    public IRBaseValue lhs, rhs;

    public IcmpInst(icmpOperator opCode, IRBaseValue lhs, IRBaseValue rhs, IRBasicBlock block) {
        super(new IRIntType(1));
        this.opCode = opCode;
        this.lhs = lhs;
        this.rhs = rhs;
        block.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = "icmp " + opCode.toString() + ' ' + valueType.toString() + ' ';
        ret += lhs.useToString() + ", " + rhs.useToString();
        return ret;
    }
}
