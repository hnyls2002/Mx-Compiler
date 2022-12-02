package IR.IRValue;

import java.util.ArrayList;

import IR.IRType.IRType;
import IR.IRType.IRType.IRTypeId;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.IRBaseInst;

public class IRBasicBlock extends IRBaseValue {
    public String entryString;
    private IRBaseInst terminal = null;
    public ArrayList<IRBaseInst> instList = new ArrayList<>();
    public IRBasicBlock tailBlock;

    public IRBasicBlock(IRFn fn) {
        super(new IRType(IRTypeId.LabelTypeId));
        this.tailBlock = this;
        fn.addBlock(this);
    }

    public IRBasicBlock() {
        super(new IRType(IRTypeId.LabelTypeId));
        this.tailBlock = this;
    }

    public static void addRetBlock(IRFn fn) {
        var retBlock = new IRBasicBlock();
        fn.retBlock = retBlock;
    }

    public void setEntry(String entryString) {
        this.entryString = entryString;
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
    public String getName() {
        return entryString == null ? "entry" : entryString;
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
