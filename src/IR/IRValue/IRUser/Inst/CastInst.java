package IR.IRValue.IRUser.Inst;

import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;

public class CastInst extends IRBaseInst {

    public enum castType {
        BIT("bitcast"), SEXT("sext"), ZEXT("zext"), TRUNC("trunc");

        String insStr;

        private castType(String insStr) {
            this.insStr = insStr;
        }

        @Override
        public String toString() {
            return insStr;
        }
    };

    IRBaseValue srcValue = null;
    IRType targetType = null;
    castType opCode = null;

    public CastInst(IRBaseValue srcValue, IRType targetType, castType opCode, IRBasicBlock block) {
        super(targetType);
        this.srcValue = srcValue;
        this.targetType = targetType;
        this.opCode = opCode;
        block.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = opCode.toString() + ' ' + srcValue.useToStringWithType() + " to ";
        ret += targetType.toString();
        return ret;
    }

}
