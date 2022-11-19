package Frontend.Util.Types;

import java.util.HashMap;

import Frontend.Util.Position;
import Frontend.Util.MxStarErrors.SemanticError;

public abstract class BaseType {
    public String typeNameString;
    public Position pos;
    public HashMap<String, FuncInfo> funMap = new HashMap<>();

    public abstract boolean isBuiltin();

    public abstract boolean isClass();

    public BaseType(Position pos) {
        this.pos = pos;
    }

    public FuncInfo getFunc(String s, Position pos) {
        if (!funMap.containsKey(s))
            throw new SemanticError("Cannot find method " + s + " in " + typeNameString + " ", pos);
        return funMap.get(s);
    }

}