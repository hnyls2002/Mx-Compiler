package IR.IRValue.IRUser.Inst;

import java.util.ArrayList;

import Debug.MyException;
import IR.IRType.IRPtType;
import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;

public class GEPInst extends IRBaseInst {
    public IRType startType;
    public IRBaseValue startPtr;
    public ArrayList<IntConst> indices = new ArrayList<>();

    public GEPInst(IRBaseValue startPtr, IRType gepInstType) {
        super(gepInstType);
        this.startPtr = startPtr;
        if (this.startPtr.valueType instanceof IRPtType t)
            this.startType = t.derefType();
        else
            throw new MyException("gep instruction's start pointer should be an pointer type");
    }

    public void addIdx(int idx) {
        // i64 or i32 ?
        indices.add(new IntConst(idx, 64));
    }

    @Override
    public String defToString() {
        var ret = "getelementptr" + " (" + startType.toString() + ", ";
        ret += startPtr.valueType.toString() + ' ' + startPtr.getName();
        for (var idx : indices)
            ret += ", " + idx.useToString();
        ret += ")";
        return ret;
    }

}
