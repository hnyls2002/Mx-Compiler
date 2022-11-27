package MiddleEnd;

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
import IR.IRValue.IRUser.Inst.IRBaseInst;

public class IRPrinter {
    IRModule topModule;
    String fileName;
    File llvmir;
    PrintStream ps;
    final String tab = "    ";

    public IRPrinter(IRModule topModule, String fileName) throws FileNotFoundException {
        this.topModule = topModule;
        llvmir = new File(fileName);
        ps = new PrintStream(llvmir);
        System.setOut(ps);
    }

    public void printIR() {
        System.out.print(ExternInfo.getExternInfo());
        System.out.print('\n');
        topModule.builtinFnList.forEach(fnType -> System.out.println(fnType.toString()));
        System.out.print('\n');
        topModule.classList.forEach(classDef -> printClassDef(classDef));
        if (!topModule.classList.isEmpty())
            System.out.print('\n');
        topModule.constStrList.forEach(constStr -> printConstStr(constStr));
        if (!topModule.constStrList.isEmpty())
            System.out.print('\n');
        topModule.globalVarList.forEach(gVar -> printGVar(gVar));
        if (!topModule.globalVarList.isEmpty())
            System.out.print('\n');
        topModule.varInitFnList.forEach(initFn -> printFn(initFn));
        if (!topModule.varInitFnList.isEmpty())
            System.out.print('\n');
        topModule.globalFnList.forEach(gFn -> printFn(gFn));
    }

    private void printClassDef(IRStructType classDef) {
        System.out.print(classDef.getClassName() + " = " + classDef.typeDefToString() + '\n');
    }

    private void printConstStr(StrConst constStr) {
        System.out.print(constStr.getName() + " = constant ");
        System.out.print(constStr.defToString() + '\n');
    }

    private void printGVar(GlobalVariable gVar) {
        System.out.print(gVar.getName() + " = global ");
        System.out.print(gVar.defToString() + '\n');
    }

    void printFn(IRFn fn) {
        System.out.print(fn.defToString());
        System.out.print("{\n");
        fn.blockList.forEach(block -> {
            printBB(block);
            System.out.print('\n');
        });
        printBB(fn.retBlock);
        System.out.print("}\n\n");
    }

    private void printBB(IRBasicBlock block) {
        System.out.print(block.defToString() + '\n');
        block.instList.forEach(inst -> printInst(inst));
        printInst(block.getTerminal());
    }

    private void printInst(IRBaseInst inst) {
        var defString = inst.defToString() + "\n";
        if (!(inst.valueType instanceof IRVoidType))
            defString = inst.getName() + " = " + defString;
        System.out.print(tab + defString);
    }

}
