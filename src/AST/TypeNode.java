package AST;

import Util.Position;

public final class TypeNode extends ASTNode {

    public int dimen = 0;
    public String typeNameString;

    public TypeNode(Position pos, int dimen, String typeNameString) {
        super(pos);
        this.dimen = dimen;
        this.typeNameString = typeNameString;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
