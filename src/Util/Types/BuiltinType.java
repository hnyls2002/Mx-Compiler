package Util.Types;

public class BuiltinType extends BaseType {

    public BuiltinType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean isClass() {
        return false;
    }
}
