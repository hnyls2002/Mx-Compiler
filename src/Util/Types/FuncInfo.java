package Util.Types;

import java.util.ArrayList;

import Util.TypeIdPair;
import Util.TypeName;

public class FuncInfo {
    public TypeName retType;
    public String funcName;
    public ArrayList<TypeIdPair> paraList = new ArrayList<>();

    public FuncInfo(TypeName ty, String funcName) {
        retType = ty;
        this.funcName = funcName;
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "FUNCTION : " + funcName + "\n";
        ret += "parameter list : ";
        for (int i = 0; i < paraList.size(); ++i)
            ret += paraList.get(i).toString() + (i == paraList.size() - 1 ? "\n" : ",");
        ret += "FUNCTION END\n";
        return ret;
    }

}
