package AST.Expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import Util.Position;

public class CreatorExprNode extends ExprNode {

    public int dimen = 0, dimenSet = 0;

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
