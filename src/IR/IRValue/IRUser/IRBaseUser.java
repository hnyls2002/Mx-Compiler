package IR.IRValue.IRUser;

import java.util.ArrayList;

import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;

public abstract class IRBaseUser extends IRBaseValue {
    private ArrayList<IRBaseValue> oprandList = new ArrayList<>();

    public IRBaseUser(IRType userType) {
        super(userType);
    }

    public void appendOprand(IRBaseValue oprand) {
        oprandList.add(oprand);
        oprand.userList.add(this);
    }

    public IRBaseValue getOprand(int idx) {
        return oprandList.get(idx);
    }

    public void setOprand(int idx, IRBaseValue oprand) {
        oprandList.get(idx).userList.remove(this);
        oprandList.set(idx, oprand);
        oprand.userList.add(this);
    }

    public void insertOprand(int idx, IRBaseValue oprand) {
        oprandList.add(idx, oprand);
        oprand.userList.add(this);
    }

    public void removeOp(int idx) {
        oprandList.remove(idx);
    }

    public int getOprandNum() {
        return oprandList.size();
    }
}
