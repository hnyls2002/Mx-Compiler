package ASM.ASMOprand;

public class PhysicalReg extends Register {
    public enum ABIType {
        arg, sp, ra, tmp, saved
    }

    public ABIType regType;
    public int retId;

    public PhysicalReg(ABIType regType, int regId) {
        this.regType = regType;
        this.retId = regId;
    }

    @Override
    public String format() {
        return switch (regType) {
            case arg -> "a" + retId;
            case ra -> "ra";
            case saved -> "s" + retId;
            case sp -> "sp";
            case tmp -> "t" + retId;
        };
    }
}
