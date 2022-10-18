package Util.Scopes;

import java.util.HashMap;

import Util.Position;
import Util.TypeName;
import Util.MxStarErrors.SemanticError;
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

    public GlobalScope() {
        super(null);
    }

    public BaseType getType(String s, Position pos) { // can just get type frome string, without demensions
        if (!typeMap.containsKey(s))
            throw new SemanticError("Cannot find typename " + s + " ", pos);
        return typeMap.get(s);
    }

    public FuncInfo getFuncInfo(String s, Position pos) {
        if (!funMap.containsKey(s))
            throw new SemanticError("Cannot find function " + s + " ", pos);
        return funMap.get(s);
    }

    public void putType(BaseType t) {
        if (typeMap.containsKey(t.typeNameString))
            throw new SemanticError("Redefinition of class " + t.typeNameString, t.pos);
        typeMap.put(t.typeNameString, t);
    }

    public void putFunc(FuncInfo f) {
        if (funMap.containsKey(f.funcName))
            throw new SemanticError("Redefinition of function " + f.funcName, f.pos);
        funMap.put(f.funcName, f);
    }

    public void showShowWay() {
        System.err.println("----------------------");
        typeMap.forEach(
                (name, type) -> {
                    System.err.print(type.toString());
                    System.err.println("----------------------");
                });
        funMap.forEach((name, func) -> {
            System.err.print(func.toString());
            System.err.println("----------------------");
        });
    }
}
