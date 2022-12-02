package AST.Expr;

import java.util.ArrayList;

import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class CreatorExprNode extends ExprNode {

    public int dimen = 0;

    public ArrayList<ExprNode> dimenSize = new ArrayList<>();

    public String typeNameString;

    public CreatorExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
