package MiddleEnd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import IR.IRModule;
import IR.IRValue.IRBasicBlock;
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
        topModule.globalFnList.forEach(gfn -> printFn(gfn));
    }

    private void printFn(IRFn fn) {
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
