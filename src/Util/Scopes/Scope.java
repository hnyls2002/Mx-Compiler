package Util.Scopes;

import java.util.HashMap;

import Util.Position;
import Util.TypeIdPair;
import Util.MxStarErrors.SemanticError;
import Util.Types.BaseType;
import Util.Types.FuncInfo;

public class Scope {
    public HashMap<String, BaseType> typeMap = new HashMap<>(); // suppose class can be in class
    public HashMap<String, FuncInfo> funMap = new HashMap<>();
    // when you enter a class, the inner function should be added into curScope !
    public HashMap<String, TypeIdPair> DefMap = new HashMap<>(); // the DefMap only stores the typename literally
    public Scope parent = null;

    public Scope(Scope parent) {
        this.parent = parent;
    }

    // adding vars when semantic checking
    public void putDef(TypeIdPair p, GlobalScope gScope) {
        if (DefMap.containsKey(p.Id))
            throw new SemanticError("Redefinition of " + p.toString(), p.pos);
        /*
         * if (gScope.typeMap.containsKey(p.Id))
         * throw new SemanticError("Invalid var name, as " + p.toString() +
         * " is a type name ", p.pos);
         * if (gScope.funMap.containsKey(p.Id))
         * throw new SemanticError("Invalid var name, as " + p.toString() +
         * " is a function name ", p.pos);
         * 
         */
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

    public void putFunc(FuncInfo f) {
        if (funMap.containsKey(f.funcName))
            throw new SemanticError("Redefinition of function " + f.funcName, f.pos);
        if (typeMap.containsKey(f.funcName))
            throw new SemanticError("Invalid name for function " + f.funcName + " , as it's already a class name",
                    f.pos);
        funMap.put(f.funcName, f);
    }

    public FuncInfo getFuncInfo(String s, Position pos) {
        if (!funMap.containsKey(s)) {
            if (parent == null)
                throw new SemanticError("Cannot find function " + s + " ", pos);
            else
                return parent.getFuncInfo(s, pos);
        }
        return funMap.get(s);
    }

}
