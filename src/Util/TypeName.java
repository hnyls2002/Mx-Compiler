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

    public TypeName(String typeNameString, int dimen) {
        this.typeNameString = typeNameString;
        this.dimen = dimen;
        this.pos = null;
    }

    public TypeName(TypeNameContext ctx) {
        typeNameString = ctx.typeNameUnit().getText();
        dimen = ctx.LBracket().size();
        pos = new Position(ctx);
    }

    public TypeName(ReturnTypeContext ctx) {
        if (ctx.typeName() == null) {
            typeNameString = "void";
            dimen = 0;
        } else {
            typeNameString = ctx.typeName().toString();
            dimen = ctx.typeName().LBracket() == null ? 0 : ctx.typeName().LBracket().size();
        }
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
