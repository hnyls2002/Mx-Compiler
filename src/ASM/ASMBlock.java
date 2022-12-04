package ASM;

import java.util.ArrayList;

import ASM.ASMInst.ASMBaseInst;
import ASM.ASMOprand.BaseOprand;

public class ASMBlock extends BaseOprand {
    public int blockId;
    public ArrayList<ASMBaseInst> instList = new ArrayList<>();

    public ASMBlock(int blockId) {
        this.blockId = blockId;
    }

    public void addInst(ASMBaseInst inst) {
        instList.add(inst);
    }
}
