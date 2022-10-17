package Util.Types;

public abstract class BaseType {
    public String typeName;

    public abstract boolean isBuiltin();

    public abstract boolean isClass();

    public boolean isInt() {
        return typeName.equals("int");
    }

    public boolean isString() {
        return typeName.equals("string");
    }

    public boolean isBool() {
        return typeName.equals("bool");
    }

    public boolean isNull() {
        return typeName.equals("null");
    }

    public boolean isVoid() {
        return typeName.equals("void");
    }
}
