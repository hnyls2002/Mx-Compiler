package Middleend;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.antlr.v4.runtime.misc.Pair;

import IR.IRModule;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.BinaryInst;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import IR.IRValue.IRUser.IRInst.IcmpInst;
import IR.IRValue.IRUser.IRInst.PhiInst;
import Middleend.IROptimize.Tools.IRLoop;
import Middleend.IROptimize.Tools.InfosRebuilder;
import Middleend.IROptimize.Tools.LoopAnalyzer;
import Share.MyException;
import Share.Lang.LLVMIR.BOP;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRLoopPass;
import Share.Pass.IRPass.IRModulePass;

public class IVT implements IRModulePass, IRFnPass, IRLoopPass {
    /*
     * induction variable transformation
     * 
     * just do strength reduction to every IV respectively
     * no need to worry about the redundant code
     * CSE will eliminate all
     * 
     */

    @Override
    public void runOnIRModule(IRModule irModule) {
        new LoopAnalyzer().runOnIRModule(irModule);
        new InfosRebuilder().rebuildDefUse(irModule);
        new InfosRebuilder().rebuildCFG(irModule);
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
        System.err.println("IVT: \t" + strengthReductionCnt + " strength reduction\n\t" + basicIVEliminationCnt
                + " basic IV elimination");
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        fn.topLoopList.forEach(this::runOnIRLoop);
    }

    public static class IV {
        // x = a * i + b
        // initValue = a * i.initValue + b
        // stepValue = a * i.stepValue
        IRBaseValue initValue, stepValue;
        IRBaseInst stepInst;
        BOP stepOpcode; // add or sub step
        IRBasicBlock stepBlock;

        boolean isBasicIV;

        IRBaseValue inst0, mul0, bias0; // mul and bias can only have one
        BOP opCode0; // add sub or mul

        public IV(IRBaseValue initValue, IRBaseValue stepValue, IRBaseInst stepInst, BOP stepOpcode,
                IRBasicBlock stepBlock,
                boolean isBasicIV) {
            this.initValue = initValue;
            this.stepValue = stepValue;
            this.stepInst = stepInst;
            this.stepOpcode = stepOpcode;
            this.stepBlock = stepBlock;
            this.isBasicIV = isBasicIV;
        }

        public IV(IRBaseValue inst0, IRBaseValue mul0, IRBaseValue bias0, BOP opcode0) {
            this.inst0 = inst0;
            this.mul0 = mul0;
            this.bias0 = bias0;
            this.opCode0 = opcode0;
            this.isBasicIV = false;
        }
    }

    HashMap<IRBaseInst, IV> inst2IV = new HashMap<>();
    HashMap<IRBaseInst, PhiInst> strengthReductionMap = new HashMap<>();
    int strengthReductionCnt = 0, basicIVEliminationCnt = 0;

    @Override
    public void runOnIRLoop(IRLoop loop) {
        inst2IV.clear();

        loop.createPreHeader();

        collectBasicIV(loop);
        collectDerivedIV(loop);

        inst2IV.keySet().forEach(inst -> ivTransform(loop, inst));

        inst2IV.forEach((inst, iv) -> strengthReduction(loop, inst, iv));

        basicIVElimination(loop);

        loop.subLoops.forEach(this::runOnIRLoop);
    }

    // simple elimination : only eliminate the icmp inst
    private void basicIVElimination(IRLoop loop) {
        HashSet<IRBaseInst> eliminated = new HashSet<>();
        HashMap<IcmpInst, Pair<Integer, Pair<IRBaseValue, IRBaseValue>>> replaceMap = new HashMap<>();

        for (var derive : inst2IV.entrySet()) {
            var div = derive.getValue();
            var dPhi = strengthReductionMap.get(derive.getKey());
            if (!div.isBasicIV && inst2IV.get(div.inst0).isBasicIV) {
                var bivInst = (IRBaseInst) div.inst0;
                if (eliminated.contains(bivInst))
                    continue;
                eliminated.add(bivInst);

                for (var user : bivInst.userList)
                    if (user instanceof IcmpInst icmp) {
                        // System.err.println("eliminate " + icmp.formatDef());
                        if (loop.checkInvariant(icmp.lhs()) || loop.checkInvariant(icmp.rhs())) {
                            int idx = loop.checkInvariant(icmp.lhs()) ? 0 : 1;
                            var bound = icmp.getOprand(idx);
                            if (div.opCode0 == BOP.add || div.opCode0 == BOP.sub) {
                                var newBound = new BinaryInst(div.opCode0, bound, div.bias0, null);
                                loop.preHeader.addInstBeforeTerminal(newBound);
                                replaceMap.put(icmp, new Pair<>(idx, new Pair<>(newBound, dPhi)));
                            } else if (div.opCode0 == BOP.mul) {
                                var newBound = new BinaryInst(BOP.mul, bound, div.mul0, null);
                                loop.preHeader.addInstBeforeTerminal(newBound);
                                replaceMap.put(icmp, new Pair<>(idx, new Pair<>(newBound, dPhi)));
                            }
                        }
                    }
            }
        }

        for (var entry : replaceMap.entrySet()) {
            var icmp = entry.getKey();
            var idx = entry.getValue().a;
            var value = entry.getValue().b;
            icmp.setOprand(idx, value.a);
            icmp.setOprand(1 - idx, value.b);
            ++basicIVEliminationCnt;
        }
    }

