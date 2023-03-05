package ASM;

import java.util.ArrayList;
import java.util.HashSet;

import ASM.ASMInst.ASMBaseInst;
import ASM.ASMOprand.BaseOprand;
import ASM.ASMOprand.Register;

public class ASMBlock extends BaseOprand {

    public int blockId, fnId, loopDepth;
    public ArrayList<ASMBaseInst> instList = new ArrayList<>();

    // graph
    public ArrayList<ASMBlock> preList = new ArrayList<>();
    public ArrayList<ASMBlock> sucList = new ArrayList<>();

    // def and use
    public HashSet<Register> useSet = new HashSet<>();
    public HashSet<Register> defSet = new HashSet<>();

    // liveness
    public HashSet<Register> liveInSet = new HashSet<>();
    public HashSet<Register> liveOutSet = new HashSet<>();

    public ASMBlock(int blockId, int fnId, int loopDepth) {
        this.blockId = blockId;
        this.fnId = fnId;
        this.loopDepth = loopDepth;
    }

    // p -> n
    // use[pn] = use[p] U (use[n] - def[p])
    // def[pn] = def[p] U def[n]
    public void getDefUseSet() {
        useSet.clear();
        defSet.clear();
        for (var inst : instList) {
            var tmp = inst.getUse();
            tmp.removeAll(defSet);
            useSet.addAll(tmp);
            defSet.addAll(inst.getDef());
        }
    }

    @Override
    public String format() {
        if (blockId == 0)
            return "# %bb.0";
        return String.format(".LBB%d_%d", fnId, blockId);
    }
}
