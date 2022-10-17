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

}
