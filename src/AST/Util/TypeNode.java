package AST.Util;

import AST.ASTNode;
import AST.ASTVisitor;
import Parser.MxStarParser.ReturnTypeContext;
import Parser.MxStarParser.TypeNameContext;
import Util.Position;

public final class TypeNode extends ASTNode {

    public int dimen = 0;
    public String typeNameString;

    public TypeNode(Position pos, TypeNameContext ctx) {
        super(pos);
        getInfoTypeName(ctx);
    }

    public TypeNode(Position pos, ReturnTypeContext ctx) {
        super(pos);
        getInfoRetType(ctx);
    }

    private void getInfoTypeName(TypeNameContext ctx) {
        typeNameString = ctx.typeNameUnit().getText();
        dimen = ctx.LBracket().size() + 1;
    }

    private void getInfoRetType(ReturnTypeContext ctx) {
        if (ctx.typeName().isEmpty())
            typeNameString = "void";
        else
            getInfoTypeName(ctx.typeName());
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
