package AST.Stmt;

import java.util.ArrayList;

import AST.Util.Position;
import Share.Visitors.ASTVisitor;

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
