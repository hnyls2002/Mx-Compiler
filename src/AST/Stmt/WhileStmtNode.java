package AST.Stmt;

import AST.ASTVisitor;
import Util.Position;

public class WhileStmtNode extends StmtNode {

    public WhileStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
