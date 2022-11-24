package IR.IRValue.IRUser.Inst;

import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;

public class BinaryInst extends IRBaseInst {
    public enum binaryOperator {
        irADD("add"), irSUB("sub"), irMUL("mul"), irSDIV("sdiv"), irSREM("srem"), irSHL("shl"),
        irASHR("ashr"), irAND("and"), irOR("or"), irXOR("xor");

        String insStr;

        private binaryOperator(String insStr) {
            this.insStr = insStr;
        }

        @Override
        public String toString() {
            return insStr;
        }
    }

    public binaryOperator opCode;
    public IRBaseValue lhs, rhs;

    public BinaryInst(binaryOperator opCode, IRBaseValue lhs, IRBaseValue rhs, IRBasicBlock block) {
        super(lhs.valueType);
        this.opCode = opCode;
        this.lhs = lhs;
        this.rhs = rhs;
        block.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = opCode.toString() + ' ' + valueType.toString() + ' ';
        ret += lhs.useToString() + ", " + rhs.useToString();
        return ret;
    }

}
