package IR;

import java.util.ArrayList;

import ASM.ASMModule;
import IR.IRType.IRFnType;
import IR.IRType.IRStructType;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.ConsValue.ConsData.StrConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;

public class IRModule {
    public ArrayList<GlobalVariable> globalVarList = new ArrayList<>();
    public ArrayList<IRFn> globalFnList = new ArrayList<>();
    public ArrayList<IRFnType> builtinFnList = new ArrayList<>();
    public ArrayList<IRStructType> classList = new ArrayList<>();
    public ArrayList<StrConst> constStrList = new ArrayList<>();
    public ArrayList<IRFn> varInitFnList = new ArrayList<>();

    // link to ASM
    public ASMModule asmModule;
}
