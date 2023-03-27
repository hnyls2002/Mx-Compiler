package Middleend;

import AST.Info.FuncInfo;
import AST.Scopes.GlobalScope;
import IR.IRModule;
import IR.IRType.IRFnType;
import IR.IRType.IRIntType;
import IR.IRType.IRPtType;
import IR.IRType.IRType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;

public class BuiltinPreload {
    public static void preload(IRModule topModule, GlobalScope gScope) {
        IRType i1 = new IRIntType(1);
        IRType i8s = new IRPtType(new IRIntType(8), 1);
        IRType i32 = new IRIntType(32);
        IRType vd = new IRVoidType();
        topModule.builtinFnList.add(new IRFn(new IRFnType("__malloc", i8s, i32), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("__str_plus", i8s, i8s, i8s), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("__str_eq", i1, i8s, i8s), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("__str_ne", i1, i8s, i8s), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("__str_lt", i1, i8s, i8s), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("__str_le", i1, i8s, i8s), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("__str_ge", i1, i8s, i8s), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("__str_gt", i1, i8s, i8s), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("__str_length", i32, i8s), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("__str_substring", i8s, i8s, i32, i32), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("__str_parseInt", i32, i8s), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("__str_ord", i32, i8s, i32), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("print", vd, i8s), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("println", vd, i8s), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("printInt", vd, i32), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("printlnInt", vd, i32), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("getString", i8s), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("getInt", i32), true));
        topModule.builtinFnList.add(new IRFn(new IRFnType("toString", i8s, i32), true));
        FuncInfo fnInfo = gScope.funMap.get("print");
        fnInfo.irFn = topModule.builtinFnList.get(12);
        fnInfo = gScope.funMap.get("println");
        fnInfo.irFn = topModule.builtinFnList.get(13);
        fnInfo = gScope.funMap.get("printInt");
        fnInfo.irFn = topModule.builtinFnList.get(14);
        fnInfo = gScope.funMap.get("printlnInt");
        fnInfo.irFn = topModule.builtinFnList.get(15);
        fnInfo = gScope.funMap.get("getString");
        fnInfo.irFn = topModule.builtinFnList.get(16);
        fnInfo = gScope.funMap.get("getInt");
        fnInfo.irFn = topModule.builtinFnList.get(17);
        fnInfo = gScope.funMap.get("toString");
        fnInfo.irFn = topModule.builtinFnList.get(18);

        var stringType = gScope.typeMap.get("string");
        fnInfo = stringType.funMap.get("length");
        fnInfo.irFn = topModule.builtinFnList.get(8);
        fnInfo = stringType.funMap.get("substring");
        fnInfo.irFn = topModule.builtinFnList.get(9);
        fnInfo = stringType.funMap.get("parseInt");
        fnInfo.irFn = topModule.builtinFnList.get(10);
        fnInfo = stringType.funMap.get("ord");
        fnInfo.irFn = topModule.builtinFnList.get(11);
    }
}
