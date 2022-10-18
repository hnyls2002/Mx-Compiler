package Util.Types;

import java.util.HashMap;

import Util.Position;
import Util.TypeIdPair;

public class ClassType extends BaseType {
    public HashMap<String, TypeIdPair> varMap = new HashMap<>();
    public HashMap<String, FuncInfo> funMap = new HashMap<>();
    public boolean haveConst = false;
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

    @Override
    public String toString() {
        String ret = "";
        ret += "CLASS : " + typeNameString + "\n";
        ret += haveConst ? "have constructor\n" : "not have constructor\n";
        StringBuffer buff = new StringBuffer();
        funMap.forEach((name, info) -> buff.append(info.toString()));
        ret += buff;
        buff.setLength(0);
        varMap.forEach((name, pair) -> buff.append(pair.toString() + "\n"));
        ret += buff;
        ret += "CLASS END\n";
        return ret;
    }
}
