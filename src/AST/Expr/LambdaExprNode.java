package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

public class LambdaExprNode extends ExprNode {

    public LambdaExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
