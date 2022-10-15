package AST.Expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import Util.Position;

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
