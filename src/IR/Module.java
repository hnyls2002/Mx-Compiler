package IR;

import java.util.ArrayList;

import IR.IRType.IRStructType;
import IR.IRValue.IRUser.ConsValue.GlobalValue.Fn;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;

public class Module {
    public ArrayList<GlobalVariable> globalVarList;
    public ArrayList<Fn> globalFnList;
    public ArrayList<Fn> builtinFnList;
    public ArrayList<IRStructType> classList;
}
