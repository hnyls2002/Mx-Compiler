package AST.Stmt;

import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class ContinueStmtNode extends StmtNode {

    public ContinueStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
