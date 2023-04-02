package Middleend;

import java.io.FileNotFoundException;

import AST.ASTNode;
import AST.Scopes.GlobalScope;
import IR.IRModule;
import Middleend.IROptimize.ADCE;
import Middleend.IROptimize.Glo2Loc;
import Middleend.IROptimize.LICM;
import Middleend.IROptimize.Mem2Reg;
import Middleend.IROptimize.PhiElimination;
import Middleend.IROptimize.SCCP;

public class Middleender {
    public IRModule run(ASTNode ast, GlobalScope gScope, String filePath, boolean testOnline)
            throws FileNotFoundException {
        IRBuilder irBuilder = new IRBuilder(ast, gScope);
        IRModule irModule = irBuilder.buildIR();

        new Glo2Loc().runOnIRModule(irModule);

        new IRRenamer().runOnIRModule(irModule);

        if (!testOnline) {
            IRPrinter irPrinter = new IRPrinter(filePath + "test_not_mem2reg.ll");
            irPrinter.runOnIRModule(irModule);
        }

        new Mem2Reg().runOnIRModule(irModule);

        for (int i = 0; i < 3; ++i) {
            System.err.println("------------Optimize " + (i + 1) + " times------------");
            new ADCE().runOnIRModule(irModule);
            new SCCP().runOnIRModule(irModule);
            new ADCE().runOnIRModule(irModule);
            new LICM().runOnIRModule(irModule);
            new ADCE().runOnIRModule(irModule);
            new CSE().runOnIRModule(irModule);
            new ADCE().runOnIRModule(irModule);
            new IVT().runOnIRModule(irModule);
            new ADCE().runOnIRModule(irModule);
        }

        new IRRenamer().runOnIRModule(irModule);

        if (!testOnline) {
            IRPrinter irPrinter = new IRPrinter(filePath + "test.ll");
            irPrinter.runOnIRModule(irModule);
        }

        new PhiElimination().runOnIRModule(irModule);

        new IRRenamer().runOnIRModule(irModule);
        if (!testOnline) {
            IRPrinter irPrinter = new IRPrinter(filePath + "test_no_phi.ll");
            irPrinter.runOnIRModule(irModule);
        }

        return irModule;
    }
}
