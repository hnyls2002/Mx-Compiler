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

    // clear the connection
    public static void removeOp1Connection(IRBaseUser user, int idx) {
        user.oprandList.get(idx).userList.remove(user);
    }

    // clear all the connection
    public static void removeOpAllConnection(IRBaseUser user) {
        for (int i = 0; i < user.getOprandNum(); ++i)
            removeOp1Connection(user, i);
    }

    public static void deleteOp1(IRBaseUser user, int idx) {
        user.oprandList.remove(idx);
    }

    public int getOprandNum() {
        return oprandList.size();
    }
}
