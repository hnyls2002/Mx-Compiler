package IR.IRValue.IRUser.Inst;

import IR.IRValue.IRBaseValue;

public class BinaryInst extends IRBaseInst {
    public enum binaryOperator {
        irADD("add"), irSUB("sub"), irMUL("mul"), irSDIV("sdiv"), irSREM("srem"), irSHL("shl"),
        irASHR("ashr"), irAND("and"), irOR("or"), irXOR("xor");

        String insStr;

        private binaryOperator(String insStr) {
            this.insStr = insStr;
        }
    }

    public binaryOperator opCode;
    public IRBaseValue lhs, rhs;

    public BinaryInst(binaryOperator opCode, IRBaseValue lhs, IRBaseValue rhs) {
        super(lhs.valueType);
        this.opCode = opCode;
        this.lhs = lhs;
        this.rhs = rhs;
    }

}
