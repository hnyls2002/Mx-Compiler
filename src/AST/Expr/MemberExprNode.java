package AST.Expr;

import AST.ASTVisitor;
import Frontend.Util.Position;

public class MemberExprNode extends ExprNode {

    public ExprNode expr;
    public FuncCallExprNode funcCall;
    public IdentiExprNode idExpr;

    public MemberExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
