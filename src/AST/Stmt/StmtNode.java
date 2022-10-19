package AST.Stmt;

import AST.ASTNode;
import Util.Position;
import Util.TypeName;

public abstract class StmtNode extends ASTNode {

    public TypeName retType = null;

    public StmtNode(Position pos) {
        super(pos);
    }
}
