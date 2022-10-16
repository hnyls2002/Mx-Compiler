package AST.Util;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Util.Position;

public class VarSingleDecNode extends ASTNode {

    public String id = null;
    public ExprNode expr = null;

    public VarSingleDecNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
