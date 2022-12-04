package ASM;

import java.util.ArrayList;

import ASM.ASMInst.ASMBaseInst;
import ASM.ASMOprand.BaseOprand;

public class ASMBlock extends BaseOprand {
    public int blockId, fnId;
    public ArrayList<ASMBaseInst> instList = new ArrayList<>();

    public ASMBlock(int blockId, int fnId) {
        this.blockId = blockId;
        this.fnId = fnId;
    }

    public void addInst(ASMBaseInst inst) {
        instList.add(inst);
    }

    @Override
    public String format() {
        if (blockId == 0)
            return "# %bb.0";
        return String.format(".LBB%d_%d", fnId, blockId);
    }
}
