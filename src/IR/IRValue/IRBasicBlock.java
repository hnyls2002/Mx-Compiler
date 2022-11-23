package IR.IRValue;

import java.util.ArrayList;

import IR.IRType.IRType;
import IR.IRType.IRType.IRTypeId;
import IR.IRValue.IRUser.Inst.IRBaseInst;
import IR.IRValue.IRUser.Inst.RetInst;

public class IRBasicBlock extends IRBaseValue {
    public String entryString;
    public IRBaseInst terminal;
    public ArrayList<IRBaseInst> instList = new ArrayList<>();

    public IRBasicBlock() {
        super(new IRType(IRTypeId.LabelTypeId));
    }

    public void setEntry(String entryString) {
        this.entryString = entryString;
    }

    public void addInst(IRBaseInst inst) {
        instList.add(inst);
    }

    public static IRBasicBlock getVarInitBB() {
        var ret = new IRBasicBlock();
        ret.terminal = RetInst.createVoidRetInst();
        return ret;
    }

    @Override
    public String getName() {
        return entryString;
    }
}
