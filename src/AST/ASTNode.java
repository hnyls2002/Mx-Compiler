package AST;

import Frontend.Util.Position;
import IR.IRValue.IRBaseValue;

public abstract class ASTNode {
    public Position pos;
    public IRBaseValue irValue = null;
    public IRBaseValue irAddr = null;

    public ASTNode(Position pos) {
        this.pos = pos;
    }

    // data structure should have an 'accept' interface
    abstract public void accept(ASTVisitor visitor);

}
