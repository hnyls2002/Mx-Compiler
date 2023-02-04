package IR.IRValue.IRUser.IRInst;

import java.util.ArrayList;

import IR.IRType.IRPtType;
import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import Share.Visitors.IRInstVisitor;

public class GEPInst extends IRBaseInst {
    public IRType startType;
    public IRBaseValue startPtr;
    public ArrayList<IRBaseValue> indices = new ArrayList<>();

    public GEPInst(IRBaseValue startPtr, IRType gepInstType, IRBasicBlock block, IRBaseValue... idxList) {
        super(gepInstType);
        this.startPtr = startPtr;
        this.startType = ((IRPtType) this.startPtr.valueType).derefType();
        for (var idx : idxList)
            this.indices.add(idx);
        if (block != null)
            block.addInst(this);
    }

    public void addIdx(int idx) {
        // i64 or i32 ? -> i32
        indices.add(new IntConst(idx, 32));
    }

    @Override
    public String defToString() {
        var ret = "getelementptr " + startType.toString() + ", ";
        ret += startPtr.valueType.toString() + ' ' + startPtr.useToString();
        for (var idx : indices)
            ret += ", " + idx.useToStringWithType();
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void replaceUse(IRBaseValue oldUse, IRBaseValue newUse) {
        if (startPtr == oldUse)
            startPtr = newUse;
        ArrayList<IRBaseValue> tmp = new ArrayList<>();
        for (var val : indices) {
            if (val == oldUse)
                tmp.add(newUse);
            else
                tmp.add(val);
        }
        indices = tmp;
    }

}
