package AST.Expr;

import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class AssignExprNode extends ExprNode {

    public ExprNode lvalue, rvalue;

    public AssignExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
