package IR.IRValue.IRUser;

import java.util.ArrayList;

import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;

public abstract class IRBaseUser extends IRBaseValue {
    public ArrayList<IRBaseValue> oprandList = new ArrayList<>();

    public IRBaseUser(IRType userType) {
        super(userType);
    }

    public void appendOprand(IRBaseValue oprand) {
        oprandList.add(oprand);
    }

    public IRBaseValue getOprand(int idx) {
        return oprandList.get(idx);
    }

    public void setOprand(int idx, IRBaseValue oprand) {
        oprandList.set(idx, oprand);
    }
}
