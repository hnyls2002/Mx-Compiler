package Middleend;

import AST.Info.FuncInfo;
import AST.Scopes.GlobalScope;
import IR.IRModule;
import IR.IRType.IRFnType;
import IR.IRType.IRIntType;
import IR.IRType.IRPtType;
import IR.IRType.IRType;
import IR.IRType.IRVoidType;

public class BuiltinPreload {
    public static void preload(IRModule topModule, GlobalScope gScope) {
        IRType i1 = new IRIntType(1);
        IRType i8s = new IRPtType(new IRIntType(8), 1);
        IRType i32 = new IRIntType(32);
        IRType vd = new IRVoidType();
        topModule.builtinFnList.add(new IRFnType("__malloc", i8s, i32));
        topModule.builtinFnList.add(new IRFnType("__str_plus", i8s, i8s, i8s));
        topModule.builtinFnList.add(new IRFnType("__str_eq", i1, i8s, i8s));
        topModule.builtinFnList.add(new IRFnType("__str_ne", i1, i8s, i8s));
        topModule.builtinFnList.add(new IRFnType("__str_lt", i1, i8s, i8s));
        topModule.builtinFnList.add(new IRFnType("__str_le", i1, i8s, i8s));
        topModule.builtinFnList.add(new IRFnType("__str_ge", i1, i8s, i8s));
        topModule.builtinFnList.add(new IRFnType("__str_gt", i1, i8s, i8s));
        topModule.builtinFnList.add(new IRFnType("__str_length", i32, i8s));
        topModule.builtinFnList.add(new IRFnType("__str_substring", i8s, i8s, i32, i32));
        topModule.builtinFnList.add(new IRFnType("__str_parseInt", i32, i8s));
        topModule.builtinFnList.add(new IRFnType("__str_ord", i32, i8s, i32));
        topModule.builtinFnList.add(new IRFnType("print", vd, i8s));
        topModule.builtinFnList.add(new IRFnType("println", vd, i8s));
        topModule.builtinFnList.add(new IRFnType("printInt", vd, i32));
        topModule.builtinFnList.add(new IRFnType("printlnInt", vd, i32));
        topModule.builtinFnList.add(new IRFnType("getString", i8s));
        topModule.builtinFnList.add(new IRFnType("getInt", i32));
        topModule.builtinFnList.add(new IRFnType("toString", i8s, i32));
        FuncInfo fnInfo = gScope.funMap.get("print");
        fnInfo.fnType = (IRFnType) topModule.builtinFnList.get(12);
        fnInfo = gScope.funMap.get("println");
        fnInfo.fnType = (IRFnType) topModule.builtinFnList.get(13);
        fnInfo = gScope.funMap.get("printInt");
        fnInfo.fnType = (IRFnType) topModule.builtinFnList.get(14);
        fnInfo = gScope.funMap.get("printlnInt");
        fnInfo.fnType = (IRFnType) topModule.builtinFnList.get(15);
        fnInfo = gScope.funMap.get("getString");
        fnInfo.fnType = (IRFnType) topModule.builtinFnList.get(16);
        fnInfo = gScope.funMap.get("getInt");
        fnInfo.fnType = (IRFnType) topModule.builtinFnList.get(17);
        fnInfo = gScope.funMap.get("toString");
        fnInfo.fnType = (IRFnType) topModule.builtinFnList.get(18);

        var stringType = gScope.typeMap.get("string");
        fnInfo = stringType.funMap.get("length");
        fnInfo.fnType = (IRFnType) topModule.builtinFnList.get(8);
        fnInfo = stringType.funMap.get("substring");
        fnInfo.fnType = (IRFnType) topModule.builtinFnList.get(9);
        fnInfo = stringType.funMap.get("parseInt");
        fnInfo.fnType = (IRFnType) topModule.builtinFnList.get(10);
        fnInfo = stringType.funMap.get("ord");
        fnInfo.fnType = (IRFnType) topModule.builtinFnList.get(11);
    }
}
