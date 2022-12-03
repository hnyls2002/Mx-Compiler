package ASM.ASMOprand;

public class PhysicalReg extends Register {
    public enum ABIType {
        arg, sp, tmp, saved
    }

    public ABIType regType;
    public int retId;

    public PhysicalReg(ABIType regType, int regId) {
        this.regType = regType;
        this.retId = regId;
    }
}
