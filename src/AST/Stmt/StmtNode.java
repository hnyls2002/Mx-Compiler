package AST.Stmt;

import AST.ASTNode;
import Util.Position;

public abstract class StmtNode extends ASTNode {

    public StmtNode(Position pos) {
        super(pos);
    }
}
