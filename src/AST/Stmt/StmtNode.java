package AST.Stmt;

import AST.ASTNode;
import AST.Util.Position;
import AST.Util.TypeName;

public abstract class StmtNode extends ASTNode {

    public TypeName retStmtType = null;

    public StmtNode(Position pos) {
        super(pos);
    }
}
