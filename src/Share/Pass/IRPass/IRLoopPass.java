package Share.Pass.IRPass;

import Middleend.IROptimize.Tools.IRLoop;

public interface IRLoopPass {
    public void runOnIRLoop(IRLoop loop);
}
