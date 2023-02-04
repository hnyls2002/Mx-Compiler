package ASM.ASMInst;

import java.util.HashSet;

import ASM.ASMBlock;
import ASM.ASMOprand.Immediate;
import ASM.ASMOprand.Register;
import Share.MyException;
import Share.Visitors.ASMInstVisitor;

public class ASMCalcInst extends ASMBaseInst {
    public enum ASMBOP {
        add, sub, mul, div, rem, sll, sra, and, or, xor,
        slt
    }

    public enum ASMBIOP {
        addi, slli, srai, andi, ori, xori,
        slti
    }

    public enum ASMBZOP {
        seqz, snez, sltz, sgtz
    }

    public ASMBOP bop;
    public ASMBIOP biop;
    public ASMBZOP bzop;
    public Register rd, rs1, rs2;
    public Immediate imm;

    public ASMCalcInst(ASMBOP op, Register rd, Register rs1, Register rs2, ASMBlock block) {
        this.bop = op;
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        block.addInst(this);
    }

    public ASMCalcInst(ASMBIOP op, Register rd, Register rs1, Immediate imm, ASMBlock block) {
        this.biop = op;
        this.rd = rd;
        this.rs1 = rs1;
        this.imm = imm;
        block.addInst(this);
    }

    public ASMCalcInst(ASMBZOP op, Register rd, Register rs1, ASMBlock block) {
        this.bzop = op;
        this.rd = rd;
        this.rs1 = rs1;
        block.addInst(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        if (bop != null)
            return String.format("\t%s\t%s, %s, %s\n", bop, rd.format(), rs1.format(), rs2.format());
        else if (biop != null) {
            return String.format("\t%s\t%s, %s, %s\n", biop, rd.format(), rs1.format(), imm.format());
        } else if (bzop != null) {
            return String.format("\t%s\t%s, %s\n", bzop, rd.format(), rs1.format());
        } else
            throw new MyException("calc have no operator");
    }

    @Override
    public HashSet<Register> getDef() {
        HashSet<Register> ret = new HashSet<>();
        ret.add(rd);
        return ret;
    }

    @Override
    public HashSet<Register> getUse() {
        HashSet<Register> ret = new HashSet<>();
        ret.add(rs1);
        if (rs2 != null)
            ret.add(rs2);
        return ret;
    }

    @Override
    public void replaceDef(Register oldDef, Register newDef) {
        if (rd == oldDef)
            rd = newDef;
    }

    @Override
    public void replaceUse(Register oldUse, Register newUse) {
        if (rs1 == oldUse)
            rs1 = newUse;
        if (rs2 == oldUse)
            rs2 = newUse;
    }

}
