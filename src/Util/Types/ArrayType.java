package Util.Types;

import java.util.ArrayList;
import java.util.HashMap;

import Util.Scopes.GlobalScope;

public class ArrayType extends BaseType {

    public BaseType uniType;
    public int dimen = 0, dimenset = 0;
    public ArrayList<Integer> dimenSize = new ArrayList<>();
    public HashMap<String, FuncInfo> funMap = new HashMap<>();

    public ArrayType(GlobalScope gScope) {
        FuncInfo size = new FuncInfo(gScope.intName, "size");
        funMap.put("size", size);
    }

    public FuncInfo getFuncInfo(String s) {
        return funMap.get(s);
    }

    @Override
    public boolean isBuiltin() {
        return uniType.isBuiltin();
    }

    @Override
    public boolean isClass() {
        return uniType.isClass();
    }
}
