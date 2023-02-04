package IR.IRValue;

import java.util.ArrayList;
import java.util.LinkedList;

import IR.IRType.IRType;
import IR.IRType.IRType.IRTypeId;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import Middleend.IROptimize.DominateTree.DTreeNode;

public class IRBasicBlock extends IRBaseValue {
    private IRBaseInst terminal = null;
    public ArrayList<IRBaseInst> instList = new ArrayList<>();
    public IRBasicBlock tailBlock;

    // for spill
    public int loopNum = 0;

    // CFG
    public LinkedList<IRBasicBlock> preList = new LinkedList<>(), sucList = new LinkedList<>();

    // DominateTree
    public DTreeNode dtNode = new DTreeNode();

    public IRBasicBlock(IRFn fn, int loopNum) {
        super(new IRType(IRTypeId.LabelTypeId));
        this.tailBlock = this;
        this.loopNum = loopNum;
        fn.addBlock(this);
    }

    public IRBasicBlock(int loopNum) {
        super(new IRType(IRTypeId.LabelTypeId));
        this.tailBlock = this;
        this.loopNum = loopNum;
    }

    public static void addRetBlock(IRFn fn) {
        var retBlock = new IRBasicBlock(0);
        fn.retBlock = retBlock;
    }

    public boolean isNamed() {
        return nameString != null;
    }

    // always set at the end of a block
    public void setTerminal(IRBaseInst terminal) {
        if (this.terminal == null) // if not terminated...
            this.terminal = terminal;
    }

    public IRBaseInst getTerminal() {
        return this.terminal;
    }

    public void addInst(IRBaseInst inst) {
        if (this.terminal == null) // if not terminated...
            instList.add(inst);
    }

    public IRBasicBlock getTail() {
        return tailBlock == this ? this : (tailBlock = tailBlock.getTail());
    }

    @Override
    public String useToStringWithType() {
        return "label %" + getName();
    }

    @Override
    public String defToString() {
        return getName() + ":";
    }

    @Override
    public String useToString() {
        return "%" + getName();
    }
}
