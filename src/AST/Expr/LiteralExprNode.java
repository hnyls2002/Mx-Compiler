package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

public class LiteralExprNode extends ExprNode {

    public LiteralExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
