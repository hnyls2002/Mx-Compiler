package AST.Expr;

import AST.Util.Position;
import Share.Visitors.ASTVisitor;

// "unary" oppsites to "binary" which means "单目"!
public class UnaryOpExprNode extends ExprNode {

    public ExprNode expr;

    public enum unaryOp {
        SUF_INC, SUF_DEC, PRE_INC, PRE_DEC, LOGIC_NOT, BIT_NOT, PRE_ADD, PRE_SUB
    }

    public unaryOp opCode;

    public UnaryOpExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
