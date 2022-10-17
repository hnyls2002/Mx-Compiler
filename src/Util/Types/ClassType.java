package Util.Types;

import java.util.HashMap;

import Util.Position;
import Util.TypeIdPair;

public class ClassType extends BaseType {
    public HashMap<String, TypeIdPair> varMap = new HashMap<>();
    public HashMap<String, FuncInfo> funMap = new HashMap<>();
    public Position pos;

    public ClassType(String classNameString, Position pos) {
        this.typeNameString = classNameString;
        this.pos = pos;
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
