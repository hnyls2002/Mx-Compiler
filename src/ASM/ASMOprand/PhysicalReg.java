package ASM.ASMOprand;

import java.util.HashMap;

import Share.Lang.RV32;

public class PhysicalReg extends Register {
    public String regName;

    public PhysicalReg(String regName) {
        this.regName = regName;
    }

    // all physical registers are stored here and unique in memory
    public static HashMap<String, PhysicalReg> phyRegMap = new HashMap<>();

    static {
        for (var regName : RV32.regList)
            phyRegMap.put(regName, new PhysicalReg(regName));
    }

    public static PhysicalReg getPhyReg(String regName) {
        return phyRegMap.get(regName);
    }

    @Override
    public String format() {
        return regName;
    }
}
