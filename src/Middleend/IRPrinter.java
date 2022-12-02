package Middleend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import IR.IRModule;
import IR.IRType.IRStructType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.StrConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import Share.Pass.IRBlockPass;
import Share.Pass.IRFnPass;
import Share.Pass.IRModulePass;

public class IRPrinter implements IRModulePass, IRFnPass, IRBlockPass {
    String fileName;
    File llvmir;
    PrintStream ps;
    final String tab = "    ";

    public IRPrinter(String fileName) throws FileNotFoundException {
        llvmir = new File(fileName);
        ps = new PrintStream(llvmir);
        System.setOut(ps);
    }

    @Override
    public void runOnIRModule(IRModule irModule) {
        System.out.print(ExternInfo.getExternInfo());
        System.out.print('\n');
        irModule.builtinFnList.forEach(fnType -> System.out.println(fnType.toString()));
        System.out.print('\n');
        irModule.classList.forEach(classDef -> printClassDef(classDef));
        if (!irModule.classList.isEmpty())
            System.out.print('\n');
        irModule.constStrList.forEach(constStr -> printConstStr(constStr));
        if (!irModule.constStrList.isEmpty())
            System.out.print('\n');
        irModule.globalVarList.forEach(gVar -> printGVar(gVar));
        if (!irModule.globalVarList.isEmpty())
            System.out.print('\n');
        irModule.varInitFnList.forEach(initFn -> runOnIRFn(initFn));
        if (!irModule.varInitFnList.isEmpty())
            System.out.print('\n');
        irModule.globalFnList.forEach(gFn -> runOnIRFn(gFn));
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        System.out.print(fn.defToString());
        System.out.print("{\n");
        fn.blockList.forEach(block -> {
            runOnBlock(block);
            System.out.print('\n');
        });
        runOnBlock(fn.retBlock);
        System.out.print("}\n\n");
    }

    @Override
    public void runOnBlock(IRBasicBlock block) {
        System.out.print(block.defToString() + '\n');
        block.instList.forEach(inst -> printInst(inst));
        printInst(block.getTerminal());
    }

    private void printClassDef(IRStructType classDef) {
        System.out.print(classDef.getClassName() + " = " + classDef.typeDefToString() + '\n');
    }

    private void printConstStr(StrConst constStr) {
        System.out.print('@' + constStr.getName() + " = constant ");
        System.out.print(constStr.defToString() + '\n');
    }

    private void printGVar(GlobalVariable gVar) {
        System.out.print('@' + gVar.getName() + " = global ");
        System.out.print(gVar.defToString() + '\n');
    }

    private void printInst(IRBaseInst inst) {
        var defString = inst.defToString() + "\n";
        if (!(inst.valueType instanceof IRVoidType))
            defString = '%' + inst.getName() + " = " + defString;
        System.out.print(tab + defString);
    }

}
