package Backend;

import java.io.FileNotFoundException;

import ASM.ASMModule;
import Backend.RegAllocate.BfRegAllocator;
import Backend.RegAllocate.StackAllocator;
import IR.IRModule;
import Share.Builtin.BuiltinPrinter;

public class Backender {
    public void run(IRModule irModule, String filePath, boolean testOnline) throws FileNotFoundException {
        new ASMPreHandler().buildSkeleton(irModule);
        ASMModule asmModule = new ASMBuiler().buildAsm(irModule);
        new BfRegAllocator().runOnASMModule(asmModule);
        new StackAllocator().runOnASMModule(asmModule);

        if (testOnline) {
            new ASMFormatter().printASM("output.s", asmModule);
            new BuiltinPrinter().printBuiltin();
        } else
            new ASMFormatter().printASM(filePath + "test.s", asmModule);
    }

}
