package IR.IRValue.IRUser.Inst;

import java.util.ArrayList;

import IR.IRType.IRPtType;
import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;

public class GEPInst extends IRBaseInst {
    public IRType startType;
    public IRBaseValue startPtr;
    public ArrayList<IntConst> indices = new ArrayList<>();

    public GEPInst(IRBaseValue startPtr, IRType gepInstType, int idx1, int idx2, IRBasicBlock block) {
        super(gepInstType);
        this.startPtr = startPtr;
        this.startType = ((IRPtType) this.startPtr.valueType).derefType();
        this.indices.add(new IntConst(idx1, 64));
        this.indices.add(new IntConst(idx2, 64));
        block.addInst(this);
    }

    public void addIdx(int idx) {
        // i64 or i32 ?
        indices.add(new IntConst(idx, 64));
    }

    @Override
    public String defToString() {
        var ret = "getelementptr " + startType.toString() + ", ";
        ret += startPtr.valueType.toString() + ' ' + startPtr.getName();
        for (var idx : indices)
            ret += ", " + idx.useToStringWithType();
        return ret;
    }

}
