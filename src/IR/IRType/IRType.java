package IR.IRType;

import Debug.MyException;
import IR.IRValue.IRBaseValue;

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
        throw new MyException("you should go to the override method!");
    }

    @Override
    public boolean equals(Object obj) {
        return false;
        // only override int and pointer type
    }

    public int getSize() {
        return switch (typeId) {
            case ArrayTypeId, FnTypeId, LabelTypeId, PtTypeId, VoidTypeId -> 8;
            case IntTypeId -> 4;
            default -> throw new IllegalArgumentException("Unexpected value: " + typeId);
        };
    }
}
