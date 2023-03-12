package IR.IRValue;

import IR.IRType.IRType;
import Share.MyException;

public class IRVReg extends IRBaseValue {

    // temporary virtual register for phi elimination
    // ----------------------
    // The source is :
    // x3 = phi(x1,x2)
    // The target is :
    // x1 -> tmp && x2 -> tmp in different block
    // tmp -> x3
    // ----------------------
    // tmp can't be def and can't be interpreted in LLVMIR

    public IRVReg(IRType valueType) {
        super(valueType);
    }

    @Override
    public String defToString() {
        throw new MyException("VReg don't need to be defined");
    }

    @Override
    public String useToString() {
        return '%' + getName();
    }
}
