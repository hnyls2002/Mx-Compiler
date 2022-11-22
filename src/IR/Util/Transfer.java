package IR.Util;

import Debug.MyException;
import Frontend.Util.TypeName;
import Frontend.Util.Types.BaseType;
import Frontend.Util.Types.FuncInfo;
import IR.IRType.IRType;
import IR.IRType.IRFnType;
import IR.IRType.IRIntType;
import IR.IRType.IRPtType;
import IR.IRType.IRStructType;
import IR.IRType.IRVoidType;

public class Transfer {
    public static IRType typeTransfer(TypeName astTypeName) {
        if (astTypeName.typeNameString.equals("int"))
            return new IRIntType(32);
        else if (astTypeName.typeNameString.equals("bool"))
            return new IRIntType(1);
        else if (astTypeName.typeNameString.equals("string"))// string : i8*
            return new IRPtType(new IRIntType(8), 1);
        else if (astTypeName.typeNameString.equals("null"))
            throw new MyException("No null type should be transfered!");
        else if (astTypeName.typeNameString.equals("void"))
            return new IRVoidType();
        else
            return new IRStructType(astTypeName.typeNameString);
    }

    public static IRType infoTransfer(BaseType astType) {
        return null;
    }

    public static IRFnType fnTransfer(FuncInfo funcInfo) {
        return null;
    }
}
