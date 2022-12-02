package AST.Stmt;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.Util.Position;

public class SelfConstructNode extends ASTNode {

    public String consNameString;
    public SuiteStmtNode body;

    public SelfConstructNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
