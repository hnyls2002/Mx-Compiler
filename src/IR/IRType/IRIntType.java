package IR.IRType;

public class IRIntType extends IRType {

    public final int intLen;

    public IRIntType(int intLen) {
        super(IRTypeId.IntTypeId);
        this.intLen = intLen;
    }

    @Override
    public String toString() {
        return "i" + intLen;
    }
}
