package Util;

import Parser.MxStarParser.ParameterContext;

public final class TypeIdPair {
    public TypeName typeName;
    public String Id;
    public Position pos;

    public TypeIdPair(TypeName ty, String id, Position pos) {
        this.typeName = ty;
        this.Id = id;
        this.pos = pos;
    }

    public TypeIdPair(TypeName ty, String id) {
        this.typeName = ty;
        this.Id = id;
        this.pos = null;
    }

    public TypeIdPair(ParameterContext ctx) {
        typeName = new TypeName(ctx.typeName(), true);
        Id = ctx.Identifier().getText();
        pos = new Position(ctx);
    }

    @Override
    public String toString() {
        return "(" + typeName.toString() + "," + Id + ")";
    }

}
