package AST.Expr;

import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class SubscExprNode extends ExprNode {

    public ExprNode arr, sub;

    public SubscExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
