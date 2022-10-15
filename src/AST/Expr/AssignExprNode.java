package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

public class AssignExprNode extends ExprNode {

    public AssignExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
