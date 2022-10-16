package AST.Stmt;

import java.util.ArrayList;

import AST.ASTVisitor;
import Util.Position;

public class SuiteStmtNode extends StmtNode {

    public ArrayList<StmtNode> StmtList = new ArrayList<>();

    public SuiteStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
