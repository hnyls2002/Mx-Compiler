package Backend;

import java.util.HashMap;

import ASM.ASMBlock;
import ASM.ASMFn;
import ASM.ASMInst.ASMLiInst;
import ASM.ASMInst.ASMMoveInst;
import ASM.ASMOprand.Register;
import ASM.ASMOprand.VirtualReg;
import IR.IRModule;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.PhiInst;
import Share.Pass.IRPass.IRBlockPass;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class PhiElimination implements IRModulePass, IRFnPass, IRBlockPass {
    HashMap<PhiInst, Register> tmpRegMap = new HashMap<>();
    ASMFn curFn;

    @Override
    public void runOnIRModule(IRModule irModule) {
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        curFn = fn.asmFn;
        fn.blockList.forEach(this::runOnIRBlock);
        runOnIRBlock(fn.retBlock);
    }

    @Override
    public void runOnIRBlock(IRBasicBlock block) {
        for (var inst : block.instList) {
            if (inst instanceof PhiInst pInst) {
                var tmpReg = new VirtualReg(curFn);
                tmpRegMap.put(pInst, tmpReg);
                ((ASMBlock) block.asOprand).instList.add(0, new ASMMoveInst((Register) pInst.asOprand, tmpReg, null));
                for (int i = 0; i < pInst.blockList.size(); ++i) {
                    var blockFrom = (ASMBlock) pInst.blockList.get(i).asOprand;
                    var irValue = pInst.valueList.get(i);
                    moveCandidate(blockFrom, irValue, tmpReg);
                }
            }
        }
    }

    private void moveCandidate(ASMBlock blockFrom, IRBaseValue irValue, Register tmpReg) {
        if (irValue.asOprand == null) {
            int imm = 0;
            if (irValue instanceof IntConst c)
                imm = c.constValue;
            blockFrom.insertBack(new ASMLiInst(tmpReg, imm, null));
        } else {
            blockFrom.insertBack(new ASMMoveInst(tmpReg, (Register) irValue.asOprand,
            null));
        }
    }
}
