package IR.IRType;

public class IRIntType extends IRBaseType {

    public final int intLen;

    public IRIntType(String typeString, int intLen) {
        super(typeString);
        this.intLen = intLen;
    }
}
