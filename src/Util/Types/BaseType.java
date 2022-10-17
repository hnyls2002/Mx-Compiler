package Util.Types;

public abstract class BaseType {
    public String typeNameString;

    public abstract boolean isBuiltin();

    public abstract boolean isClass();

    public boolean isInt() {
        return typeNameString.equals("int");
    }

    public boolean isString() {
        return typeNameString.equals("string");
    }

    public boolean isBool() {
        return typeNameString.equals("bool");
    }

    public boolean isNull() {
        return typeNameString.equals("null");
    }

    public boolean isVoid() {
        return typeNameString.equals("void");
    }
}
