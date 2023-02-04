package ASM;

import java.util.ArrayList;
import java.util.HashSet;

import ASM.ASMInst.ASMBaseInst;
import ASM.ASMOprand.BaseOprand;
import ASM.ASMOprand.Register;

public class ASMBlock extends BaseOprand {
    public int blockId, fnId;
    public ArrayList<ASMBaseInst> instList = new ArrayList<>();

    // for to-spill node choose
    public int loopNum = 0;

    // for CFG and liveness analyzer
    public ArrayList<ASMBlock> preList = new ArrayList<>(), sucList = new ArrayList<>();
    public HashSet<Register> useSet = new HashSet<>(), defSet = new HashSet<>(),
            liveIn = new HashSet<>(), liveOut = new HashSet<>();

    public ASMBlock(int blockId, int fnId, int loopNum) {
        this.blockId = blockId;
        this.fnId = fnId;
        this.loopNum = loopNum;
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
}
