package ASM.ASMOprand;

import Share.Lang.RV32.SPLabel;

public class VirtualOffset extends Immediate {

    public SPLabel label;
    public int rank;
    // rank means the order to place it in the stack

    public VirtualOffset(SPLabel label, int rank) {
        super(-1); // -1 means this is a virtual offset
        this.label = label;
        this.rank = rank;
    }

    @Override
    public String format() { // for debug
        return label.name() + rank;
    }
}
