package AST.Expr;

import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class LiteralExprNode extends ExprNode {

    public enum literalType {
        INT, STRING, TRUE, FALSE, NULL;
    };

    public literalType lit;
    public String litString;

    public LiteralExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
