package ASM.ASMOprand;

import ASM.ASMOprand.PhysicalReg.ABIType;

public class StackOffset extends RegOffset {
    // offset + sp
    public int id = 0;

    public StackOffset(int id) {
        super(new PhysicalReg(ABIType.sp, id), null);
    }
}
