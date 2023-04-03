package Middleend.IROptimize.Tools;

import java.util.ArrayList;

import IR.IRModule;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.BrInst;
import IR.IRValue.IRUser.IRInst.JumpInst;

public class InfosRebuilder {
    public void rebuildDefUse(IRModule irModule) {
        irModule.globalFnList.forEach(irFn -> rebuildDefUse(irFn));
        irModule.varInitFnList.forEach(irFn -> rebuildDefUse(irFn));
    }

    public void rebuildCFG(IRModule irModule) {
        irModule.globalFnList.forEach(irFn -> rebuildCFG(irFn));
        irModule.varInitFnList.forEach(irFn -> rebuildCFG(irFn));
    }

    public void rebuildDefUse(IRFn irFn) {
        // clear all value's user lists : value can only be used in inst
        var tempList = new ArrayList<>(irFn.blockList);
        tempList.add(irFn.retBlock);
        tempList.forEach(block -> {
            block.phiList.forEach(inst -> {
                for (int i = 0; i < inst.getOprandNum(); ++i)
                    inst.getOprand(i).userList.clear();
            });
            block.instList.forEach(inst -> {
                for (int i = 0; i < inst.getOprandNum(); ++i)
                    inst.getOprand(i).userList.clear();
            });
        });

        // rebuild def-use chain
        tempList.forEach(block -> {
            block.phiList.forEach(inst -> {
                inst.parentBlock = block;
                for (int i = 0; i < inst.getOprandNum(); ++i)
                    inst.getOprand(i).userList.add(inst);
            });
            block.instList.forEach(inst -> {
                inst.parentBlock = block;
                for (int i = 0; i < inst.getOprandNum(); ++i)
                    inst.getOprand(i).userList.add(inst);
            });
        });

    }

    public void rebuildCFG(IRFn irFn) {
        // clear CFG
        var tempList = new ArrayList<>(irFn.blockList);
        tempList.add(irFn.retBlock);
        tempList.forEach(block -> {
            block.sucList.clear();
            block.preList.clear();
        });

        // rebuild CFG
        tempList.forEach(block -> {
            block.instList.forEach(inst -> {
                inst.parentBlock = block;
                if (inst instanceof BrInst br) {
                    block.sucList.add((IRBasicBlock) br.getOprand(1));
                    block.sucList.add((IRBasicBlock) br.getOprand(2));
                    ((IRBasicBlock) br.getOprand(1)).preList.add(block);
                    ((IRBasicBlock) br.getOprand(2)).preList.add(block);
                } else if (inst instanceof JumpInst jp) {
                    block.sucList.add((IRBasicBlock) jp.getOprand(0));
                    ((IRBasicBlock) jp.getOprand(0)).preList.add(block);
                }
            });
        });
    }

}
