package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import ASM.ASMModule;

public class ASMPrinter {
    private String tab = "    ";

    private String tab(String str) {
        return str + " ".repeat(4 - str.length() % 4);
    }

    public void printASM(String fileName, ASMModule asmModule) throws FileNotFoundException {
        File asm = new File(fileName);
        PrintStream ps = new PrintStream(asm);
        System.setOut(ps);
        printModule(asmModule);
    }

    public void printModule(ASMModule asmModule) {
        System.out.println(tab("") + ".text");
        System.out.println(tab(tab + ".file") + "\"test.ll\"");
    }

    public void printBlock() {
    }

    public void printInst() {
    }
}
