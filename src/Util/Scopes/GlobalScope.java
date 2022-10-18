package Util.Scopes;

import java.util.HashMap;

import Util.TypeName;
import Util.Types.BaseType;
import Util.Types.FuncInfo;

public class GlobalScope extends Scope {
    public HashMap<String, BaseType> typeMap = new HashMap<>(); // the globally defined class type.
    public HashMap<String, FuncInfo> funMap = new HashMap<>(); // function defined globally

    public final TypeName intName = new TypeName("int", 0);
    public final TypeName stringName = new TypeName("string", 0);
    public final TypeName boolName = new TypeName("bool", 0);
    public final TypeName nullName = new TypeName("null", 0);
    public final TypeName voidName = new TypeName("void", 0);

    public BaseType getType(String s) {
        return typeMap.get(s);
    }

    public FuncInfo getFuncInfo(String s) {
        return funMap.get(s);
    }

    public void putType(BaseType t) {
        typeMap.put(t.typeNameString, t);
    }

    public void putFunc(FuncInfo f) {
        funMap.put(f.funcName, f);
    }

    public void showShowWay() {
        System.err.println("----------------------");
        typeMap.forEach(
                (name, type) -> {
                    System.err.print(type.toString());
                    System.err.println("----------------------");
                });
    }
}
