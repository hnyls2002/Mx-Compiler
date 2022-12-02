package AST.Expr;

import AST.ASTVisitor;
import AST.Util.Position;

public class ThisExprNode extends ExprNode {

    public ThisExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
