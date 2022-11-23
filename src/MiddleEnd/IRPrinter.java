package MiddleEnd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import IR.IRModule;
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
        System.out.print(ExternInfo.getExternInfo() + '\n');
        topModule.constStrList.forEach(constStr -> printConstStr(constStr));
        System.out.print('\n');
        topModule.globalVarList.forEach(gVar -> printGVar(gVar));
        System.out.print('\n');
        topModule.globalFnList.forEach(gFn -> printFn(gFn));
    }

    private void printConstStr(StrConst constStr) {
        System.out.println(constStr.toString());
    }

    private void printGVar(GlobalVariable gVar) {
        System.out.print(gVar.toString() + '\n');
    }

    void printFn(IRFn fn) {
        System.out.print(fn.toString());
        System.out.print("{\n");
        fn.blockList.forEach(blk -> printBB(blk));
        System.out.print("}\n");
    }

    private void printBB(IRBasicBlock block) {
        if (block.entryString != null)
            System.out.print(block.entryString);
        block.instList.forEach(inst -> printInst(inst));
        printInst(block.terminal);
    }

    private void printInst(IRBaseInst inst) {
        System.out.print(tab + inst.toString() + "\n");
    }

}
