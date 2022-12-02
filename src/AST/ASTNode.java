package AST;

import AST.Util.Position;
import IR.IRValue.IRBaseValue;
import Share.Visitors.ASTVisitor;

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
