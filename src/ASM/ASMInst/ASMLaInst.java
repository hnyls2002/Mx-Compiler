package ASM.ASMInst;

import ASM.ASMBlock;
import ASM.ASMOprand.Register;
import ASM.ASMOprand.ASMGlobal.ASMGlobalData;
import Share.Visitors.ASMInstVisitor;

public class ASMLaInst extends ASMBaseInst {
    public Register rd;
    public ASMGlobalData symbol;

    public ASMLaInst(Register rd, ASMGlobalData symbol, ASMBlock block) {
        this.rd = rd;
        this.symbol = symbol;
        block.addInst(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\tla\t%s, %s\n", rd.format(), symbol.format());
    }
}
