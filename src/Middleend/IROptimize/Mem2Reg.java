package Middleend.IROptimize;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import IR.IRModule;
import IR.IRType.IRPtType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.AllocaInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.PhiInst;
import IR.IRValue.IRUser.IRInst.StoreInst;
import Middleend.IROptimize.Tools.DTBuilder;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class Mem2Reg implements IRModulePass, IRFnPass {

    @Override
    public void runOnIRModule(IRModule irModule) {
        new DTBuilder().buildDT(irModule, false);
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        HashMap<AllocaInst, HashSet<IRBasicBlock>> defSetMap = new HashMap<>();
        HashMap<AllocaInst, HashSet<IRBasicBlock>> DFSetMap = new HashMap<>();

        // get the def set of each alloca
        for (var bb : fn.blockList)
            for (var inst : bb.instList)
                if (inst instanceof StoreInst sInst && sInst.getOprand(1) instanceof AllocaInst aInst) {
                    if (!defSetMap.containsKey(aInst))
                        defSetMap.put(aInst, new HashSet<>());
                    defSetMap.get(aInst).add(bb);
                }

        // calcuate the DF set of each alloca
        var it = defSetMap.entrySet().iterator();
        while (it.hasNext()) {
            var entry = it.next();
            var aInst = entry.getKey();
            var defSet = entry.getValue();

            HashSet<IRBasicBlock> DFSet = new HashSet<>();
            defSet.forEach(bb -> DFSet.addAll(bb.dtNode.frontier));

            while (true) {
                HashSet<IRBasicBlock> newDFSet = new HashSet<>();
                DFSet.forEach(bb -> newDFSet.addAll(bb.dtNode.frontier));
                if (DFSet.containsAll(newDFSet))
                    break;
                DFSet.addAll(newDFSet);
            }
            DFSetMap.put(aInst, DFSet);
        }

        // add phi node for each alloca
        DFSetMap.forEach((aInst, DFSet) -> {
            DFSet.forEach(bb -> {
                var phiInst = new PhiInst(aInst.elementType, bb);
                phiInst.belongAlloca = aInst;
            });
        });

        renamePhi(fn);
    }

    public void renamePhi(IRFn fn) {
        /*
         * according to the dfs order of the CFG (worklist algorithm)
         * for every block, calculate the incoming definition of each alloca
         * then update the phi node in the successor of current block
         */

        /*
         * incomingDef is just one way to touch the block
         * As current block would be updated by all of it's predecessors
         * so that method is correct
         */
        HashMap<IRBasicBlock, HashMap<AllocaInst, IRBaseValue>> incomingDef = new HashMap<>();

        Queue<IRBasicBlock> workList = new LinkedList<>();
        var root = fn.blockList.get(0);
        workList.add(root);

        // for the undefined usage of alloca, we give it a 0/NULL value
        HashMap<AllocaInst, IRBaseValue> initDef = new HashMap<>();
        for (var inst : root.instList)
            if (inst instanceof AllocaInst aInst) {
                if (aInst.elementType instanceof IRPtType)
                    initDef.put(aInst, new NullConst());
                else
                    initDef.put(aInst, new IntConst(0, 32));
            }
        incomingDef.put(root, initDef);

        while (!workList.isEmpty()) {
            var block = workList.poll();
            var defMap = incomingDef.get(block);

            for (var pInst : block.phiList) {
                if (pInst.belongAlloca != null)
                    defMap.put(pInst.belongAlloca, pInst);
            }

            var it = block.instList.iterator();
            while (it.hasNext()) {
                var inst = it.next();
                if (inst instanceof AllocaInst)
                    it.remove();
                else if (inst instanceof StoreInst sInst && sInst.getOprand(1) instanceof AllocaInst aInst) {
                    defMap.put(aInst, sInst.getOprand(0));
                    it.remove();
                } else if (inst instanceof LoadInst lInst && lInst.getOprand(0) instanceof AllocaInst aInst) {
                    lInst.replaceAllUseWith(defMap.get(aInst));
                    it.remove();
                }
            }
            for (var suc : block.sucList) {
                for (var pInst : suc.phiList) {
                    if (pInst.belongAlloca != null) {
                        var res = defMap.get(pInst.belongAlloca);
                        pInst.addBranch(block, res);
                    }
                }
                if (incomingDef.containsKey(suc))
                    continue;
                HashMap<AllocaInst, IRBaseValue> nextMap = new HashMap<>(defMap);
                workList.add(suc);
                incomingDef.put(suc, nextMap);
            }
        }
    }
}
