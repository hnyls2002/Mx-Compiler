package Backend;

import ASM.ASMBlock;
import ASM.ASMFn;
import ASM.ASMModule;
import ASM.ASMOprand.Immediate;
import ASM.ASMOprand.VirtualReg;
import ASM.ASMOprand.ASMGlobal.ASMConstStr;
import ASM.ASMOprand.ASMGlobal.ASMGlobalVar;
import IR.IRModule;
import IR.IRType.IRVoidType;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRVReg;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.BinaryInst;
import IR.IRValue.IRUser.IRInst.BrInst;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.CastInst;
import IR.IRValue.IRUser.IRInst.GEPInst;
import IR.IRValue.IRUser.IRInst.IcmpInst;
import IR.IRValue.IRUser.IRInst.JumpInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.MoveInst;
import IR.IRValue.IRUser.IRInst.PhiInst;
import IR.IRValue.IRUser.IRInst.RetInst;
import IR.IRValue.IRUser.IRInst.StoreInst;
import Share.Pass.IRPass.IRBlockPass;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;
import Share.Visitors.IRInstVisitor;

public class ASMPreHandler implements IRModulePass, IRFnPass, IRBlockPass, IRInstVisitor {

    public ASMCurrent cur = new ASMCurrent();
    public int fnCnt = 0;

    /*
     * oprand has several kinds
     * 1. virtualReg, virtualOffset : need to be pre created
     * 2. physicalReg
     * 3. Immediate, globalVar, constStr :
     * ----1) constant to immediate
     * ----2) globalVar to globalVar
     * ----3) constStr to constStr
     */

    public void buildSkeleton(IRModule irModule) {
        irModule.asmModule = new ASMModule();
        cur.module = irModule.asmModule;
        runOnIRModule(irModule);
    }

    @Override
    public void runOnIRModule(IRModule irModule) {
        // precreate the constStr and globalVar as oprand
        irModule.constStrList.forEach(constStr -> {
            ASMConstStr t = new ASMConstStr(constStr);
            cur.module.constStrList.add(t);
            constStr.asOprand = t;
        });
        irModule.globalVarList.forEach(gVar -> {
            ASMGlobalVar t = new ASMGlobalVar(gVar);
            cur.module.globalVarList.add(t);
            gVar.asOprand = t;
        });
        irModule.varInitFnList.forEach(this::runOnIRFn);
        irModule.globalFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn irfn) {
        ASMFn asmFn = new ASMFn(irfn);
        irfn.asmFn = asmFn;
        cur.module.fnList.add(asmFn);
        cur.fn = asmFn;

        // block preload : set oprand for every IRBasicBlock
        for (int i = 0; i < irfn.blockList.size(); ++i) {
            var irBlock = irfn.blockList.get(i);
            irBlock.asOprand = new ASMBlock(i, fnCnt, irBlock.loopDepth);
        }
        irfn.retBlock.asOprand = new ASMBlock(irfn.blockList.size(), fnCnt, 0);
        ++fnCnt;

        // reset the virtual register counter
        cur.block = (ASMBlock) irfn.blockList.get(0).asOprand;

        // function's parameters implicitly define a set of virtual registers
        for (int i = 0; i < irfn.paraList.size(); ++i)
            irfn.paraList.get(i).asOprand = new VirtualReg(cur.fn);

        // runOnBlock
        irfn.blockList.forEach(this::runOnIRBlock);
        runOnIRBlock(irfn.retBlock);
    }

    @Override
    public void runOnIRBlock(IRBasicBlock irBlock) {
        cur.block = (ASMBlock) irBlock.asOprand;
        cur.fn.blockList.add(cur.block);

        // those instructions which have a return value should be preloaded
        irBlock.instList.forEach(inst -> {
            for (int i = 0; i < inst.getOprandNum(); ++i) {
                var use = inst.getOprand(i);
                if (use instanceof IntConst intConst)
                    use.asOprand = new Immediate(intConst.constValue);
                else if (use instanceof NullConst)
                    use.asOprand = new Immediate(0);
            }
            inst.accept(this);
        });
    }

    @Override
    public void visit(BinaryInst inst) {
        inst.asOprand = new VirtualReg(cur.fn);
    }

    @Override
    public void visit(CallInst inst) {
        // preload the return value
        if (!(inst.calledFnType.retType instanceof IRVoidType))
            inst.asOprand = new VirtualReg(cur.fn);
    }

    @Override
    public void visit(GEPInst inst) {
        inst.asOprand = new VirtualReg(cur.fn);
    }

    @Override
    public void visit(IcmpInst inst) {
        inst.asOprand = new VirtualReg(cur.fn);
    }

    @Override
    public void visit(LoadInst inst) {
        inst.asOprand = new VirtualReg(cur.fn);
    }

    @Override
    public void visit(MoveInst inst) {
        var dest = inst.getOprand(0);

        // when dest is a IRVReg, it should be preloaded
        if (dest instanceof IRVReg && dest.asOprand == null)
            dest.asOprand = new VirtualReg(cur.fn);

        // phi node should be created
        if (dest instanceof PhiInst && dest.asOprand == null)
            dest.asOprand = new VirtualReg(cur.fn);
    }

    @Override
    public void visit(RetInst inst) {
    }

    @Override
    public void visit(JumpInst inst) {
    }

    @Override
    public void visit(BrInst inst) {
    }

    @Override
    public void visit(StoreInst inst) {
    }

    @Override
    public void visit(CastInst inst) {
    }

}
