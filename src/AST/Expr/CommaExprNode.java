package AST.Expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import Util.Position;

public class CommaExprNode extends ExprNode {

    public ArrayList<ExprNode> exprList = new ArrayList<>();

    public CommaExprNode(Position pos) {
        super(pos);
    }

    public ExprNode last() {
        return exprList.get(exprList.size() - 1);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
