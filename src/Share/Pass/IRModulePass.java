package Share.Pass;

import IR.IRModule;

public interface IRModulePass {
    public void runOnIRModule(IRModule irModule);
}
