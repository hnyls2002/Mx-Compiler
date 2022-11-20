package IR.IRType;

public class IRArrayType extends IRBaseType {
    public IRBaseType elementType;
    public int arrayLen = 0;

    public IRArrayType(IRBaseType elemenType, int arrayLen) {
        this.elementType = elemenType;
        this.arrayLen = arrayLen;
    }
}
