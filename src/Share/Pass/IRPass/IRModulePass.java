package Share.Pass.IRPass;

import IR.IRModule;

public interface IRModulePass {
    public void runOnIRModule(IRModule irModule);
}
