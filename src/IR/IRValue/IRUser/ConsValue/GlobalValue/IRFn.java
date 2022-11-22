package IR.IRValue.IRUser.ConsValue.GlobalValue;

import java.util.ArrayList;

import IR.IRType.IRFnType;
import IR.IRValue.IRBasicBlock;

public class IRFn extends BaseGlobalValue {
    public String fnNameString;
    public ArrayList<IRBasicBlock> blockList = new ArrayList<>();

    public IRFn(String fnNamString, IRFnType fnType) {
        super(fnType);
        this.fnNameString = fnNamString;
    }

    public void AddBlock(IRBasicBlock block) {
        blockList.add(block);
    }

    @Override
    public String toString() {
        var ret = "define" + ' ';
        ret += valueType.toString();
        return ret;
    }

}
