package Middleend.IROptimize;

import java.util.ArrayList;

import IR.IRModule;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRVReg;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.JumpInst;
import IR.IRValue.IRUser.IRInst.MoveInst;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class PhiElimination implements IRModulePass, IRFnPass {

    @Override
    public void runOnIRModule(IRModule irModule) {
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        splitCriticalEdge(fn);
        addMovement(fn);
    }

    public void splitCriticalEdge(IRFn fn) {
        ArrayList<IRBasicBlock> tmpAddList = new ArrayList<>();
        for (var fromBlock : fn.blockList) {
            if (fromBlock.sucList.size() <= 1)
                continue;
            for (var toBlock : fromBlock.sucList) {
                if (toBlock.preList.size() <= 1)
                    continue;
                // we now find a critical edge fromBlock -> toBlock
                assert fromBlock.sucList.size() == 2 && toBlock.preList.size() == 2;

                var insertBlock = new IRBasicBlock(toBlock.loopDepth);
                tmpAddList.add(insertBlock);

                // replace CFG graph
                for (int i = 0; i < fromBlock.sucList.size(); ++i)
                    if (fromBlock.sucList.get(i) == toBlock) {
                        fromBlock.sucList.set(i, insertBlock);
                        insertBlock.preList.add(fromBlock);
                    }
                for (int i = 0; i < toBlock.preList.size(); ++i)
                    if (toBlock.preList.get(i) == fromBlock) {
                        toBlock.preList.set(i, insertBlock);
                        insertBlock.sucList.add(toBlock);
                    }

                // modify phi inst
                for (var phiInst : toBlock.phiList)
                    for (int i = 0; i < phiInst.getOprandNum(); ++i)
                        if (phiInst.getOprand(i) == fromBlock)
                            phiInst.setOprand(i, insertBlock);

                // modify terminator
                for (int i = 0; i < fromBlock.terminal.getOprandNum(); ++i)
                    if (fromBlock.terminal.getOprand(i) == toBlock)
                        fromBlock.terminal.setOprand(i, insertBlock);

                insertBlock.terminal = new JumpInst(toBlock);
                insertBlock.terminal.parentBlock = insertBlock;
                insertBlock.instList.add(insertBlock.terminal);
            }
        }

        fn.blockList.addAll(tmpAddList);
    }

    public void addMovement(IRFn fn) {
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
