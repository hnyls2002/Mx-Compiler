package IR.IRType;

import IR.IRValue.IRBaseValue;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;

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

    public IRBaseValue defaultValue() {
        return switch (typeId) {
            case IntTypeId -> new IntConst(0, null); // we don't care about 0's type
            case PtTypeId -> new NullConst();
            // array type used for literal string, no need for default value
            // label, void, fn, struct -----> no need for default value
            default -> throw new IllegalArgumentException("Unexpected value: " + typeId);
        };
    }

}
