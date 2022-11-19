package AST.Stmt;

import AST.ASTVisitor;
import Frontend.Util.Position;

public class ContinueStmtNode extends StmtNode {

    public ContinueStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
