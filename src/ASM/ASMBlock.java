package ASM;

import java.util.ArrayList;

import ASM.ASMInst.ASMBaseInst;

public class ASMBlock {
    public int blockId;
    public ArrayList<ASMBaseInst> instList = new ArrayList<>();

    public ASMBlock(int blockId) {
        this.blockId = blockId;
    }

    public void addInst(ASMBaseInst inst) {
        instList.add(inst);
    }
}
