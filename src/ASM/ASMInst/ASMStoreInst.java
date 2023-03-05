package ASM.ASMInst;

import ASM.ASMBlock;
import ASM.ASMOprand.Immediate;
import ASM.ASMOprand.Register;
import Share.Lang.RV32;
import Share.Visitors.ASMInstVisitor;

public class ASMStoreInst extends ASMBaseInst {
    public RV32.BitWidth bitWidth;
    // addr = rs1 + imm
    // value in rs2

    public ASMStoreInst(Register rs1, Register rs2, Immediate imm, RV32.BitWidth bitWidth, ASMBlock block) {
        this.rs1 = rs1;
        this.rs2 = rs2;
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
        return String.format("\ts%s\t%s, %s(%s)\n", bitWidth, rs2.format(), imm.format(), rs1.format());
    }
}
