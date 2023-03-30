package Middleend.IROptimize;

import java.util.ArrayList;

import org.antlr.v4.runtime.misc.Pair;

import IR.IRModule;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.BrInst;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import IR.IRValue.IRUser.IRInst.JumpInst;
import Middleend.IROptimize.Tools.CallGraphAnalyzer;
import Middleend.IROptimize.Tools.IRLoop;
import Middleend.IROptimize.Tools.InfosRebuilder;
import Middleend.IROptimize.Tools.LoopAnalyzer;
import Share.MyException;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRLoopPass;
import Share.Pass.IRPass.IRModulePass;

public class LICM implements IRModulePass, IRFnPass, IRLoopPass {
    // loop invariant code motion

    public int movableInstCnt = 0;

    @Override
    public void runOnIRModule(IRModule irModule) {
        new CallGraphAnalyzer().runOnIRModule(irModule);
        new LoopAnalyzer().runOnIRModule(irModule);
        new InfosRebuilder().rebuildDefUse(irModule);
        new InfosRebuilder().rebuildCFG(irModule);
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
        System.err.println("LICM: " + movableInstCnt + " insts moved");
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        fn.topLoopList.forEach(this::runOnIRLoop);
    }

    @Override
    public void runOnIRLoop(IRLoop loop) {
        createPreHeader(loop);

        ArrayList<IRBaseInst> movableInsts = new ArrayList<>();

        for (var block : loop.contents)
            for (var inst : block.instList) {
                if (loop.checkInvariant(inst))
                    movableInsts.add(inst);
            }

        movableInstCnt += movableInsts.size();

        while (!movableInsts.isEmpty()) {
            var it = movableInsts.iterator();
            var inst = it.next();
            // System.err.println(inst.formatDef());
            it.remove();
            inst.parentBlock.instList.remove(inst);
            loop.preHeader.addInstBeforeTerminal(inst);
            inst.parentBlock = loop.preHeader;
        }

        loop.subLoops.forEach(this::runOnIRLoop);
    }

    private void createPreHeader(IRLoop loop) {
        var header = loop.header;
        var preHeader = new IRBasicBlock(loop.loopDepth - 1);
        // set preHeader for loop and add it to blockList
        loop.preHeader = preHeader;
        loop.parentFn.blockList.add(loop.parentFn.blockList.indexOf(header), preHeader);

        // !!! add preHeader to this loop's parent loop's contents
        var parentLoop = loop.parentLoop;
        while (parentLoop != null) {
            parentLoop.contents.add(preHeader);
            parentLoop = parentLoop.parentLoop;
        }

        for (var preBlock : header.preList)
            if (!loop.contents.contains(preBlock)) {
                var terminal = preBlock.terminal;
                if (terminal instanceof JumpInst)
                    terminal.setOprand(0, preHeader);
                else if (terminal instanceof BrInst)
                    terminal.setOprand(terminal.getOprand(0) == header ? 1 : 2, preHeader);
                preBlock.sucList.remove(header);
                preBlock.sucList.add(preHeader);
                preHeader.preList.add(preBlock);
            }
        for (var preBlock : preHeader.preList)
            header.preList.remove(preBlock);
        new JumpInst(header, preHeader);

        for (var phi : header.phiList) {
            ArrayList<Pair<Integer, Pair<IRBasicBlock, IRBaseValue>>> outSideBranches = new ArrayList<>();
            for (int i = 0; i < phi.getOprandNum(); i += 2)
                if (!loop.contents.contains(phi.getOprand(i)))
                    outSideBranches
                            .add(new Pair<>(i, new Pair<>((IRBasicBlock) phi.getOprand(i), phi.getOprand(i + 1))));
            if (outSideBranches.size() == 0)
                continue;
            if (outSideBranches.size() == 1) {
                int idx = outSideBranches.get(0).a;
                phi.setOprand(idx, preHeader);
                continue;
            }
            // I suppose that the number of outside branches is at most 1
            throw new MyException("LICM: phi node has more than one outside branch");
        }
    }
}
