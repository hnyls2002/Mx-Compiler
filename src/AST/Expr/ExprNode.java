package AST.Expr;

import AST.ASTNode;
import Frontend.Util.Position;
import Frontend.Util.TypeName;

public abstract class ExprNode extends ASTNode {

    public TypeName typeName = null;

    public ExprNode(Position pos) {
        super(pos);
    }

}
