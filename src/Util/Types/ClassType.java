package Util.Types;

import java.util.HashMap;

import Util.Position;
import Util.TypeIdPair;
import Util.MxStarErrors.SemanticError;

public class ClassType extends BaseType {
    public HashMap<String, TypeIdPair> varMap = new HashMap<>();
    public HashMap<String, FuncInfo> funMap = new HashMap<>();
    public boolean haveConst = false;
    public Position pos;

    public ClassType(String classNameString, Position pos) {
        super(pos);
        this.typeNameString = classNameString;
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

    // put def of vars in global definition
    public void putDef(TypeIdPair p) {
        if (varMap.containsKey(p.Id))
            throw new SemanticError("Redefinition of " + p.Id + " in class " + typeNameString, p.pos);
        varMap.put(p.Id, p);
    }

    // put funcs in global definition
    public void putFunc(FuncInfo f) {
        if (funMap.containsKey(f.funcName))
            throw new SemanticError("Redefinition of " + f.funcName + " in class " + typeNameString, f.pos);
        funMap.put(f.funcName, f);
    }

    public TypeIdPair getDef(String s, Position pos) {
        if (!varMap.containsKey(s))
            throw new SemanticError("Cannot find field " + s + " in class " + typeNameString + " ", pos);
        return varMap.get(s);
    }

    public FuncInfo getFunc(String s, Position pos) {
        if (!funMap.containsKey(s))
            throw new SemanticError("Cannot find method " + s + " in class " + typeNameString + " ", pos);
        return funMap.get(s);
    }
}
