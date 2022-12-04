package Share.Pass.IRPass;

import IR.IRValue.IRBasicBlock;

public interface IRBlockPass {
    public void runOnIRBlock(IRBasicBlock block);
}
