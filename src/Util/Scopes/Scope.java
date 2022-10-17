package Util.Scopes;

import java.util.HashMap;

import Util.TypeIdPair;

public class Scope {
    public HashMap<String, TypeIdPair> DefMap = new HashMap<>(); // the DefMap only stores the typename literally

    public TypeIdPair getDef(String s) {
        return DefMap.get(s);
    }

}
