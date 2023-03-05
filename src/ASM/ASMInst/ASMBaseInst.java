package ASM.ASMInst;

import java.util.HashSet;

import ASM.ASMOprand.Immediate;
import ASM.ASMOprand.Register;
import Share.Visitors.ASMInstVisitor;

public abstract class ASMBaseInst {
    public Register rd, rs1, rs2;
    public Immediate imm;

    public abstract void accept(ASMInstVisitor visitor);

    public abstract String format();

    public HashSet<Register> getUse() {
        HashSet<Register> ret = new HashSet<>();
        if (rs1 != null)
            ret.add(rs1);
        if (rs2 != null)
            ret.add(rs2);
        return ret;
    }

    public HashSet<Register> getDef() {
        HashSet<Register> ret = new HashSet<>();
        if (rd != null)
            ret.add(rd);
        return ret;
    }
}
