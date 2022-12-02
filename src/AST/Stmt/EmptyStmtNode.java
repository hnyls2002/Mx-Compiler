package AST.Stmt;

import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class EmptyStmtNode extends StmtNode {

    public EmptyStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
