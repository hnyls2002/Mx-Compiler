package Middleend.IROptimize;

import IR.IRModule;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class Glo2Loc implements IRModulePass, IRFnPass {

    @Override
    public void runOnIRFn(IRFn fn) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runOnIRFn'");
    }

    @Override
    public void runOnIRModule(IRModule irModule) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runOnIRModule'");
    }

}
