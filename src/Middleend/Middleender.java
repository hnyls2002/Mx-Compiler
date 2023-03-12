package Middleend;

import AST.ASTNode;
import AST.Scopes.GlobalScope;
import IR.IRModule;

public class Middleender {
    public IRModule run(ASTNode ast, GlobalScope gScope) {
        IRBuilder irBuilder = new IRBuilder(ast, gScope);
        IRModule irModule = irBuilder.buildIR();
        new IRRenamer().runOnIRModule(irModule);
        return irModule;
    }
}
