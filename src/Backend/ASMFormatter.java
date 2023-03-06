package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import ASM.ASMBlock;
import ASM.ASMFn;
import ASM.ASMModule;
import ASM.ASMOprand.ASMGlobal.ASMConstStr;
import ASM.ASMOprand.ASMGlobal.ASMGlobalVar;
import Share.Pass.ASMPass.ASMBlockPass;
import Share.Pass.ASMPass.ASMFnPass;
import Share.Pass.ASMPass.ASMModulePass;

public class ASMFormatter implements ASMModulePass, ASMFnPass, ASMBlockPass {
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
        asmModule.constStrList.forEach(str -> ps.print(printConStr(str)));
        asmModule.globalVarList.forEach(gvar -> ps.print(printGVar(gvar)));
    }

    @Override
    public void runOnASMFn(ASMFn asmFn) {
        ps.print(fnPreFormat(asmFn));
        asmFn.blockList.forEach(this::runOnASMBlock);
        ps.print(fnSufFormat(asmFn, fnCnt++));
    }

    @Override
    public void runOnASMBlock(ASMBlock asmBlock) {
        ps.print(asmBlock.format() + ":\n");
        asmBlock.instList.forEach(inst -> ps.print(inst.format()));
    }

    public static String printConStr(ASMConstStr str) {
        var ret = "";
        ret += String.format("\t.type\t%s,@object\n", str.name);
        ret += "\t.section\t.rodata,\"a\",@progbits\n";
        ret += String.format("\t.globl\t%s\n", str.name);
        ret += String.format("%s:\n", str.name);
        str.data = str.data.replaceAll("\\\\22", "\\\\\"");
        str.data = str.data.replaceAll("\\\\0A", "\\\\n");
        ret += String.format("\t.asciz\t\"%s\"\n", str.data);
        ret += String.format("\t.size\t%s, %d\n", str.name, str.size);
        ret += "\n";
        return ret;
    }

    public static String printGVar(ASMGlobalVar gvar) {
        var ret = "";
        ret += String.format("\t.type\t%s,@object\n", gvar.name);
        ret += "\t.section\t" + (gvar.isContainsPtr ? ".sbss,\"aw\",@nobits\n" : ".sdata,\"aw\",@progbits\n");
        ret += String.format("\t.globl\t%s\n", gvar.name);
        ret += gvar.name + ":\n";
        ret += String.format("\t.word\t%s\n", gvar.data);
        ret += String.format("\t.size\t%s, %d\n", gvar.name, gvar.size);
        ret += "\n";
        return ret;
    }

    public static String fnPreFormat(ASMFn fn) {
        var ret = "";
        ret += String.format("\t.globl\t%s\n", fn.name);
        ret += String.format("\t.type\t%s,@function\n", fn.name);
        ret += fn.name + ":\n";
        ret += "\t.cfi_startproc\n";
        return ret;
    }

    public static String fnSufFormat(ASMFn fn, int id) {
        var ret = "";
        var label = ".Lfunc_end" + id;
        ret += label + ":\n";
        ret += String.format("\t.size\t%s, %s-%s\n", fn.name, label, fn.name);
        ret += String.format("\t.cfi_endproc\n");
        ret += "\n";
        return ret;
    }

}
