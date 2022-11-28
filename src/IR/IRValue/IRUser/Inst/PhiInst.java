package IR.IRValue.IRUser.Inst;

import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;

public class PhiInst extends IRBaseInst {

    public IRBasicBlock block1, block2;
    public IRBaseValue res1, res2;

    public PhiInst(IRBasicBlock block1, IRBaseValue res1, IRBasicBlock block2, IRBaseValue res2,
            IRBasicBlock curBlock) {
        super(res1.valueType);
        this.block1 = block1;
        this.res1 = res1;
        this.block2 = block2;
        this.res2 = res2;
        if (curBlock != null)
            curBlock.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = "phi " + valueType.toString() + " ";
        ret += "[ " + res1.useToString() + ", " + block1.useToString() + " ], ";
        ret += "[ " + res2.useToString() + ", " + block2.useToString() + " ]";
        return ret;
    }
}