package AST.Expr;

import AST.ASTNode;
import Util.Position;
import Util.TypeName;

public abstract class ExprNode extends ASTNode {

    public TypeName typeName = null;

    public ExprNode(Position pos) {
        super(pos);
    }

}
