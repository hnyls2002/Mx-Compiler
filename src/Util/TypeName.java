package Util;

import Parser.MxStarParser.ReturnTypeContext;
import Parser.MxStarParser.TypeNameContext;

public final class TypeName {

    private String typeNameString;
    private int dimen = 0;
    public Position pos;

    public TypeName(String typeNameString, int dimen, Position pos) {
        this.typeNameString = typeNameString;
        this.dimen = dimen;
        this.pos = pos;
    }

    public TypeName(TypeNameContext ctx) {
        typeNameString = ctx.typeNameUnit().toString();
        dimen = ctx.LBracket().size() + 1;
        pos = new Position(ctx);
    }

    public TypeName(ReturnTypeContext ctx) {
        typeNameString = ctx.typeName().isEmpty() ? "void" : ctx.typeName().toString();
        dimen = ctx.typeName().LBracket().size() + 1;
        pos = new Position(ctx);
    }

    public boolean isUnit() {
        return dimen == 0;
    }

    public int getDimen() {
        return dimen;
    }

    @Override
    public String toString() {
        return typeNameString;
    }

}
