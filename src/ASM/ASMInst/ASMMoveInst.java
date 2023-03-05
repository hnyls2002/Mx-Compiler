package ASM.ASMInst;

import ASM.ASMBlock;
import ASM.ASMOprand.Register;
import Share.Visitors.ASMInstVisitor;

public class ASMMoveInst extends ASMBaseInst {
    public ASMMoveInst(Register rd, Register rs1, ASMBlock block) {
        this.rd = rd;
        this.rs1 = rs1;
        block.instList.add(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\tmv\t%s, %s\n", rd.format(), rs1.format());
    }
}
