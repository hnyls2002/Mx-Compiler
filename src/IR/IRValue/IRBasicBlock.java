package IR.IRValue;

import java.util.ArrayList;

import IR.IRType.IRType;
import IR.IRType.IRType.IRTypeId;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.BrInst;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import IR.IRValue.IRUser.IRInst.JumpInst;
import IR.IRValue.IRUser.IRInst.PhiInst;
import IR.IRValue.IRUser.IRInst.RetInst;
import Middleend.IROptimize.DominateTree.DTreeNode;
import Share.MyException;

public class IRBasicBlock extends IRBaseValue {
    public IRBaseInst terminal = null; // terminal instruction and also contained in instList
    public ArrayList<IRBaseInst> instList = new ArrayList<>();
    public ArrayList<PhiInst> phiList = new ArrayList<>();
    public IRBasicBlock tailBlock;

    // a loop counter : for spill
    public int loopDepth = 0;

    // for CFG
    public ArrayList<IRBasicBlock> sucList = new ArrayList<>(), preList = new ArrayList<>();

    // for mem2reg
    public DTreeNode dtNode = new DTreeNode();

    public IRBasicBlock(IRFn fn, int loopDepth) {
        super(new IRType(IRTypeId.LabelTypeId));
        this.tailBlock = this;
        this.loopDepth = loopDepth;
        fn.blockList.add(this);
    }

    public IRBasicBlock(int loopDepth) {
        super(new IRType(IRTypeId.LabelTypeId));
        this.tailBlock = this;
        this.loopDepth = loopDepth;
    }

    // always set at the end of a block
    public void setTerminal(IRBaseInst terminal) {
        if (this.terminal == null) { // if not terminated...
            this.terminal = terminal;
            instList.add(terminal);

            if (terminal instanceof JumpInst) {
                var targetBlock = (IRBasicBlock) terminal.getOprand(0);
                sucList.add(targetBlock);
                targetBlock.preList.add(this);
            } else if (terminal instanceof BrInst) {
                var targetBlock1 = (IRBasicBlock) terminal.getOprand(1);
                var targetBlock2 = (IRBasicBlock) terminal.getOprand(2);
                sucList.add(targetBlock1);
                targetBlock1.preList.add(this);
                sucList.add(targetBlock2);
                targetBlock2.preList.add(this);
            } else if (!(terminal instanceof RetInst))
                throw new MyException("Wrong terminal instruction");
        }
    }

    public void addInst(IRBaseInst inst) {
        if (this.terminal == null) // if not terminated...
            instList.add(inst);
    }

    public void addInstBeforeTerminal(IRBaseInst inst) {
        for (int i = 0; i <= instList.size(); ++i)
            if (i == instList.size() || instList.get(i) == terminal) {
                instList.add(i, inst);
                return;
            }
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
