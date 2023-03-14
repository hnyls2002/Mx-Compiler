package Middleend;

import java.io.FileNotFoundException;

import AST.ASTNode;
import AST.Scopes.GlobalScope;
import IR.IRModule;
import Middleend.IROptimize.DominateTree;
import Middleend.IROptimize.Mem2Reg;
import Middleend.IROptimize.PhiElimination;

public class Middleender {
    public IRModule run(ASTNode ast, GlobalScope gScope, String filePath, boolean testOnline)
            throws FileNotFoundException {
        IRBuilder irBuilder = new IRBuilder(ast, gScope);
        IRModule irModule = irBuilder.buildIR();

        new DominateTree().runOnIRModule(irModule);
        new Mem2Reg().runOnIRModule(irModule);

        new IRRenamer().runOnIRModule(irModule);

        // // -------------- debug --------------------
        // var root = irModule.globalFnList.get(0).blockList.get(0);
        // for (var bb : root.dtNode.slaveSet) {
        // System.err.println(bb.getName());

        // System.err.println("slaveSet : ");
        // for (var slave : bb.dtNode.slaveSet)
        // System.err.println(slave.getName());

        // System.err.println("masterSet : ");
        // for (var master : bb.dtNode.masterSet)
        // System.err.println(master.getName());

        // System.err.println("frontier : ");
        // for (var frontier : bb.dtNode.frontier)
        // System.err.println(frontier.getName());

        // System.err.println("\n-----------------------------------");
        // }

        if (!testOnline) {
            IRPrinter irPrinter = new IRPrinter(filePath + "test.ll");
            irPrinter.runOnIRModule(irModule);
        }

        new PhiElimination().runOnIRModule(irModule);

        new IRRenamer().runOnIRModule(irModule);
        if (!testOnline) {
            IRPrinter irPrinter = new IRPrinter(filePath + "test_fake.ll");
            irPrinter.runOnIRModule(irModule);
        }

        return irModule;
    }
}
