package AST.Scopes;

import java.util.LinkedHashMap;

import AST.Info.FuncInfo;
import AST.Info.TypeIdPair;
import AST.Types.BaseType;
import AST.Util.Position;
import Frontend.MxStar.MxStarErrors.SemanticError;

public class Scope {
    public LinkedHashMap<String, BaseType> typeMap = new LinkedHashMap<>(); // suppose class can be in class
    public LinkedHashMap<String, FuncInfo> funMap = new LinkedHashMap<>();
    // when you enter a class, the inner function should be added into curScope !
    public LinkedHashMap<String, TypeIdPair> DefMap = new LinkedHashMap<>();
    // the DefMap only stores the typename literally
    public Scope parent = null;

    public Scope(Scope parent) {
        this.parent = parent;
    }

    // adding vars when semantic checking
    public void putDef(TypeIdPair p) {
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
