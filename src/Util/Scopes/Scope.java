package Util.Scopes;

import java.util.HashMap;

import Util.Position;
import Util.TypeIdPair;
import Util.MxStarErrors.SemanticError;

public class Scope {
    public HashMap<String, TypeIdPair> DefMap = new HashMap<>(); // the DefMap only stores the typename literally
    public Scope parent = null;

    public Scope(Scope parent) {
        this.parent = parent;
    }

    // adding vars when semantic checking
    public void putDef(TypeIdPair p, GlobalScope gScope) {
        if (DefMap.containsKey(p.Id))
            throw new SemanticError("Redefinition of " + p.toString(), p.pos);
        if (gScope.typeMap.containsKey(p.Id))
            throw new SemanticError("Invalid var name, as " + p.toString() + " is a type name ", p.pos);
        if (gScope.funMap.containsKey(p.Id))
            throw new SemanticError("Invalid var name, as " + p.toString() + " is a function name ", p.pos);
        DefMap.put(p.Id, p);
    }

    public TypeIdPair getDef(String s, Position pos) {
        if (!DefMap.containsKey(s)) {
            if (parent == null)
                throw new SemanticError("Cannot find var " + s + " ", pos);
            else
                return parent.getDef(s, pos);
        }
        return DefMap.get(s);
    }

}
