package IR.IRType;

import java.util.ArrayList;

import IR.IRModule;
import Share.MyException;

// reference to https://llvm.org/doxygen/classllvm_1_1StructType.html

public class IRStructType extends IRType {
    public String className;

    // proto StructType only used (maybe) during calculate the size
    // before calcutelate the size, replace the prototype with concret type
    public boolean isSolid = false;
    public ArrayList<IRType> fieldTypeList = new ArrayList<>();

    public IRFnType constructFnType = null;

    public IRStructType(String className) {
        super(IRTypeId.StructTypeId);
        this.className = "struct." + className;
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
    public int getSize(IRModule irModule) {
        if (!isSolid) {
            IRStructType solidStructType = null;
            for (var st : irModule.classList)
                if (st.className.equals(className)) {
                    solidStructType = st;
                    break;
                }
            if (solidStructType == null)
                throw new MyException("Can not find struct " + className);
            this.isSolid = true;
            this.constructFnType = solidStructType.constructFnType;
            this.fieldTypeList = solidStructType.fieldTypeList;
        }
        int sum = 0;
        for (var ty : fieldTypeList)
            sum += ty.getSize(irModule);
        return sum;
    }

}