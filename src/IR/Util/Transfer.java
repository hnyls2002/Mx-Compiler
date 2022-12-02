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
import IR.IRValue.IRUser.Inst.BinaryInst.binaryOperator;
import IR.IRValue.IRUser.Inst.IcmpInst.icmpOperator;
import Share.MyException;

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
            // 2. stored the index in sructType
            ret.fieldIdxMap.put(memVarString, ret.fieldTypeList.size() - 1);
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

    public static binaryOperator binaryArthTransfer(binaryOp astOpCode) {
        binaryOperator binaryOpCode = switch (astOpCode) {
            case ADD -> binaryOperator.irADD;
            case SUB -> binaryOperator.irSUB;
            case BIT_AND -> binaryOperator.irAND;
            case BIT_OR -> binaryOperator.irOR;
            case DIV -> binaryOperator.irSDIV;
            case MOD -> binaryOperator.irSREM;
            case MUL -> binaryOperator.irMUL;
            case SHIFT_LEFT -> binaryOperator.irSHL;
            case SHIFT_RIGHT -> binaryOperator.irASHR;
            case BIT_XOR -> binaryOperator.irXOR;
            default -> null;
        };
        return binaryOpCode;
    }

    public static icmpOperator binaryCmpTransfer(binaryOp astOpCode) {
        icmpOperator icmpOpCode = switch (astOpCode) {
            case EQUAL -> icmpOperator.irEQ;
            case NOT_EQUAL -> icmpOperator.irNE;
            case GREATER -> icmpOperator.irSGT;
            case GREATER_EQUAL -> icmpOperator.irSGE;
            case LESS -> icmpOperator.irSLT;
            case LESS_EQUAL -> icmpOperator.irSLE;
            default -> null;
        };
        return icmpOpCode;
    }

    public static IRFnType constructorTransfer(IRStructType structType) {
        return new IRFnType(structType.className + ".__cons", new IRPtType(structType, 1), new IRPtType(structType, 1));
    }
}
