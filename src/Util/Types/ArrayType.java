package Util.Types;

import java.util.ArrayList;

public class ArrayType extends BaseType {

    public BaseType uniType;
    public int dimen = 0, dimenset = 0;
    public ArrayList<Integer> dimenSize = new ArrayList<>();

    @Override
    public boolean isBuiltin() {
        return uniType.isBuiltin();
    }

    @Override
    public boolean isClass() {
        return uniType.isClass();
    }
}
