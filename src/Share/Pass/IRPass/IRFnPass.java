package Share.Pass.IRPass;

import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;

public interface IRFnPass {
    public void runOnIRFn(IRFn fn);
}
