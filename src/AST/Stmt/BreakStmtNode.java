package AST.Stmt;

import AST.ASTVisitor;
import Frontend.Util.Position;

public class BreakStmtNode extends StmtNode {

    public BreakStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
