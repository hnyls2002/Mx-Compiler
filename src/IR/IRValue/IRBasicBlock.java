package IR.IRValue;

import java.util.ArrayList;

import IR.IRValue.IRUser.Inst.BaseInst;

public class IRBasicBlock extends IRBaseValue {
    public BaseInst entry, teminal;
    public ArrayList<BaseInst> instList;
}
