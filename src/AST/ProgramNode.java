package AST;

import java.util.ArrayList;

import Frontend.Util.Position;

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
