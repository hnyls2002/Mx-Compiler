package AST.Stmt;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;

public class SelfConstructNode extends ASTNode {

    public String ClassNameString;
    public SuiteStmtNode body;

    public SelfConstructNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
