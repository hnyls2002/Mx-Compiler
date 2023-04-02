package Middleend.IROptimize;

import java.util.ArrayList;
import java.util.HashSet;

import org.antlr.v4.runtime.misc.Pair;

import IR.IRModule;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.BinaryInst;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.CastInst;
import IR.IRValue.IRUser.IRInst.GEPInst;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import IR.IRValue.IRUser.IRInst.IcmpInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.StoreInst;
import Middleend.IROptimize.Tools.DTBuilder;
import Middleend.IROptimize.Tools.InfosRebuilder;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class CSE implements IRModulePass, IRFnPass {
    // common subexpression elimination

    public int CSECount = 0;

    @Override
    public void runOnIRModule(IRModule irModule) {
        new DTBuilder().buildDT(irModule, false);
        new InfosRebuilder().rebuildDefUse(irModule);
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
        System.err.println("CSE: \t" + CSECount + " subexpression eliminated");
    }

    HashSet<Pair<IRBaseInst, IRBaseInst>> workList = new HashSet<>();

    private void addWorkList(IRBaseInst inst1, IRBaseInst inst2) {
        if (!workList.contains(new Pair<>(inst1, inst2))
                && !workList.contains(new Pair<>(inst2, inst1)))
            workList.add(new Pair<>(inst1, inst2));
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        var blockList = new ArrayList<>(fn.blockList);
        blockList.add(fn.retBlock);
        var instList = new ArrayList<IRBaseInst>();
        for (var block : blockList)
            instList.addAll(block.instList);

        workList.clear();

        for (var inst1 : instList)
            for (var inst2 : instList)
                if (checkCS(inst1, inst2))
                    addWorkList(inst1, inst2);

        while (!workList.isEmpty()) {
            var it = workList.iterator();
            var pair = it.next();
            it.remove();
            if (!checkCS(pair.a, pair.b))
                continue;
            if (CSEliminate(pair.a, pair.b)) {
                ++CSECount;
                var domInst = pair.a.userList.isEmpty() ? pair.b : pair.a;
                for (var user1 : domInst.userList)
                    for (var user2 : domInst.userList)
                        if (checkCS((IRBaseInst) user1, (IRBaseInst) user2))
                            addWorkList((IRBaseInst) user1, (IRBaseInst) user2);
            }
        }
    }

    private boolean isOprandEqual(IRBaseValue op1, IRBaseValue op2) {
        if (op1 == op2)
            return true;
        if (op1 instanceof IntConst int1 && op2 instanceof IntConst int2)
            return int1.constValue == int2.constValue;
        return false;
    }

    private boolean checkOprands(IRBaseInst inst1, IRBaseInst inst2) {
        if (inst1.getOprandNum() != inst2.getOprandNum())
            return false;
        for (int i = 0; i < inst1.getOprandNum(); ++i)
            if (!isOprandEqual(inst1.getOprand(i), inst2.getOprand(i)))
                return false;
        return true;
    }

    private boolean CSEliminate(IRBaseInst inst1, IRBaseInst inst2) {
        if (inst1 == inst2)
            return false;

        var block1 = inst1.parentBlock;
        var block2 = inst2.parentBlock;

        if (!block1.dtNode.slaveSet.contains(block2) && !block2.dtNode.slaveSet.contains(block1))
            return false;

        if (!block1.dtNode.slaveSet.contains(block2))
            return CSEliminate(inst2, inst1);
        else if (block1 == block2 && block1.instList.indexOf(inst1) > block2.instList.indexOf(inst2))
            return CSEliminate(inst2, inst1);
        else {
            inst2.replaceAllUseWith(inst1);
            return true;
        }
    }

    private boolean checkCS(IRBaseInst inst1, IRBaseInst inst2) {
        if (inst1.getClass() != inst2.getClass() || inst1 == inst2)
            return false;

        if (inst1 instanceof BinaryInst binary1) {
            var binary2 = (BinaryInst) inst2;
            return binary1.opCode == binary2.opCode && checkOprands(binary1, binary2);
        }
        if (inst1 instanceof GEPInst gep1) {
            var gep2 = (GEPInst) inst2;
            return checkOprands(gep1, gep2);
        }
        if (inst1 instanceof IcmpInst icmp1) {
            var icmp2 = (IcmpInst) inst2;
            return icmp1.opCode == icmp2.opCode && checkOprands(icmp1, icmp2);
        }
        if (inst1 instanceof CastInst cast1) {
            var cast2 = (CastInst) inst2;
            return cast1.opCode == cast2.opCode
                    && cast1.targetType.equals(cast2.targetType)
                    && checkOprands(cast1, cast2);
        }
        if (inst1 instanceof CallInst) {
            return false;
        }
        if (inst1 instanceof LoadInst) {
            return false;
        }
        if (inst1 instanceof StoreInst) {
            return false;
        }
        return false;
    }
}
