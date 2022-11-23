package IR.IRValue.IRUser.ConsValue.ConsData;

import IR.IRType.IRType;
import IR.IRType.IRType.IRTypeId;

public class NullConst extends BaseConstData {

    // null is only used for pointer initialize, it's type can be any pointer type!
    public NullConst() {
        super(new IRType(IRTypeId.PtTypeId));
    }

    @Override
    public String useToString() {
        return "null";
    }

}
