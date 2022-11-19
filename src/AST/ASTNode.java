package AST;

import Frontend.Util.Position;

public abstract class ASTNode {
    public Position pos;

    public ASTNode(Position pos) {
        this.pos = pos;
    }

    // data structure should have an 'accept' interface
    abstract public void accept(ASTVisitor visitor);

}
