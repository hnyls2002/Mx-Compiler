package Middleend.IROptimize.Tools;

import java.util.ArrayList;
import java.util.HashSet;

import org.antlr.v4.runtime.misc.Pair;

import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRParameter;
import IR.IRValue.IRUser.ConsValue.BaseConstValue;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.BrInst;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import IR.IRValue.IRUser.IRInst.JumpInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.RetInst;
import IR.IRValue.IRUser.IRInst.StoreInst;
import Share.MyException;

public class IRLoop {
    public IRBasicBlock header, preHeader;
    public HashSet<IRBasicBlock> tails = new HashSet<>();
    public HashSet<IRBasicBlock> contents = new HashSet<>();
    public HashSet<IRLoop> subLoops = new HashSet<>();
    public IRLoop parentLoop = null;
    public IRFn parentFn = null;
    public int loopDepth = 0;

    public IRLoop(IRBasicBlock header, IRFn parentFn) {
        this.header = header;
        this.parentFn = parentFn;
    }

    public boolean notModified(IRBaseValue value, AliasAnalyzer aliasAnalyzer) {
        for (var block : contents) {
            for (var inst : block.instList) {
                if (inst instanceof StoreInst && aliasAnalyzer.mayAlias(value, inst.getOprand(1)))
                    return false;
                if (inst instanceof CallInst call && call.callee.callInfo.willModifyMemory())
                    return false;
            }
        }
        return true;
    }

    /*
     * used in LICM
     * not so conservative
     * 1) check the call inst, if it will not change the memory or program's IO
     * status definitely, then move it out
     * 2) check the load inst, if the address is not modified in the loop, then move
     * it out (alias analysis is required here)
     * 
     */
    public boolean checkInvariant(IRBaseValue value, AliasAnalyzer aliasAnalyzer) {
        // const value (const data + global var)
        // or parameter
        if (value instanceof BaseConstValue
                || value instanceof IRParameter)
            return true;

        // basic block
        if (value instanceof IRBasicBlock block)
            return !contents.contains(block);

        var inst = (IRBaseInst) value;

        // not contained in the loop
        if (!contents.contains(inst.parentBlock))
            return true;

        // has side effect
        if (inst instanceof RetInst
                || inst instanceof JumpInst
                || inst instanceof BrInst
                || inst instanceof StoreInst)
            return false;

        if (inst instanceof CallInst call && call.callee.callInfo.mayVariant())
            return false;
        // if (inst instanceof CallInst)
        // return false;

        // check all the operands
        for (int i = 0; i < inst.getOprandNum(); ++i)
            if (!checkInvariant(inst.getOprand(i), aliasAnalyzer))
                return false;

        if (inst instanceof LoadInst) // check alias
            return notModified(inst.getOprand(0), aliasAnalyzer);

        return true;
    }

    /*
     * used in IVT
     * the invariant is not an inside instruction (moved out in LICM)
     * 
     * 1. instruction already outside the loop
     * 2. const value
     */

    public boolean checkInvariant(IRBaseValue value) {
        if (value instanceof IntConst)
            return true;
        if (value instanceof IRBaseInst inst)
            return !contents.contains(inst.parentBlock);
        return false;
    }

    public void createPreHeader() {
        preHeader = new IRBasicBlock(loopDepth - 1);
        // set preHeader for loop and add it to blockList
        parentFn.blockList.add(parentFn.blockList.indexOf(header), preHeader);

        // !!! add preHeader to this loop's parent loop's contents
        while (parentLoop != null) {
            parentLoop.contents.add(preHeader);
            parentLoop = parentLoop.parentLoop;
        }

        for (var preBlock : header.preList)
            if (!contents.contains(preBlock)) {
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
                if (!contents.contains(phi.getOprand(i)))
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
            throw new MyException("CreatePreHeader: phi node has more than 1 outside branches");
        }
    }
}
