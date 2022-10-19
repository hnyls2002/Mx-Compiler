package Util;

import Parser.MxStarParser.ReturnTypeContext;
import Parser.MxStarParser.TypeNameContext;

public final class TypeName {

    public String typeNameString;
    public int dimen = 0;
    public Position pos;
    public boolean isLeftValue = false;

    public TypeName(String typeNameString, int dimen, boolean islv, Position pos) {
        this.typeNameString = typeNameString;
        this.dimen = dimen;
        this.isLeftValue = islv;
        this.pos = pos;
    }

    public TypeName(String typeNameString, int dimen, boolean islv) {
        this.typeNameString = typeNameString;
        this.dimen = dimen;
        this.isLeftValue = islv;
        this.pos = null;
    }

    public TypeName(TypeNameContext ctx, boolean islv) {
        typeNameString = ctx.typeNameUnit().getText();
        dimen = ctx.LBracket().size();
        pos = new Position(ctx);
        this.isLeftValue = islv;
    }

    public TypeName(ReturnTypeContext ctx, boolean islv) {
        if (ctx.typeName() == null) {
            typeNameString = "void";
            dimen = 0;
        } else {
            typeNameString = ctx.typeName().typeNameUnit().getText();
            dimen = ctx.typeName().LBracket() == null ? 0 : ctx.typeName().LBracket().size();
        }
        pos = new Position(ctx);
        this.isLeftValue = islv;
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

    public boolean isBuiltin() {
        if (dimen != 0)
            return false;
        return typeNameString.equals("int") || typeNameString.equals("bool") || typeNameString.equals("string")
                || typeNameString.equals("void") || typeNameString.equals("null");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TypeName t) {
            if ((dimen != 0 || !isBuiltin()) && t.typeNameString == "null")
                return true;
            if (typeNameString == "null" && (t.dimen != 0 || !isBuiltin()))
                return true;
            return typeNameString.equals(t.typeNameString) && dimen == t.dimen;
        }
        return false;
    }

}
