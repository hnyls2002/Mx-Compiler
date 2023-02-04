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
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class Mem2Reg implements IRModulePass, IRFnPass {

    @Override
    public void runOnIRModule(IRModule irModule) {
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        HashMap<AllocaInst, HashSet<IRBasicBlock>> defBlockMap = new HashMap<>();
        new DominateTree().runOnIRFn(fn);

        // get all alloca def
        // return block wouln't have store inst
        // terminal is not store inst
        for (var bb : fn.blockList) {
            for (var inst : bb.instList) {
                if (inst instanceof StoreInst sInt && sInt.destAddr instanceof AllocaInst aInst) {
                    if (!defBlockMap.containsKey(aInst))
                        defBlockMap.put(aInst, new HashSet<>());
                    defBlockMap.get(aInst).add(bb);
                }
            }
        }

        // add phi insst
        var it = defBlockMap.entrySet().iterator();
        while (it.hasNext()) {
            var entry = it.next();
            var aInst = entry.getKey();
            var defSet = entry.getValue();
            HashSet<IRBasicBlock> workSet = new HashSet<>(defSet);
            HashSet<IRBasicBlock> hadPhi = new HashSet<>();

            while (!workSet.isEmpty()) {
                var tmpIt = workSet.iterator();
                var defBlock = tmpIt.next();
                tmpIt.remove();
                for (var newDef : defBlock.dtNode.frontier) {
                    if (!hadPhi.contains(newDef)) {
                        newDef.instList.add(0, new PhiInst(aInst));
                        hadPhi.add(newDef);
                        if (!defSet.contains(newDef)) {
                            workSet.add(newDef);
                            defSet.add(newDef);
                        }
                    }
                }
            }
        }

        renamePhi(fn);
    }

    private void replaceAllUse(IRFn fn, IRBaseValue oldUse, IRBaseValue newUse) {
        fn.blockList.forEach(block -> {
            block.instList.forEach(inst -> inst.replaceUse(oldUse, newUse));
            block.getTerminal().replaceUse(oldUse, newUse);
        });
        fn.retBlock.instList.forEach(inst -> inst.replaceUse(oldUse, newUse));
        fn.retBlock.getTerminal().replaceUse(oldUse, newUse);
    }

    public void renamePhi(IRFn fn) {
        HashMap<IRBasicBlock, HashMap<AllocaInst, IRBaseValue>> incomingDef = new HashMap<>();
        Queue<IRBasicBlock> workList = new LinkedList<>();
        var root = fn.blockList.get(0);
        workList.add(root);
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
            var it = block.instList.iterator();
            while (it.hasNext()) {
                var inst = it.next();
                if (inst instanceof AllocaInst)
                    it.remove();
                else if (inst instanceof StoreInst sInst && sInst.destAddr instanceof AllocaInst aInst) {
                    defMap.put(aInst, sInst.storedValue);
                    it.remove();
                } else if (inst instanceof LoadInst lInst && lInst.srcAddr instanceof AllocaInst aInst) {
                    replaceAllUse(fn, lInst, defMap.get(aInst));
                    it.remove();
                } else if (inst instanceof PhiInst pInst && pInst.allocaDef != null) {
                    defMap.put(pInst.allocaDef, pInst);
                }
            }
            for (var suc : block.sucList) {
                for (var inst : suc.instList) {
                    if (inst instanceof PhiInst pInst && pInst.allocaDef != null) {
                        var val = defMap.get(pInst.allocaDef);
                        pInst.addOprand(block, val);
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