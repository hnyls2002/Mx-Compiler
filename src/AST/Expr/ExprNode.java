package AST.Expr;

import AST.ASTNode;
import Util.Position;
import Util.Types.BaseType;

public abstract class ExprNode extends ASTNode {

    public BaseType type = null;

    public ExprNode(Position pos) {
        super(pos);
    }

}
