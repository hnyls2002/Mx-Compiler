package AST.Expr;

import AST.ASTVisitor;
import AST.Util.Position;

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
