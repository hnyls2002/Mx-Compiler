package AST;

import java.util.ArrayList;

import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class ProgramNode extends ASTNode {

    public ArrayList<ASTNode> blocks = new ArrayList<>();

    public ProgramNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
