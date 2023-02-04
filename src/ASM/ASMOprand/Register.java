package ASM.ASMOprand;

import java.util.LinkedList;

import ASM.ASMInst.ASMMoveInst;

public abstract class Register extends BaseOprand {
    public LinkedList<Register> adjList = new LinkedList<>();
    public int degree;
    public LinkedList<ASMMoveInst> moveList = new LinkedList<>();
    public Register alias;
    public PhysicalReg color;
    public StackOffset spilledPos;

    public int loopIndex = 0;

    public void coloringInit() {
        adjList.clear();
        degree = 0;
        moveList.clear();
        alias = this;
        if (this instanceof PhysicalReg p)
            color = p;
        else
            color = null;
        spilledPos = null;
        loopIndex = 0;
    }
}
