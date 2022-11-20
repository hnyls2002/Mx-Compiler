package IR.Util;

import java.util.HashMap;

import IR.IRType.IRStructType;

public class InfoManager {
    public HashMap<String, IRStructType> structInfoMap;

    public IRStructType getSolidStruct(String nameString){
        return structInfoMap.get(nameString);
    }
}
