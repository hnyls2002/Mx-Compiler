package ASM.ASMOprand;

public class StackOffset extends RegOffset {
    // offset + sp
    public int id = 0;

    public StackOffset(int id) {
        this.id = id;
    }
}
