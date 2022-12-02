package AST.Stmt;

import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class BreakStmtNode extends StmtNode {

    public BreakStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
