package AST.Stmt;

import AST.Expr.ExprNode;
import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class ReturnStmtNode extends StmtNode {

    public ExprNode expr;

    public ReturnStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
