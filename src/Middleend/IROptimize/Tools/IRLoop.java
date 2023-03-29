package Middleend.IROptimize.Tools;

import java.util.HashSet;
import java.util.List;

import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRParameter;
import IR.IRValue.IRUser.ConsValue.BaseConstValue;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.BrInst;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import IR.IRValue.IRUser.IRInst.JumpInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.RetInst;
import IR.IRValue.IRUser.IRInst.StoreInst;

public class IRLoop {
    public IRBasicBlock header, preHeader;
    public HashSet<IRBasicBlock> tails = new HashSet<>();
    public HashSet<IRBasicBlock> contents = new HashSet<>();
    public HashSet<IRLoop> subLoops = new HashSet<>();
    public IRLoop parentLoop = null;
    public IRFn parentFn = null;
    public int loopDepth = 0;

    static public List<String> builtInSideEffectFn = List.of(
            "__malloc", "print", "println", "getString", "getInt", "printInt", "printlnInt");

    public IRLoop(IRBasicBlock header, IRFn parentFn) {
        this.header = header;
        this.parentFn = parentFn;
    }

    public boolean checkInvariant(IRBaseValue value) {
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

        if (inst instanceof CallInst call) {
            if (builtInSideEffectFn.contains(call.callee.nameString))
                return false;
            if (call.callee.callInfo.hasLoadInst || call.callee.callInfo.hasStoreInst)
                return false;
        }

        // check all the operands
        for (int i = 0; i < inst.getOprandNum(); ++i)
            if (!checkInvariant(inst.getOprand(i)))
                return false;

        if (inst instanceof LoadInst) // check alias
            return false;

        return true;
    }
}
