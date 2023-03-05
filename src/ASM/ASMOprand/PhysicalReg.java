package ASM.ASMOprand;

import java.util.HashMap;
import java.util.HashSet;

import Share.Lang.RV32;

public class PhysicalReg extends Register {
    public String regName;

    private PhysicalReg(String regName) {
        this.regName = regName;
    }

    // all physical registers are stored here and unique in memory
    private static HashMap<String, PhysicalReg> phyRegMap = new HashMap<>();

    public static PhysicalReg getPhyReg(String regName) {
        return phyRegMap.get(regName);
    }

    public static HashSet<PhysicalReg> assignableSet = new HashSet<>();
    public static HashSet<PhysicalReg> calleeSavedReg = new HashSet<>();
    public static HashSet<PhysicalReg> callerSavedReg = new HashSet<>();

    static {
        for (var regName : RV32.regList)
            phyRegMap.put(regName, new PhysicalReg(regName));

        assignableSet.addAll(phyRegMap.values());
        assignableSet.remove(phyRegMap.get("zero"));
        assignableSet.remove(phyRegMap.get("ra"));
        assignableSet.remove(phyRegMap.get("sp"));
        assignableSet.remove(phyRegMap.get("gp"));
        assignableSet.remove(phyRegMap.get("tp"));

        for (int i = 0; i <= 11; ++i)
            calleeSavedReg.add(phyRegMap.get("s" + i));
        for (int i = 0; i <= 6; ++i)
            callerSavedReg.add(phyRegMap.get("t" + i));
        for (int i = 0; i <= 7; ++i)
            callerSavedReg.add(phyRegMap.get("a" + i));
    }

    @Override
    public String format() {
        return regName;
    }
}
