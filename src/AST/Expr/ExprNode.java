package AST.Expr;

import AST.ASTNode;
import AST.Util.Position;
import AST.Util.TypeName;

public abstract class ExprNode extends ASTNode {

    public TypeName typeName = null;

    public ExprNode(Position pos) {
        super(pos);
    }

}