    private void strengthReduction(IRLoop loop, IRBaseInst inst, IV iv) {
        if (iv.isBasicIV)
            return;
        var phi = new PhiInst(iv.initValue.valueType, loop.header);
        phi.addBranch(loop.preHeader, iv.initValue);
        var stepInst = new BinaryInst(iv.stepOpcode, phi, iv.stepValue, null);
        iv.stepBlock.addInstBeforeTerminal(stepInst);
        phi.addBranch(iv.stepBlock, stepInst);
        strengthReductionMap.put(inst, phi);
        inst.replaceAllUseWith(phi);
        ++strengthReductionCnt;
    }

    private void ivTransform(IRLoop loop, IRBaseInst inst) {
        var iv = inst2IV.get(inst);
        if (iv.initValue != null)
            return;

        var inst0 = (IRBaseInst) iv.inst0;
        var iv0 = inst2IV.get(inst0);
        ivTransform(loop, inst0);

        if (iv0.initValue == null)
            throw new MyException("IVT: " + inst0.formatDef() + " is not derived successfully");

        if (iv.opCode0 == BOP.add || iv.opCode0 == BOP.sub) { // x + b or x - b
            iv.initValue = new BinaryInst(iv.opCode0, iv0.initValue, iv.bias0, null);
            iv.stepValue = iv0.stepValue;
            iv.stepOpcode = iv0.stepOpcode;
            iv.stepBlock = iv0.stepBlock;
            loop.preHeader.addInstBeforeTerminal((IRBaseInst) iv.initValue);
        } else if (iv.opCode0 == BOP.mul) { // x * a
            iv.initValue = new BinaryInst(BOP.mul, iv0.initValue, iv.mul0, null);
            iv.stepValue = new BinaryInst(BOP.mul, iv0.stepValue, iv.mul0, null);
            iv.stepOpcode = iv0.stepOpcode;
            iv.stepBlock = iv0.stepBlock;
            loop.preHeader.addInstBeforeTerminal((IRBaseInst) iv.initValue);
            loop.preHeader.addInstBeforeTerminal((IRBaseInst) iv.stepValue);
        }

    }

    /*
     * all IV is contained in the header block of the loop
     * x = phi (init, x + c)
     */
    private void collectBasicIV(IRLoop loop) {
        for (var phi : loop.header.phiList) {
            if (phi.getOprandNum() != 4)
                continue;
            var initIdx = loop.contents.contains(phi.getOprand(0)) ? 2 : 0;
            var stepIdx = initIdx == 0 ? 2 : 0;
            var initValue = phi.getOprand(initIdx + 1);
            var stepValue = phi.getOprand(stepIdx + 1);
            if (stepValue instanceof BinaryInst stepInst) {
                if (stepInst.opCode == BOP.add || stepInst.opCode == BOP.sub) {
                    if (stepInst.lhs() == phi && loop.checkInvariant(stepInst.rhs())) {
                        var iv = new IV(initValue, stepInst.rhs(), stepInst, stepInst.opCode, stepInst.parentBlock,
                                true);
                        inst2IV.put(phi, iv);
                    } else if (stepInst.rhs() == phi && loop.checkInvariant(stepInst.lhs())
                            && stepInst.opCode == BOP.add) {
                        var iv = new IV(initValue, stepInst.lhs(), stepInst, stepInst.opCode, stepInst.parentBlock,
                                true);
                        inst2IV.put(phi, iv);
                    }
                }
            }
        }
    }

    /*
     * y = a * x + b, where x is an IV
     * 
     * two forms :
     * 
     * 1) y = a * x --> b is null
     * 
     * 2) y = x1 + b --> x1 = a * x, where x1 is an IV either
     */

    boolean deriveJudge(IRLoop loop, Queue<BinaryInst> workList, IRBaseInst inst) {
        return inst instanceof BinaryInst // binary inst
                && loop.contents.contains(inst.parentBlock) // inloop
                && !inst2IV.containsKey(inst) // not an IV yet
                && !workList.contains(inst); // not in worklist yet
    }

    private void collectDerivedIV(IRLoop loop) {
        Queue<BinaryInst> workList = new LinkedList<>();
        for (var bivInst : inst2IV.keySet())
            for (var user : bivInst.userList)
                if (deriveJudge(loop, workList, (IRBaseInst) user))
                    workList.add((BinaryInst) user);

        while (!workList.isEmpty()) {
            var inst = workList.poll();
            if (inst2IV.containsKey(inst))
                continue;
            boolean isIV = false;
            if (inst.opCode == BOP.mul) { // y = a * x, rhs is IV
                if (!inst2IV.containsKey(inst.rhs()))
                    inst.swapOprand();
                if (inst2IV.containsKey(inst.rhs()) && loop.checkInvariant(inst.lhs())) {
                    var iv = new IV(inst.rhs(), inst.lhs(), null, BOP.mul);
                    inst2IV.put(inst, iv);
                    isIV = true;
                }
            } else if (inst.opCode == BOP.add || inst.opCode == BOP.sub) {// y = x + b or x - b, lhs is IV
                if (inst.opCode == BOP.add && !inst2IV.containsKey(inst.lhs()))
                    inst.swapOprand();

                // this inst is a step inst of a basic IV, not a derived IV
                var inst0 = inst2IV.get(inst.lhs());
                if (inst0 == null || inst0.isBasicIV && inst0.stepInst == inst)
                    continue;

                if (inst2IV.containsKey(inst.lhs()) && loop.checkInvariant(inst.rhs())) {
                    var iv = new IV(inst.lhs(), null, inst.rhs(), inst.opCode);
                    inst2IV.put(inst, iv);
                    isIV = true;
                }
            }
            if (isIV) {
                for (var user : inst.userList)
                    if (deriveJudge(loop, workList, (IRBaseInst) user))
                        workList.add((BinaryInst) user);
            }
        }
    }
}