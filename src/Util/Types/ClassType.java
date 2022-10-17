package Util.Types;

import java.util.HashMap;

public class ClassType extends BaseType {
    public HashMap<String, BaseType> varMap = new HashMap<>();

    public ClassType(String className) {
        this.typeName = className;
    }

    @Override
    public boolean isClass() {
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return false;
    }
}
