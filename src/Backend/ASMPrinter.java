package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import ASM.ASMBlock;
import ASM.ASMFn;
import ASM.ASMModule;
import Share.Pass.ASMPass.ASMBlockPass;
import Share.Pass.ASMPass.ASMFnPass;
import Share.Pass.ASMPass.ASMModulePass;

public class ASMPrinter implements ASMModulePass, ASMBlockPass, ASMFnPass {
    private static int fnCnt = 0;

    public void printASM(String fileName, ASMModule asmModule) throws FileNotFoundException {
        File asm = new File(fileName);
        PrintStream ps = new PrintStream(asm);
        System.setOut(ps);
        runOnASMModule(asmModule);
    }

    @Override
    public void runOnASMModule(ASMModule asmModule) {
        System.out.print("\t.text\n");
        System.out.print("\t.file\t\"test.ll\"\n\n");

        asmModule.fnList.forEach(this::runOnASMFn);
        asmModule.constStrList.forEach(str -> System.out.print(ASMFormatter.format(str)));
        asmModule.globalVarList.forEach(gvar -> System.out.print(ASMFormatter.format(gvar)));
    }

    @Override
    public void runOnASMFn(ASMFn asmFn) {
        System.out.print(ASMFormatter.prefixFormat(asmFn));
        asmFn.blockList.forEach(this::runOnASMBlock);
        System.out.print(ASMFormatter.suffixFormat(asmFn, fnCnt++));
    }

    @Override
    public void runOnASMBlock(ASMBlock asmBlock) {
        System.out.print(asmBlock.format() + ":\n");
        asmBlock.instList.forEach(inst -> System.out.print(inst.format()));
    }
}
