package ASM.ASMInst;

import ASM.ASMBlock;
import ASM.ASMOprand.Immediate;
import ASM.ASMOprand.Register;
import Share.Visitors.ASMInstVisitor;

public class ASMLiInst extends ASMBaseInst {
    public ASMLiInst(Register rd, Immediate imm, ASMBlock block) {
        this.rd = rd;
        this.imm = imm;
        block.instList.add(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\tli\t%s, %s\n", rd.format(), imm.format());
    }
}
