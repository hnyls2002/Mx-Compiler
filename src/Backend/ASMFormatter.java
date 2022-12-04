package Backend;

import ASM.ASMFn;
import ASM.ASMOprand.ASMGlobal.ASMConstStr;
import ASM.ASMOprand.ASMGlobal.ASMGlobalVar;

public class ASMFormatter {
    public static String format(ASMConstStr str) {
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

    public static String format(ASMGlobalVar gvar) {
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

    public static String prefixFormat(ASMFn fn) {
        var ret = "";
        ret += String.format("\t.globl\t%s\n", fn.name);
        ret += String.format("\t.type\t%s,@function\n", fn.name);
        ret += fn.name + ":\n";
        ret += "\t.cfi_startproc\n";
        return ret;
    }

    public static String suffixFormat(ASMFn fn, int id) {
        var ret = "";
        var label = ".Lfunc_end" + id;
        ret += label + ":\n";
        ret += String.format("\t.size\t%s, %s-%s\n", fn.name, label, fn.name);
        ret += String.format("\t.cfi_endproc\n");
        ret += "\n";
        return ret;
    }
}
