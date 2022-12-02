package Share.Pass;

import IR.IRValue.IRBasicBlock;

public interface IRBlockPass {
    public void runOnBlock(IRBasicBlock block);
}
