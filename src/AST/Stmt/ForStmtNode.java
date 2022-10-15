package AST.Stmt;

import AST.ASTVisitor;
import Util.Position;

public class ForStmtNode extends StmtNode {

    public ForStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);

    }
}
