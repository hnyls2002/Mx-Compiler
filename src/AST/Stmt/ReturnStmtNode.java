package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Frontend.Util.Position;

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
