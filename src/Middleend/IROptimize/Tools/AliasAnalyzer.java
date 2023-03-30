package Middleend.IROptimize.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import IR.IRModule;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRParameter;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.AllocaInst;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.CastInst;
import IR.IRValue.IRUser.IRInst.GEPInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.PhiInst;
import Share.MyException;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;

public class AliasAnalyzer implements IRModulePass, IRFnPass {
    /*
     * trying to judge two pointer is definitely not alias
     * just work in specific cases : loop invariant code motion
     * 
     * memory starter (base pointer) : global var, malloc, alloca
     * just consider : arithmetic operation on memory starter ----> GEP
     * cast will be considered as alias linker
     * 
     * definitely not alias :
     * 1) types not match
     * 2) different memory source
     * 3) both constant offset but not equal
     * 
     */

    HashMap<IRBaseValue, IRBaseValue> castUnion = new HashMap<>();
    HashSet<IRBaseValue> memoryStarter = new HashSet<>();

    @Override
    public void runOnIRModule(IRModule irModule) {
        for (var glo : irModule.globalVarList)
            memoryStarter.add(glo);
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        var blockList = new ArrayList<>(fn.blockList);
        blockList.add(fn.retBlock);
        for (var block : blockList) {
            for (var inst : block.instList) {
                if (inst instanceof CallInst call && call.callee.nameString.equals("__malloc"))
                    memoryStarter.add(call);
                if (inst instanceof AllocaInst)
                    memoryStarter.add(inst);
                // all pointer type is initialized as itself
                if (inst instanceof CastInst cast)
                    castUnion.put(cast, cast.getOprand(0));
            }
        }

        // for test
        // for (var block1 : blockList) {
        // for (var inst1 : block1.instList) {
        // for (var block2 : blockList) {
        // for (var inst2 : block2.instList) {
        // if (inst1 == inst2)
        // continue;
        // if (inst1 instanceof StoreInst store1 && inst2 instanceof StoreInst store2) {
        // if (!mayAlias(store1.getOprand(1), store2.getOprand(1))) {
        // System.err.println(store1.formatDef());
        // System.err.println(store2.formatDef());
        // System.err.println("--------------------");
        // }
        // }
        // }
        // }
        // }
        // }
    }

    public IRBaseValue findCastEnd(IRBaseValue addr) {
        if (castUnion.get(addr) == null || castUnion.get(addr) == addr)
            return addr;
        var ret = findCastEnd(castUnion.get(addr));
        castUnion.put(addr, ret);
        return ret;
    }

    public boolean mayAlias(IRBaseValue addr1, IRBaseValue addr2) {
        // store to different type must not be alias
        if (!addr1.valueType.equals(addr2.valueType))
            return false;

        addr1 = findCastEnd(addr1);
        addr2 = findCastEnd(addr2);
        // addr1 and addr2 must : global var, alloca, call(malloc)
        // phi,load,para and gep
        if (addr1 instanceof PhiInst
                || addr2 instanceof PhiInst
                || addr1 instanceof LoadInst
                || addr2 instanceof LoadInst
                || addr1 instanceof IRParameter
                || addr2 instanceof IRParameter)
            return true;
        if (memoryStarter.contains(addr1) && memoryStarter.contains(addr2))
            return addr1 == addr2;
        if (memoryStarter.contains(addr1) || memoryStarter.contains(addr2)) {
            if (memoryStarter.contains(addr2))
                return mayAlias(addr2, addr1);
            // addr1 is memory starter
            if (!(addr2 instanceof GEPInst))
                throw new MyException("addr2 should be GEPInst here");
            return mayAlias(addr1, ((GEPInst) addr2).getOprand(0));
        }

        var gep1 = (GEPInst) addr1;
        var gep2 = (GEPInst) addr2;
        if (gep1.getOprandNum() != gep2.getOprandNum())
            return false;
        boolean checkStarter = mayAlias(gep1.getOprand(0), gep2.getOprand(0));
        if (!checkStarter)
            return false;
        for (int i = 1; i < gep1.getOprandNum(); ++i) {
            var oprand1 = gep1.getOprand(i);
            var oprand2 = gep2.getOprand(i);
            if (oprand1 instanceof IntConst intConst1 && oprand2 instanceof IntConst intConst2)
                if (intConst1.constValue != intConst2.constValue)
                    return false;
        }

        return true;
    }
}
