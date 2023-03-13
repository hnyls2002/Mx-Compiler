package Middleend;

import java.io.FileNotFoundException;

import AST.ASTNode;
import AST.Scopes.GlobalScope;
import IR.IRModule;
import Middleend.IROptimize.PhiElimination;

public class Middleender {
    public IRModule run(ASTNode ast, GlobalScope gScope, String filePath, boolean testOnline)
            throws FileNotFoundException {
        IRBuilder irBuilder = new IRBuilder(ast, gScope);
        IRModule irModule = irBuilder.buildIR();
        IRRenamer renamer = new IRRenamer();

        renamer.runOnIRModule(irModule);
        if (!testOnline) {
            IRPrinter irPrinter = new IRPrinter(filePath + "test.ll");
            irPrinter.runOnIRModule(irModule);
        }

        new PhiElimination().runOnIRModule(irModule);

        renamer.runOnIRModule(irModule);
        if (!testOnline) {
            IRPrinter irPrinter = new IRPrinter(filePath + "test_fake.ll");
            irPrinter.runOnIRModule(irModule);
        }

        return irModule;
    }
}
