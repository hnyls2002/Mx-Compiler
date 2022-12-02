package AST.Expr;

import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class IdentiExprNode extends ExprNode {

    public String idString;

    public IdentiExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
