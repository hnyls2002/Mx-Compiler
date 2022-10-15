package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

public class BinaryOpExprNode extends ExprNode {

    public ExprNode lhs, rhs;

    public enum binaryOp {
        ADD, SUB, MUL, DIV, MUl, SHIFT_LEFT, SHIFT_RIGHT, LESS, LESS_EQUAL, GREATER, GREATER_EQUAL, EQUAL, NOT_EQUAL,
        BIT_AND, BIT_XOR, BIT_OR, LOGIC_AND, LOGIC_OR
    }

    public binaryOp opcode;

    public BinaryOpExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
