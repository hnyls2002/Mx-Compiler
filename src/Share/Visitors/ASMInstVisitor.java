package Share.Visitors;

import ASM.ASMInst.ASMBrInst;
import ASM.ASMInst.ASMCalcInst;
import ASM.ASMInst.ASMCallInst;
import ASM.ASMInst.ASMJInst;
import ASM.ASMInst.ASMLaInst;
import ASM.ASMInst.ASMLiInst;
import ASM.ASMInst.ASMLoadInst;
import ASM.ASMInst.ASMMoveInst;
import ASM.ASMInst.ASMRetInst;
import ASM.ASMInst.ASMStoreInst;

public interface ASMInstVisitor {
    public void visit(ASMBrInst inst);

    public void visit(ASMCalcInst inst);

    public void visit(ASMCallInst inst);

    public void visit(ASMJInst inst);

    public void visit(ASMLaInst inst);

    public void visit(ASMLiInst inst);

    public void visit(ASMLoadInst inst);

    public void visit(ASMStoreInst inst);

    public void visit(ASMMoveInst inst);

    public void visit(ASMRetInst inst);
}
