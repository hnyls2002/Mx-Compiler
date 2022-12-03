package ASM;

import java.util.ArrayList;

import ASM.ASMGlobal.ASMConstStr;
import ASM.ASMGlobal.ASMGlobalVar;

public class ASMModule {
    public ArrayList<ASMGlobalVar> globalVarList = new ArrayList<>();
    public ArrayList<ASMConstStr> constStrList = new ArrayList<>();
    public ArrayList<ASMFn> fnList = new ArrayList<>();
}
