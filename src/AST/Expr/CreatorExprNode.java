package AST.Expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import Util.Position;

public class CreatorExprNode extends ExprNode {

    public int dimen = 0, dimenSet = 0;

    public ArrayList<Integer> dimenSize = new ArrayList<>();

    public CreatorExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
