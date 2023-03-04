package ASM.ASMInst;

import ASM.ASMBlock;
import ASM.ASMOprand.Register;
import Share.Visitors.ASMInstVisitor;

public class ASMMoveInst extends ASMBaseInst {
    public Register rd, rs;

    public ASMMoveInst(Register rd, Register rs, ASMBlock block) {
        this.rd = rd;
        this.rs = rs;
        block.addInst(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\tmv\t%s, %s\n", rd.format(), rs.format());
    }
}
