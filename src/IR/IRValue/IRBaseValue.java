package IR.IRValue;

import java.util.ArrayList;

import ASM.ASMOprand.BaseOprand;
import IR.IRType.IRType;
import IR.IRValue.IRUser.IRBaseUser;
import Share.MyException;

public abstract class IRBaseValue {
    public IRType valueType;
    public String nameString;
    public ArrayList<IRBaseUser> userList = new ArrayList<>();

    // for assembly
    public BaseOprand asOprand;

    public IRBaseValue(IRType valueType) {
        this.valueType = valueType;
    }

    public void replaceAllUseWith(IRBaseValue nVal) {
        if (this == nVal)
            return;
        ArrayList<IRBaseUser> tmpUserList = new ArrayList<>(userList);
        for (var user : tmpUserList) {
            for (int i = 0; i < user.getOprandNum(); ++i) {
                if (user.getOprand(i) == this)
                    user.setOprand(i, nVal);
            }
        }
    }

    public final String getName() {
        if (nameString == null)
            throw new MyException("NullName");
        return nameString;
    }

    public final void setName(String nameString) {
        this.nameString = nameString;
    }

    // we don't present the name, name was handled during IRPrinter
    // def means when the value is created
    public abstract String formatDef();

    public abstract String formatUse();

    public String formatUseWithType() {
        return valueType.formatType() + ' ' + formatUse();
    }

}
