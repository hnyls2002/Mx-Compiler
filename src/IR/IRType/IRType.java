package IR.IRType;

/// All values have a type
/// llvm::type is not a abstract type
/// typeId shows the specific type
/// So it cannot be called "base type"

public class IRType {
    public enum IRTypeId {
        LabelTypeId, ArrayTypeId, IntTypeId, VoidTypeId, FnTypeId, PtTypeId, StructTypeId
    }

    public IRTypeId typeId;

    public IRType(IRTypeId typeId) {
        this.typeId = typeId;
    }

    static IRType getLabelType() {
        return new IRType(IRTypeId.LabelTypeId);
    }
}
