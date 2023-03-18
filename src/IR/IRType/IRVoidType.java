package IR.IRType;

public class IRVoidType extends IRType {
    public IRVoidType() {
        super(IRTypeId.VoidTypeId);
    }

    @Override
    public String formatType() {
        return "void";
    }
}
