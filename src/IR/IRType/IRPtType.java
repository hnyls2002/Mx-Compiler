package IR.IRType;

public class IRPtType extends IRBaseType {
    public IRBaseType atomicType;
    public int refNum = 0;

    public IRPtType(IRBaseType atomicType, int refNum) {
        this.atomicType = atomicType;
        this.refNum = refNum;

    }

}
