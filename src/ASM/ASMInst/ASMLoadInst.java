package ASM.ASMInst;

import ASM.ASMBlock;
import ASM.ASMOprand.Immediate;
import ASM.ASMOprand.Register;
import Share.Lang.RV32;
import Share.Visitors.ASMInstVisitor;

public class ASMLoadInst extends ASMBaseInst {
    public RV32.BitWidth bitWidth;

    public ASMLoadInst(Register rd, Register rs1, Immediate imm, RV32.BitWidth bitWidth, ASMBlock block) {
        this.rd = rd;
        this.rs1 = rs1;
        this.imm = imm;
        this.bitWidth = bitWidth;
        block.instList.add(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\tl%s\t%s, %s(%s)\n", bitWidth, rd.format(), imm.format(), rs1.format());
    }
}
