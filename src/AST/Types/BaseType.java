package AST.Types;

import java.util.LinkedHashMap;

import AST.Info.FuncInfo;
import AST.Util.Position;
import Frontend.MxStar.MxStarErrors.SemanticError;

public abstract class BaseType {
    public String typeNameString;
    public Position pos;
    public LinkedHashMap<String, FuncInfo> funMap = new LinkedHashMap<>();

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