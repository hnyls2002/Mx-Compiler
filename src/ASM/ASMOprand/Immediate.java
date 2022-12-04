package ASM.ASMOprand;

public class Immediate extends BaseOprand {
    public int immInt;

    public Immediate(int imm) {
        this.immInt = imm;
    }

    @Override
    public String format() {
        return String.valueOf(immInt);
    }
}
