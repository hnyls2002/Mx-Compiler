package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRPtType;
import IR.IRType.IRType;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import Share.MyException;
import Share.Visitors.IRInstVisitor;

public class AllocaInst extends IRBaseInst {

    public IRType elementType;

    public AllocaInst(IRType elemenType, IRFn curFn) {
        super(new IRPtType(elemenType, 1), curFn.blockList.get(0));
        this.elementType = elemenType;
        var block = curFn.blockList.get(0);
        // insert allocaInst at the beginning of the first block
        for (int i = 0; i <= block.instList.size(); ++i)
            if (i == block.instList.size() || !(block.instList.get(i) instanceof AllocaInst)) {
                block.instList.add(i, this);
                break;
            }
    }

    @Override
    public String formatDef() {
        return "alloca " + elementType.formatType();
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        throw new MyException("AllocaInst.accept() should not be called");
    }

    @Override
    public IRBaseInst copy() {
        throw new MyException("No allocaInst in inlining phase");
    }
}
