package IR.IRValue.IRUser.IRInst;

import IR.IRType.IRIntType;
import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import Share.Lang.LLVMIR.CastType;
import Share.Visitors.IRInstVisitor;

public class CastInst extends IRBaseInst {

    public IRType targetType = null;
    public CastType opCode = null;

    public static IRBaseValue tryBoolCast(IRBaseValue srcValue, IRType targetType, CastType opCode,
            IRBasicBlock block) {
        if (srcValue.valueType instanceof IRIntType int1 && targetType instanceof IRIntType int2 && int1.intLen == 1
                && int2.intLen == 1)
            return srcValue;
        else
            return new CastInst(srcValue, targetType, opCode, block);
    }

    public CastInst(IRBaseValue srcValue, IRType targetType, CastType opCode, IRBasicBlock block) {
        super(targetType, block);
        appendOprand(srcValue);
        this.targetType = targetType;
        this.opCode = opCode;
        if (block != null)
            block.addInst(this);
    }

    public CastInst(IRBaseValue srcValue, IRType targetType, CastType opCode, IRBasicBlock block, int insertIdx) {
        super(targetType, block);
        appendOprand(srcValue);
        this.targetType = targetType;
        this.opCode = opCode;
        block.instList.add(insertIdx, this);
    }

    @Override
    public String formatDef() {
        var ret = opCode.name() + ' ' + getOprand(0).formatUseWithType() + " to ";
        ret += targetType.formatType();
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public IRBaseInst copy() {
        return new CastInst(getOprand(0), targetType, opCode, null);
    }
}
