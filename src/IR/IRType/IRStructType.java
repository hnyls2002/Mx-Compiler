package IR.IRType;

import java.util.ArrayList;
import java.util.HashMap;

// reference to https://llvm.org/doxygen/classllvm_1_1StructType.html

public class IRStructType extends IRType {
    public String className;

    public boolean isSolid = false;
    public ArrayList<IRType> fieldList = null;

    public HashMap<String, Integer> fieldIdxMap;

    public IRStructType(String className) {
        super(IRTypeId.StructTypeId);
        this.className = className;
    }

    public static IRStructType create(String className) {
        var ret = new IRStructType(className);
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IRStructType t)
            return this.className == t.className;
        return false;
    }

}