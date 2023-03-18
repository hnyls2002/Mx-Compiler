package IR.IRValue.IRUser.ConsValue.ConsData;

import IR.IRType.IRArrayType;
import IR.IRType.IRIntType;
import IR.IRType.IRPtType;
import IR.IRType.IRType;

public class StrConst extends BaseConstData {
    public String data;
    public IRType derefType;

    public StrConst(String litString, int len, int idx) {
        super(new IRPtType(new IRArrayType(new IRIntType(8), len + 1), 1)); // "hello world" -> "hello // world\n"
        this.derefType = new IRArrayType(new IRIntType(8), len + 1);
        this.data = litString;
        this.nameString = ".str." + idx;
    }

    @Override
    public String formatDef() {
        return derefType.formatType() + ' ' + 'c' + "\"" + data + "\\00" + "\"";
    }

}
