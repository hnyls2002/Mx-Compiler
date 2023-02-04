package ASM.ASMInst;

import java.util.HashSet;

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

    @Override
    public HashSet<Register> getDef() {
        HashSet<Register> ret = new HashSet<>();
        ret.add(rd);
        return ret;
    }

    @Override
    public HashSet<Register> getUse() {
        return new HashSet<>();
    }

    @Override
    public void replaceDef(Register oldDef, Register newDef) {
        if (rd == oldDef)
            rd = newDef;
    }

    @Override
    public void replaceUse(Register oldUse, Register newUse) {
    }
}
