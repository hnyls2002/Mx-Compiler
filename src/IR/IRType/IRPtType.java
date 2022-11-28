package IR.IRType;

import IR.IRValue.IRBaseValue;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;

public class IRPtType extends IRType {
    public IRType atomicType;
    public int refNum = 0;

    public IRPtType(IRType atomicType, int refNum) {
        super(IRTypeId.PtTypeId);
        while (atomicType instanceof IRPtType t) {
            atomicType = t.atomicType;
            refNum += t.refNum;
        }
        this.atomicType = atomicType;
        this.refNum = refNum;
    }

    public static IRPtType getCharRefType() {
        return new IRPtType(new IRIntType(8), 1);
    }

    public IRType derefType() {
        if (refNum >= 2)
            return new IRPtType(atomicType, refNum - 1);
        return atomicType;
    }

    @Override
    public String toString() {
        return atomicType.toString() + "*".repeat(refNum);
    }

    @Override
    public IRBaseValue defaultValue() {
        return new NullConst();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IRType t && t.typeId == IRTypeId.NullTypeId)
            return true;
        if (obj instanceof IRPtType t && t.atomicType.equals(atomicType) && t.refNum == refNum)
            return true;
        return false;
    }
}
