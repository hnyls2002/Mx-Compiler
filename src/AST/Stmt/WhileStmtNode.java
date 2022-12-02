package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import AST.Util.Position;

public class WhileStmtNode extends StmtNode {

    public ExprNode expr;

    public StmtNode body;

    public WhileStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
