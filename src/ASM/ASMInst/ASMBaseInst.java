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

    // use and def only include the assignable registers
    // that is, the unassignable registers will not be analyzed

    public HashSet<Register> getOprands() {
        HashSet<Register> ret = new HashSet<>();
        if (rd != null && rd.isAssignable())
            ret.add(rd);
        if (rs1 != null && rs1.isAssignable())
            ret.add(rs1);
        if (rs2 != null && rs2.isAssignable())
            ret.add(rs2);
        return ret;
    }

    public HashSet<Register> getUse() {
        HashSet<Register> ret = new HashSet<>();
        if (rs1 != null && rs1.isAssignable())
            ret.add(rs1);
        if (rs2 != null && rs2.isAssignable())
            ret.add(rs2);
        return ret;
    }

    public HashSet<Register> getDef() {
        HashSet<Register> ret = new HashSet<>();
        if (rd != null && rd.isAssignable())
            ret.add(rd);
        return ret;
    }

    public void replaceUse(Register oldReg, Register newReg) {
        if (rs1 == oldReg)
            rs1 = newReg;
        if (rs2 == oldReg)
            rs2 = newReg;
    }

    public void replaceDef(Register oldReg, Register newReg) {
        if (rd == oldReg)
            rd = newReg;
    }
}
