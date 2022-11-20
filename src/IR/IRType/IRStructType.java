package IR.IRType;

import java.util.ArrayList;

import IR.Util.InfoManager;

// reference to https://llvm.org/doxygen/classllvm_1_1StructType.html

public class IRStructType extends IRBaseType {
    public String className;

    public boolean isSolid = false;
    public ArrayList<IRFnType> fnList = null;
    public ArrayList<IRBaseType> fieldList = null;

    public InfoManager infoManager = null;

    public IRStructType(String className) {
        this.className = className;
    }

    public static IRStructType create(String className) {
        var ret = new IRStructType(className);
        return ret;
    }

    public void link(InfoManager infoManager) {
        this.infoManager = infoManager;
    }

    public IRStructType getSolid() {
        var tmp = infoManager.getSolidStruct(className);
        this.isSolid = true;
        this.fnList = tmp.fnList;
        this.fieldList = tmp.fieldList;
        return tmp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IRStructType t)
            return this.className == t.className;
        return false;
    }

}