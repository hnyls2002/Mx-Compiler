package ASM.ASMOprand;

import java.util.HashMap;

import Share.Lang.RV32;

public class PhysicalReg extends Register {
    public String regName;

    public PhysicalReg(String regName) {
        this.regName = regName;
    }

    public static HashMap<String, PhysicalReg> phyRegMap = new HashMap<>();

    static {
        for (var regName : RV32.regName)
            phyRegMap.put(regName, new PhysicalReg(regName));
    }

    public static PhysicalReg getPhyReg(String name) {
        return phyRegMap.get(name);
    }

    @Override
    public String format() {
        return regName;
    }
}
