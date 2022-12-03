package ASM.ASMInst;

import ASM.ASMOprand.Immediate;
import ASM.ASMOprand.Register;

public class ASMBinaryInst extends ASMBaseInst {
    public enum ASMBOP {
        addi, add, sub, mul, div, rem
    }

    public ASMBOP op;
    public Register rd, rs1, rs2;
    public Immediate imm;

    public ASMBinaryInst(ASMBOP op, Register rd, Register rs1, Register rs2) {
        this.op = op;
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
    }

    public ASMBinaryInst(ASMBOP op, Register rd, Register rs1, Immediate imm) {
        this.op = op;
        this.rd = rd;
        this.rs1 = rs1;
        this.imm = imm;
    }

}
