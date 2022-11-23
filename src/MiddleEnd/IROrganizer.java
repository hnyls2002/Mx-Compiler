package MiddleEnd;

import Debug.MyException;
import IR.IRModule;
import IR.IRType.IRFnType;
import IR.IRType.IRPtType;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.BaseConstData;
import IR.IRValue.IRUser.ConsValue.ConsData.StrConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.Inst.GEPInst;
import IR.IRValue.IRUser.Inst.StoreInst;

public class IROrganizer {
    public IRModule topModule;

    public IROrganizer(IRModule topModule) {
        this.topModule = topModule;
    }

    public void organizeIR() {
        organizeGlobalVarInit();
    }

    private void organizeGlobalVarInit() {
        topModule.globalVarList.forEach(gVar -> {
            if (gVar.valueType instanceof IRPtType tmp) {
                gVar.derefType = tmp.derefType();
                if (!gVar.isInit)// use default value
                    gVar.initValue = gVar.derefType.defaultValue();
                else if (gVar.initData instanceof BaseConstData constData) {
                    if (constData instanceof StrConst constStr) { // strConst should define a global str first
                        var gepInst = new GEPInst(gVar.initData, gVar.derefType);
                        gepInst.addIdx(0);
                        gepInst.addIdx(0);
                        gVar.initValue = gepInst;
                    } else // constInt or constNull
                        gVar.initValue = constData;
                } else {
                    gVar.initValue = gVar.derefType.defaultValue();
                    var fnNameString = "__mx_global_var_init." + topModule.varInitFnList.size();
                    var varInitFn = new IRFn(fnNameString, IRFnType.getVarInitFnType());
                    var block = IRBasicBlock.getVarInitBB();
                    block.addInst(new StoreInst(gVar.initData, gVar));
                    varInitFn.addBlock(block);
                    topModule.varInitFnList.add(varInitFn);
                }

            } else
                throw new MyException("global var should be a pointer type");
        });
    }
}
