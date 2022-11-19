package AST.Expr;

import AST.ASTVisitor;
import Frontend.Util.Position;

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
