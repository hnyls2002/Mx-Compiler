package IR.IRType;

public class IRArrayType extends IRType {
    public IRType elementType;
    public int arrayLen = 0;

    public IRArrayType(IRType elemenType, int arrayLen) {
        super(IRTypeId.ArrayTypeId);
        this.elementType = elemenType;
        this.arrayLen = arrayLen;
    }

    @Override
    public String toString() {
        return "[" + arrayLen + " x " + elementType.toString() + "]";
    }

}
