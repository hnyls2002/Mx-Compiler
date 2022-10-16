package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

public class LiteralExprNode extends ExprNode {

    public enum literalType {
        INT, STRING, TRUE, FALSE, NULL;

        public String litString;
    };

    public literalType lit;

    public LiteralExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
