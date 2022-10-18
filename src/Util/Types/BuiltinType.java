package Util.Types;

import java.util.HashMap;

public class BuiltinType extends BaseType {

    public HashMap<String, FuncInfo> funMap = new HashMap<>();

    public BuiltinType(String typeNameString) {
        this.typeNameString = typeNameString;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean isClass() {
        return false;
    }

    @Override
    public String toString() {
        return "BUILTIN TYPE : " + typeNameString + "\n";
    }

}
