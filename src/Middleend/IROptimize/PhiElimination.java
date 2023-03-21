package Middleend.IROptimize;

import java.util.ArrayList;

import IR.IRModule;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRVReg;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.MoveInst;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class PhiElimination implements IRModulePass, IRFnPass {

    @Override
    public void runOnIRModule(IRModule irModule) {
        new CFGTransformer().splitCriticalEdge(irModule);
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        ArrayList<IRBasicBlock> tmpBlockList = new ArrayList<>(fn.blockList);
        tmpBlockList.add(fn.retBlock);

        for (var block : tmpBlockList) {
            for (var phiInst : block.phiList) {
                var temp = new IRVReg(phiInst.valueType);
                for (int i = 0; i < phiInst.getOprandNum(); i += 2) {
                    var preBlock = (IRBasicBlock) phiInst.getOprand(i);
                    var res = phiInst.getOprand(i + 1);
                    new MoveInst(temp, res, preBlock, true);
                }
                new MoveInst(phiInst, temp, block, false);
            }
        }
    }
}
