package ASM.ASMOprand;

public class StackOffset extends RegOffset {
    // offset + sp
    public enum stackDataKind {
        alloca, vReg, putArg, getArg, phi, ra
    }

    public int id = 0;
    public stackDataKind kind;

    public StackOffset(int id, stackDataKind kind) {
        super(PhysicalReg.getPhyReg("sp"), null);
        this.id = id;
        this.kind = kind;

        // ----------- for debug -----------
        this.offset = new Immediate(-1);
        // ----------- for debug -----------
    }
}
