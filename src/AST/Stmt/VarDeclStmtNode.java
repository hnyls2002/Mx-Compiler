package AST.Stmt;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;

public class VarDeclStmtNode extends ASTNode {

    public VarDeclStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
