package IR.IRValue.IRUser.Inst;

import IR.IRType.IRPtType;
import IR.IRType.IRType;
import IR.IRValue.IRBasicBlock;

public class AllocaInst extends IRBaseInst {

    public IRType elementType;

    public AllocaInst(IRType elemenType, IRBasicBlock block) {
        super(new IRPtType(elemenType, 1));
        this.elementType = elemenType;
        block.addInst(this);
    }

    @Override
    public String defToString() {
        return "alloca " + elementType.toString();
    }
}
