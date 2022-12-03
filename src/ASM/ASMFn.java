package ASM;

import java.util.ArrayList;

import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;

public class ASMFn {
    public String name;
    public ArrayList<ASMBlock> blockList = new ArrayList<>();

    public ASMFn(IRFn irFn) {
        this.name = irFn.getName();
    }

}
