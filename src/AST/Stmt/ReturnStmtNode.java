package AST.Stmt;

import AST.ASTVisitor;
import Util.Position;

public class ReturnStmtNode extends StmtNode {

    public ReturnStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
