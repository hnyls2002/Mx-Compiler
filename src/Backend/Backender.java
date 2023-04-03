package Backend;

import java.io.FileNotFoundException;

import ASM.ASMModule;
import Backend.BackendOpt.CoalesceMoves;
import Backend.RegAllocate.RegisterColoring;
import Backend.RegAllocate.StackAllocator;
import IR.IRModule;
import Middleend.IROptimize.Tools.CallGraphAnalyzer;
import Share.Builtin.BuiltinPrinter;

public class Backender {
    public void run(IRModule irModule, String filePath, boolean testOnline) throws FileNotFoundException {
        // for build the loop depth info
        new CallGraphAnalyzer().runOnIRModule(irModule);

        new ASMPreHandler().buildSkeleton(irModule);
        ASMModule asmModule = new ASMBuiler().buildAsm(irModule);

        System.err.println("------------Register Coloring------------");

        // new BfRegAllocator().runOnASMModule(asmModule);
        new RegisterColoring().runOnASMModule(asmModule);

        new CoalesceMoves().runOnASMModule(asmModule);

        new StackAllocator().runOnASMModule(asmModule);

        if (testOnline) {
            new ASMFormatter().printASM("output.s", asmModule);
            new BuiltinPrinter().printBuiltin();
        } else
            new ASMFormatter().printASM(filePath + "test.s", asmModule);
    }

}
