package IR.IRType;

import java.util.ArrayList;

// function signatures

public class IRFnType extends IRBaseType {
    public String fnName;
    public IRBaseType retType;
    public ArrayList<IRBaseType> argumentList = new ArrayList<>();

    public IRFnType(String fnName) {
        this.fnName = fnName;
    }

}
