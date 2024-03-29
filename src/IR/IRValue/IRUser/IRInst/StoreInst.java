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

    private boolean trans(IRBaseValue storedValue, IRBaseValue destAddr, IRBasicBlock block, int insertIdx) {
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

            if (insertIdx == -1)
                setOprand(0, new CastInst(storedValue, derefType, opCode, block));
            else
                setOprand(0, new CastInst(storedValue, derefType, opCode, block, insertIdx));
            return true;
        }
        return false;
    }

    public StoreInst(IRBaseValue storedValue, IRBaseValue destAddr, IRBasicBlock block) {
        super(new IRVoidType(), block);

        appendOprand(storedValue);
        appendOprand(destAddr);

        trans(storedValue, destAddr, block, -1);

        if (block != null)
            block.addInst(this);
    }

    public StoreInst(IRBaseValue storedValue, IRBaseValue destAddr, IRBasicBlock block, int insertIdx) {
        super(new IRVoidType(), block);

        appendOprand(storedValue);
        appendOprand(destAddr);

        boolean isCast = trans(storedValue, destAddr, block, insertIdx);

        if (block != null)
            block.instList.add(isCast ? insertIdx + 1 : insertIdx, this);
    }

    @Override
    public String formatDef() {
        var ret = "store ";
        // stored value can be null
        var storedType = getOprand(0).valueType;
        var derefType = ((IRPtType) getOprand(1).valueType).derefType();
        if (!(getOprand(0) instanceof NullConst) && !storedType.equals(derefType))
            throw new MyException(
                    "stored : [" + storedType.formatType() + "] and address deref : [" + derefType.formatType()
                            + "] not match!");

        ret += ((IRPtType) getOprand(1).valueType).derefType().formatType() + ' ' + getOprand(0).formatUse() + ", ";
        ret += getOprand(1).formatUseWithType();
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public IRBaseInst copy() {
        return new StoreInst(getOprand(0), getOprand(1), null);
    }
}
