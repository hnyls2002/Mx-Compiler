package ASM.ASMOprand;

import java.util.HashMap;
import java.util.LinkedList;

import Share.Lang.RV32;

public class PhysicalReg extends Register {
    public String regName;

    private PhysicalReg(String regName) {
        this.regName = regName;
    }

    public static HashMap<String, PhysicalReg> phyRegMap = new HashMap<>();
    public static LinkedList<PhysicalReg> assignableSet = new LinkedList<>();

    static {
        for (var regName : RV32.regName)
            phyRegMap.put(regName, new PhysicalReg(regName));
        phyRegMap.forEach((name, reg) -> assignableSet.add(reg));
        assignableSet.remove(phyRegMap.get("zero"));
        assignableSet.remove(phyRegMap.get("sp"));
        assignableSet.remove(phyRegMap.get("ra"));
        assignableSet.remove(phyRegMap.get("gp"));
        assignableSet.remove(phyRegMap.get("tp"));

        // fortest
        // for (int i = 0; i <= 11; ++i)
        // assignableSet.remove(phyRegMap.get("s" + i));
        // for (int i = 0; i <= 6; ++i)
        // assignableSet.remove(phyRegMap.get("t" + i));
        // for (int i = 2; i <= 7; ++i)
        // assignableSet.remove(phyRegMap.get("a" + i));
    }

    public static PhysicalReg getPhyReg(String name) {
        return phyRegMap.get(name);
    }

    @Override
    public String format() {
        return regName;
    }
}
