package AST.Expr;

import java.util.ArrayList;

import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class FuncCallExprNode extends ExprNode {

    public String FuncNameString;

    public ArrayList<ExprNode> argList = new ArrayList<>();

    public FuncCallExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
