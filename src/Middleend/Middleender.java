package Middleend;

import AST.ASTNode;
import AST.Scopes.GlobalScope;
import IR.IRModule;
import Middleend.IROptimize.PhiElimination;

public class Middleender {
    public IRModule run(ASTNode ast, GlobalScope gScope) {
        IRBuilder irBuilder = new IRBuilder(ast, gScope);
        IRModule irModule = irBuilder.buildIR();

        new PhiElimination().runOnIRModule(irModule);

        new IRRenamer().runOnIRModule(irModule);
        return irModule;
    }
}
