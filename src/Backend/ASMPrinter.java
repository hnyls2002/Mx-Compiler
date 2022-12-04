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

    public File asm;
    public PrintStream ps;

    public void printASM(String fileName, ASMModule asmModule) throws FileNotFoundException {
        asm = new File(fileName);
        ps = new PrintStream(asm);
        runOnASMModule(asmModule);
    }

    @Override
    public void runOnASMModule(ASMModule asmModule) {
        ps.print("\t.text\n");
        ps.print("\t.file\t\"test.ll\"\n\n");

        asmModule.fnList.forEach(this::runOnASMFn);
        asmModule.constStrList.forEach(str -> ps.print(ASMFormatter.format(str)));
        asmModule.globalVarList.forEach(gvar -> ps.print(ASMFormatter.format(gvar)));
    }

    @Override
    public void runOnASMFn(ASMFn asmFn) {
        ps.print(ASMFormatter.prefixFormat(asmFn));
        asmFn.blockList.forEach(this::runOnASMBlock);
        ps.print(ASMFormatter.suffixFormat(asmFn, fnCnt++));
    }

    @Override
    public void runOnASMBlock(ASMBlock asmBlock) {
        ps.print(asmBlock.format() + ":\n");
        asmBlock.instList.forEach(inst -> ps.print(inst.format()));
    }
}
