package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

// Know as IdentifierExpression in .g4 file
public class VarExprNode extends ExprNode {

    public VarExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
