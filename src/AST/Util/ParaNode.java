package AST.Util;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;

public class ParaNode extends ASTNode {
    public TypeNode type;
    public String nameString;

    public ParaNode(Position pos, TypeNode type, String nameString) {
        super(pos);
        this.type = type;
        this.nameString = nameString;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
