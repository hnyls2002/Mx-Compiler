package ASM.ASMOprand;

public class RegOffset extends BaseOprand {
    // offset + reg
    public Register reg;
    public Immediate offset;

    public RegOffset(Register reg, Immediate offset) {
        this.reg = reg;
        this.offset = offset;
    }

    @Override
    public String format() {
        return String.format("%s(%s)", offset.format(), reg.format());
    }
}
