package AST.Expr;

import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class ThisExprNode extends ExprNode {

    public ThisExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
