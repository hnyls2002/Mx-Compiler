package ASM;

import java.util.ArrayList;

import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;

public class ASMFn {
    public String name;
    public ArrayList<ASMBlock> blockList = new ArrayList<>();
    public int allocaCnt = 0;
    public int spilledArgMax = 0;
    public int phiStackCnt = 0;
    public int stackRegCnt = 0;

    public ASMFn(IRFn irFn) {
        this.name = irFn.getName();
    }

}
