package IR.Util;

import AST.Expr.BinaryOpExprNode.binaryOp;
import AST.Info.ClassType;
import AST.Info.FuncInfo;
import AST.Util.TypeName;
import IR.IRType.IRType;
import IR.IRType.IRFnType;
import IR.IRType.IRIntType;
import IR.IRType.IRPtType;
import IR.IRType.IRStructType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.StrConst;
import Share.MyException;
import Share.Lang.LLVMIR;
import Share.Lang.LLVMIR.BOP;
import Share.Lang.LLVMIR.ICMPOP;

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
                return new IRPtType(new IRStructType(astTypeName.typeNameString), 1);
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

    public static IRStructType structTypeTransfer(ClassType classType) {
        var ret = new IRStructType(classType.typeNameString);
        classType.varMap.forEach((memVarString, memVar) -> {
            // 1. store the var-def in structType
            ret.fieldTypeList.add(typeTransfer(memVar.typeName));

            // ----------------------------USELESS------------------------------------
            // 2. stored the index in sructType
            // ret.fieldIdxMap.put(memVarString, ret.fieldTypeList.size() - 1);
            // ----------------------------USELESS------------------------------------

            // 3. store the varValue(which is index) into classType's varMap
            memVar.varValue = new IntConst(ret.fieldTypeList.size() - 1, 32);
        });
        ret.isSolid = true;

        return ret;
    }

    public static IRFnType fnTypeTransfer(FuncInfo funcInfo) {
        var ret = new IRFnType(funcInfo.funcName);
        ret.retType = Transfer.typeTransfer(funcInfo.retType);
        if (funcInfo.inWhichClass != null) { // set the first parameter to be struct type
            ret.methodFrom = funcInfo.inWhichClass.structType;
            ret.paraTypeList.add(ret.methodFrom);
            ret.fnNameString = "struct." + funcInfo.inWhichClass.typeNameString + '.' + funcInfo.funcName;
        }
        for (var para : funcInfo.paraList)
            ret.paraTypeList.add(Transfer.typeTransfer(para.typeName));
        return ret;
    }

    public static LLVMIR.BOP binaryArthTransfer(binaryOp astOpCode) {
        LLVMIR.BOP binaryOpCode = switch (astOpCode) {
            case ADD -> BOP.add;
            case SUB -> BOP.sub;
            case BIT_AND -> BOP.and;
            case BIT_OR -> BOP.or;
            case DIV -> BOP.sdiv;
            case MOD -> BOP.srem;
            case MUL -> BOP.mul;
            case SHIFT_LEFT -> BOP.shl;
            case SHIFT_RIGHT -> BOP.ashr;
            case BIT_XOR -> BOP.xor;
            default -> null;
        };
        return binaryOpCode;
    }

    public static ICMPOP binaryCmpTransfer(binaryOp astOpCode) {
        ICMPOP icmpOpCode = switch (astOpCode) {
            case EQUAL -> ICMPOP.eq;
            case NOT_EQUAL -> ICMPOP.ne;
            case GREATER -> ICMPOP.sgt;
            case GREATER_EQUAL -> ICMPOP.sge;
            case LESS -> ICMPOP.slt;
            case LESS_EQUAL -> ICMPOP.sle;
            default -> null;
        };
        return icmpOpCode;
    }

    public static IRFnType constructorTransfer(IRStructType structType) {
        return new IRFnType(structType.className + ".__cons", new IRPtType(structType, 1), new IRPtType(structType, 1));
    }
}
