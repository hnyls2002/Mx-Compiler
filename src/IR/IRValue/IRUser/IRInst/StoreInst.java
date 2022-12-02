package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRIntType;
import IR.IRType.IRPtType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import IR.IRValue.IRUser.IRInst.CastInst.castType;
import Share.MyException;

public class StoreInst extends IRBaseInst {
    public IRBaseValue storedValue, destAddr;

    public StoreInst(IRBaseValue storedValue, IRBaseValue destAddr, IRBasicBlock block) {
        super(new IRVoidType());

        this.storedValue = storedValue;
        this.destAddr = destAddr;

        var storedType = storedValue.valueType;
        var derefType = ((IRPtType) destAddr.valueType).derefType();
        if (!(storedValue instanceof NullConst) && !storedType.equals(derefType)) {
            castType opCode = null;
            if (storedType instanceof IRPtType && derefType instanceof IRPtType) // bitcast
                opCode = castType.BIT;
            else if (storedType instanceof IRIntType i1 && derefType instanceof IRIntType i2) {
                if (i1.intLen > i2.intLen)
                    opCode = castType.TRUNC;
                else // zero extend or signal extend ?
                    opCode = castType.ZEXT;
            } else
                throw new MyException("Cast Instruction unknown error");
            this.storedValue = new CastInst(storedValue, derefType, opCode, block);
        }

        block.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = "store ";
        // stored value can be null
        var storedType = storedValue.valueType;
        var derefType = ((IRPtType) destAddr.valueType).derefType();
        if (!(storedValue instanceof NullConst) && !storedType.equals(derefType))
            throw new MyException(
                    "stored : [" + storedType.toString() + "] and address deref : [" + derefType.toString()
                            + "] not match!");

        ret += ((IRPtType) destAddr.valueType).derefType().toString() + ' ' + storedValue.useToString() + ", ";
        ret += destAddr.useToStringWithType();
        return ret;
    }

}
