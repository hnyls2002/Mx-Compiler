package ASM.ASMOprand;


public class VirtualReg extends Register {
    public static int virtualRegCnt = 0;
    public int id;

    public VirtualReg() {
        this.id = virtualRegCnt++;
    }

    @Override
    public String format() {
        return "v" + id;
    }
}
