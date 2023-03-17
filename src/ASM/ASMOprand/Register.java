package ASM.ASMOprand;

public abstract class Register extends BaseOprand {
    public boolean isAssignable() {
        return this instanceof VirtualReg || PhysicalReg.assignableSet.contains(this);
    }
}
