package Share.Visitors;

import IR.IRValue.IRUser.IRInst.BinaryInst;
import IR.IRValue.IRUser.IRInst.BrInst;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.GEPInst;
import IR.IRValue.IRUser.IRInst.IcmpInst;
import IR.IRValue.IRUser.IRInst.JumpInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.MoveInst;
import IR.IRValue.IRUser.IRInst.RetInst;
import IR.IRValue.IRUser.IRInst.StoreInst;

public interface IRInstVisitor {
    public void visit(BinaryInst inst);

    public void visit(BrInst inst);

    public void visit(CallInst inst);

    public void visit(GEPInst inst);

    public void visit(IcmpInst inst);

    public void visit(LoadInst inst);

    public void visit(RetInst inst);

    public void visit(StoreInst inst);

    public void visit(JumpInst inst);

    public void visit(MoveInst inst);
}
