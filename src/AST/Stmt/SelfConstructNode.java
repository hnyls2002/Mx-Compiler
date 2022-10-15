package AST.Stmt;

import java.util.ArrayList;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;

public class SelfConstructNode extends ASTNode {

    public String ClassNameString;
    public ArrayList<StmtNode> stmtList = new ArrayList<>();

    public SelfConstructNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
