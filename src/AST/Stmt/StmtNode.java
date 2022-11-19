package AST.Stmt;

import AST.ASTNode;
import Frontend.Util.Position;
import Frontend.Util.TypeName;

public abstract class StmtNode extends ASTNode {

    public TypeName retStmtType = null;

    public StmtNode(Position pos) {
        super(pos);
    }
}
