package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import AST.Util.Position;

public class IfStmtNode extends StmtNode {

    public ExprNode expr;

    public StmtNode thenStmt, elseStmt;

    public IfStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
