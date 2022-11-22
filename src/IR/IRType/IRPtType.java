package IR.IRType;

public class IRPtType extends IRType {
    public IRType atomicType;
    public int refNum = 0;

    public IRPtType(IRType atomicType, int refNum) {
        super(IRTypeId.PtTypeId);
        this.atomicType = atomicType;
        this.refNum = refNum;
    }
}
