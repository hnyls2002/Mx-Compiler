package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import AST.Util.Position;

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
