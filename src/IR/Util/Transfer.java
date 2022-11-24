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
import IR.IRValue.IRUser.ConsValue.ConsData.StrConst;

public class Transfer {
    private static IRType typeTransferNoRef(TypeName astTypeName) {
        switch (astTypeName.typeNameString) {
            case "int" -> {
                return new IRIntType(32);
            }
            case "bool" -> {
                return new IRIntType(8);
            }
            case "string" -> {
                return new IRPtType(new IRIntType(8), 1); // i8*
            }
            case "void" -> {
                return new IRVoidType();
            }
            case "null" -> {
                throw new MyException("No null type should be transfered!");
            }
            default -> {
                return new IRStructType(astTypeName.typeNameString);
            }
        }
    }

    public static IRType typeTransfer(TypeName astTypeName) {
        if (astTypeName.dimen == 0)
            return typeTransferNoRef(astTypeName);
        return new IRPtType(typeTransferNoRef(astTypeName), astTypeName.dimen);
    }

    // just transfer the contains, without \0 -> \00 , without "literalString"
    public static StrConst constStrTranfer(String literalString, int constStrIdx) {
        int len = literalString.length();
        for (int i = 0; i + 1 <= literalString.length() - 1; ++i) {
            var substr = literalString.substring(i, i + 2);
            if (substr.equals("\\\\") || substr.equals("\\\"") || substr.equals("\\n")) {
                ++i;
                --len;
            }
        }
        literalString = literalString.replaceAll("\\\\n", "\\\\0A"); // replace \n to \0A
        literalString = literalString.replaceAll("\\\\\"", "\\\\22"); // replace \" to \22
        return new StrConst(literalString, len, constStrIdx);
    }

    public static IRType infoTransfer(BaseType astType) {
        return null;
    }

    public static IRStructType structTypeTransfer() {
        var ret = new IRStructType(null);
        return ret;
    }

    public static IRFnType fnTypeTransfer(FuncInfo funcInfo) {
        var ret = new IRFnType();
        ret.retType = Transfer.typeTransfer(funcInfo.retType);
        for (var arg : funcInfo.paraList)
            ret.argumentList.add(Transfer.typeTransfer(arg.typeName));
        return ret;
    }

}
