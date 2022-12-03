package ASM;

import java.util.ArrayList;

import ASM.ASMOprand.ASMConstStr;
import ASM.ASMOprand.ASMGlobalVar;

public class ASMModule {
    public ArrayList<ASMGlobalVar> globalVarList = new ArrayList<>();
    public ArrayList<ASMConstStr> constStrList = new ArrayList<>();
    public ArrayList<ASMFn> fnList = new ArrayList<>();
}
