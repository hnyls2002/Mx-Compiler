package IR.IRValue.IRUser.IRInst;

import java.util.ArrayList;

import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import Share.Visitors.IRInstVisitor;

public class PhiInst extends IRBaseInst {

    public ArrayList<IRBasicBlock> blockList = new ArrayList<>();
    public ArrayList<IRBaseValue> valueList = new ArrayList<>();
    public AllocaInst allocaDef = null;

    public PhiInst(AllocaInst allocaDef) {
        super(allocaDef.elementType);
        this.allocaDef = allocaDef;
    }

    public void addOprand(IRBasicBlock block, IRBaseValue res) {
        if (block != null)
            blockList.add(block);
        if (res != null)
            valueList.add(res);
    }

    public PhiInst(IRBasicBlock block1, IRBaseValue res1, IRBasicBlock block2, IRBaseValue res2,
            IRBasicBlock curBlock) {
        super(res1.valueType);
        if (block1 != null)
            blockList.add(block1);
        if (block2 != null)
            blockList.add(block2);
        if (res1 != null)
            valueList.add(res1);
        if (res2 != null)
            valueList.add(res2);
        if (curBlock != null)
            curBlock.addInst(this);
    }

    @Override
    public String defToString() {
        var ret = "phi " + valueType.toString() + " ";
        for (int i = 0; i < blockList.size(); ++i)
            ret += "[ " + valueList.get(i).useToString() + ", " + blockList.get(i).useToString()
                    + (i == blockList.size() - 1 ? " ]" : " ], ");
        return ret;
    }

    @Override
    public void accept(IRInstVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void replaceUse(IRBaseValue oldUse, IRBaseValue newUse) {
        ArrayList<IRBaseValue> tmp = new ArrayList<>();
        for (var val : valueList) {
            if (val == oldUse)
                tmp.add(newUse);
            else
                tmp.add(val);
        }
        valueList = tmp;
    }
}
