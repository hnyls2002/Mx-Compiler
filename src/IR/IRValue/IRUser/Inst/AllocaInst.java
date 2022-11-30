package IR.IRValue.IRUser.Inst;

import IR.IRType.IRPtType;
import IR.IRType.IRType;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;

public class AllocaInst extends IRBaseInst {

    public IRType elementType;

    public AllocaInst(IRType elemenType, IRFn curFn) {
        super(new IRPtType(elemenType, 1));
        this.elementType = elemenType;
        var block = curFn.blockList.get(0);
        for (int i = 0; i <= block.instList.size(); ++i)
            if (i == block.instList.size() || !(block.instList.get(i) instanceof AllocaInst)) {
                block.instList.add(i, this);
                break;
            }
    }

    @Override
    public String defToString() {
        return "alloca " + elementType.toString();
    }
}
