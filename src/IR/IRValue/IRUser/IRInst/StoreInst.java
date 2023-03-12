package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRIntType;
import IR.IRType.IRPtType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import Share.MyException;
import Share.Lang.LLVMIR.CastType;
import Share.Visitors.IRInstVisitor;

public class StoreInst extends IRBaseInst {
    // storedValue 0, destAddr 1

    public StoreInst(IRBaseValue storedValue, IRBaseValue destAddr, IRBasicBlock block) {
        super(new IRVoidType());

        appendOprand(storedValue);
        appendOprand(destAddr);

        var storedType = storedValue.valueType;
        var derefType = ((IRPtType) destAddr.valueType).derefType();
        if (!(storedValue instanceof NullConst) && !storedType.equals(derefType)) {
            CastType opCode = null;
            if (storedType instanceof IRPtType && derefType instanceof IRPtType) // bitcast
                opCode = CastType.bitcast;
            else if (storedType instanceof IRIntType i1 && derefType instanceof IRIntType i2) {
                if (i1.intLen > i2.intLen)
                    opCode = CastType.trunc;
                else // zero extend or signal extend ?
                    opCode = CastType.zext;
            } else
                throw new MyException("Cast Instruction unknown error");
            setOprand(0, new CastInst(storedValue, derefType, opCode, block));
        }

        block.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = "store ";
        // stored value can be null
        var storedType = getOprand(0).valueType;
        var derefType = ((IRPtType) getOprand(1).valueType).derefType();
        if (!(getOprand(0) instanceof NullConst) && !storedType.equals(derefType))
            throw new MyException(
                    "stored : [" + storedType.toString() + "] and address deref : [" + derefType.toString()
                            + "] not match!");

        ret += ((IRPtType) getOprand(1).valueType).derefType().toString() + ' ' + getOprand(0).useToString() + ", ";
        ret += getOprand(1).useToStringWithType();
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

}
