package AST.Types;

import AST.Info.FuncInfo;
import AST.Scopes.GlobalScope;
import AST.Util.Position;

public class ArrayType extends BaseType {

    public BaseType uniType;
    public int dimen = 0;

    public ArrayType(GlobalScope gScope, BaseType uniType, int dimen, Position pos) {
        super(pos);
        this.uniType = uniType;
        this.dimen = dimen;
        FuncInfo size = new FuncInfo(gScope.intName, "size", new Position(0, 0));
        funMap.put("size", size);
    }

    @Override
    public boolean isBuiltin() {
        return uniType.isBuiltin();
    }

    @Override
    public boolean isClass() {
        return uniType.isClass();
    }
}
