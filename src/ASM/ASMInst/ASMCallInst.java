package ASM.ASMInst;

import java.util.HashSet;

import ASM.ASMBlock;
import ASM.ASMOprand.PhysicalReg;
import ASM.ASMOprand.Register;
import Share.Lang.RV32;
import Share.Visitors.ASMInstVisitor;

public class ASMCallInst extends ASMBaseInst {

    public String fnName;
    public int argNum;

    public ASMCallInst(String fnName, int argNum, ASMBlock block) {
        this.fnName = fnName;
        this.argNum = argNum;
        block.addInst(this);
    }

    @Override
    public void accept(ASMInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String format() {
        return String.format("\tcall\t%s\n", fnName);
    }

    @Override
    public HashSet<Register> getDef() {
        // for all live virables can be assigned to callee saved reg
        return new HashSet<>(PhysicalReg.callerSavedReg);
    }

    @Override
    public HashSet<Register> getUse() {
        HashSet<Register> ret = new HashSet<>();
        for (int i = 0; i < Math.min(argNum, RV32.MAX_ARG_NUM); ++i)
            ret.add(PhysicalReg.getPhyReg("a" + i));
        return ret;
    }

    @Override
    public void replaceDef(Register oldDef, Register newDef) {
    }

    @Override
    public void replaceUse(Register oldUse, Register newUse) {
    }
}
