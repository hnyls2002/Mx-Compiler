package AST.Stmt;

import AST.ASTVisitor;
import Util.Position;

public class IfStmtNode extends StmtNode {

    public IfStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
