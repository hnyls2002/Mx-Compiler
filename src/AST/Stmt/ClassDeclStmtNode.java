package AST.Stmt;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;

public class ClassDeclStmtNode extends ASTNode {

    public ClassDeclStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
