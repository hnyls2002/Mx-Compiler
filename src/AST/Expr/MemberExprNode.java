package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

public class MemberExprNode extends ExprNode {

    public ExprNode expr;
    public String idString;
    public FuncCallExprNode funcCall;

    public MemberExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
