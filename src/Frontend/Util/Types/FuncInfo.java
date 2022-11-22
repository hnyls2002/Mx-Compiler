package Frontend.Util.Types;

import java.util.ArrayList;

import Frontend.Util.Position;
import Frontend.Util.TypeIdPair;
import Frontend.Util.TypeName;
import IR.IRValue.IRBaseValue;

public class FuncInfo {
    public TypeName retType;
    public String funcName;
    public ArrayList<TypeIdPair> paraList = new ArrayList<>();
    public Position pos;
    public IRBaseValue val;

    public FuncInfo(TypeName ty, String funcName, Position pos) {
        retType = ty;
        this.funcName = funcName;
        this.pos = pos;
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "FUNCTION : " + funcName + "\n";
        ret += "parameter list : ";
        for (int i = 0; i < paraList.size(); ++i)
            ret += paraList.get(i).toString() + (i == paraList.size() - 1 ? "\n" : ",");
        if (paraList.isEmpty())
            ret += "\n";
        ret += "FUNCTION END\n";
        return ret;
    }

}
