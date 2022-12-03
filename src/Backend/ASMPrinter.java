package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import ASM.ASMFn;
import ASM.ASMModule;

public class ASMPrinter {
    private static int fnCnt = 0;

    public void printASM(String fileName, ASMModule asmModule) throws FileNotFoundException {
        File asm = new File(fileName);
        PrintStream ps = new PrintStream(asm);
        System.setOut(ps);
        printModule(asmModule);
    }

    public void printModule(ASMModule asmModule) {
        System.out.print("\t.text\n");
        System.out.print("\t.file\t\"test.ll\"\n\n");

        asmModule.fnList.forEach(this::printFn);
        asmModule.constStrList.forEach(str -> System.out.print(ASMFormatter.format(str)));
        asmModule.globalVarList.forEach(gvar -> System.out.print(ASMFormatter.format(gvar)));
    }

    void printFn(ASMFn fn) {
        System.out.print(ASMFormatter.prefixFormat(fn));
        System.out.print(ASMFormatter.suffixFormat(fn, fnCnt++));
    }

    public void printBlock() {
    }

    public void printInst() {
    }
}
