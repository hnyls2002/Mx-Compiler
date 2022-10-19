package Util.Scopes;

import Util.Position;
import Util.TypeName;
import Util.MxStarErrors.SemanticError;
import Util.Types.BaseType;

public class GlobalScope extends Scope {
    public final TypeName intName = new TypeName("int", 0, false);
    public final TypeName stringName = new TypeName("string", 0, false);
    public final TypeName boolName = new TypeName("bool", 0, false);
    public final TypeName nullName = new TypeName("null", 0, false);
    public final TypeName voidName = new TypeName("void", 0, false);

    public GlobalScope() {
        super(null);
    }

    public BaseType getType(String s, Position pos) { // can just get type frome string, without demensions
        if (!typeMap.containsKey(s))
            throw new SemanticError("Cannot find typename " + s + " ", pos);
        return typeMap.get(s);
    }

    public void putType(BaseType t) {
        if (typeMap.containsKey(t.typeNameString))
            throw new SemanticError("Redefinition of class " + t.typeNameString, t.pos);
        if (funMap.containsKey(t.typeNameString))
            throw new SemanticError("Invalid name for class " + t.typeNameString + " , as it's already a function name",
                    t.pos);
        typeMap.put(t.typeNameString, t);
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
