package ASM.ASMInst;

import ASM.ASMBlock;
import ASM.ASMOprand.Immediate;
import ASM.ASMOprand.Register;
import Share.MyException;
import Share.Lang.RV32.ASMOp;
import Share.Lang.RV32.BinaryImOp;
import Share.Lang.RV32.BinaryRegOp;
import Share.Lang.RV32.BinaryZeroOp;
import Share.Visitors.ASMInstVisitor;

public class ASMCalcInst extends ASMBaseInst {
    public ASMOp op;

    public ASMCalcInst(ASMOp op, Register rd, Register rs1, Register rs2, Immediate imm, ASMBlock block) {
        this.op = op;
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.imm = imm;
        block.instList.add(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        if (op instanceof BinaryRegOp)
            return String.format("\t%s\t%s, %s, %s\n", op.format(), rd.format(), rs1.format(), rs2.format());
        else if (op instanceof BinaryImOp)
            return String.format("\t%s\t%s, %s, %s\n", op.format(), rd.format(), rs1.format(), imm.format());
        else if (op instanceof BinaryZeroOp)
            return String.format("\t%s\t%s, %s\n", op.format(), rd.format(), rs1.format());
        else
            throw new MyException("calc have no operator");
    }

}
