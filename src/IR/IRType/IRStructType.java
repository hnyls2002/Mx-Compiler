package IR.IRType;

import java.util.ArrayList;
import java.util.HashMap;

// reference to https://llvm.org/doxygen/classllvm_1_1StructType.html

public class IRStructType extends IRType {
    public String className;

    public boolean isSolid = false;
    public ArrayList<IRType> fieldTypeList = new ArrayList<>();

    public HashMap<String, Integer> fieldIdxMap = new HashMap<>();
    public IRFnType constructFnType = null;

    public IRStructType(String className) {
        super(IRTypeId.StructTypeId);
        this.className = "struct." + className;
        this.isSolid = true;
    }

    public IRStructType getProtoStructType(String className) {
        var proto = new IRStructType(className);
        proto.isSolid = false;
        return proto;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IRStructType t)
            return this.className.equals(t.className);
        return false;
    }

    public String getClassName() {
        return "%" + className;
    }

    public String typeDefToString() {
        var ret = "type { ";
        for (var fieldType : fieldTypeList)
            ret += fieldType.toString() + ", ";
        if (!fieldTypeList.isEmpty())
            ret = ret.substring(0, ret.length() - 2);
        ret += " }";
        return ret;
    }

    @Override
    public String toString() {
        return getClassName();
    }

    @Override
    public int getSize() {
        int sum = 0;
        for (var ty : fieldTypeList)
            sum += ty.getSize();
        return sum;
    }

}