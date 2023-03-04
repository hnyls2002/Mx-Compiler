package ASM.ASMOprand;

import ASM.ASMOprand.PhysicalReg.ABIType;

public class StackOffset extends RegOffset {
    // offset + sp
    public enum stackDataKind {
        alloca, vReg, putArg, getArg, phi, ra
    }

    public int id = 0;
    public stackDataKind kind;

    public StackOffset(int id, stackDataKind kind) {
        super(new PhysicalReg(ABIType.sp, 0), null);
        this.id = id;
        this.kind = kind;

        // ----------- for debug -----------
        this.offset = new Immediate(0);
        // ----------- for debug -----------
    }
}
