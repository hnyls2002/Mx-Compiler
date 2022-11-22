package IR;

import java.util.ArrayList;

import IR.IRType.IRStructType;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;

public class IRModule {
    public ArrayList<GlobalVariable> globalVarList = new ArrayList<>();
    public ArrayList<IRFn> globalFnList = new ArrayList<>();
    public ArrayList<IRFn> builtinFnList = new ArrayList<>();
    public ArrayList<IRStructType> classList = new ArrayList<>();
}
