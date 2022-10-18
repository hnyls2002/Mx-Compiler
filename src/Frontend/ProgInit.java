package Frontend;

import java.util.HashMap;

import AST.ASTVisitor;
import AST.ProgramNode;
import AST.Expr.AssignExprNode;
import AST.Expr.BinaryOpExprNode;
import AST.Expr.CommaExprNode;
import AST.Expr.CreatorExprNode;
import AST.Expr.FuncCallExprNode;
import AST.Expr.IdentiExprNode;
import AST.Expr.LambdaExprNode;
import AST.Expr.LiteralExprNode;
import AST.Expr.MemberExprNode;
import AST.Expr.SubscExprNode;
import AST.Expr.ThisExprNode;
import AST.Expr.UnaryOpExprNode;
import AST.Expr.VarExprNode;
import AST.Stmt.BreakStmtNode;
import AST.Stmt.ClassDeclStmtNode;
import AST.Stmt.ContinueStmtNode;
import AST.Stmt.EmptyStmtNode;
import AST.Stmt.ExprStmtNode;
import AST.Stmt.ForStmtNode;
import AST.Stmt.FuncDeclrStmtNode;
import AST.Stmt.IfStmtNode;
import AST.Stmt.ReturnStmtNode;
import AST.Stmt.SelfConstructNode;
import AST.Stmt.SingleVarDeclStmtNode;
import AST.Stmt.SuiteStmtNode;
import AST.Stmt.VarDeclStmtNode;
import AST.Stmt.WhileStmtNode;
import Util.Position;
import Util.TypeIdPair;
import Util.MxStarErrors.SemanticError;
import Util.Scopes.GlobalScope;
import Util.Types.BuiltinType;
import Util.Types.ClassType;
import Util.Types.FuncInfo;

public class ProgInit implements ASTVisitor {
    private GlobalScope gScope;
    private boolean inClass = false;
    private HashMap<String, FuncInfo> funcCollector = null;

    private void builtinTypeInit() {
        BuiltinType intType = new BuiltinType("int");
        BuiltinType stringType = new BuiltinType("string");
        BuiltinType boolType = new BuiltinType("bool");
        BuiltinType nullType = new BuiltinType("null");
        BuiltinType voidType = new BuiltinType("void");

        FuncInfo length = new FuncInfo(gScope.intName, "length", new Position(0, 0));
        stringType.funMap.put("length", length);

        FuncInfo substring = new FuncInfo(gScope.stringName, "substring", new Position(0, 0));
        substring.paraList.add(new TypeIdPair(gScope.intName, "left"));
        substring.paraList.add(new TypeIdPair(gScope.intName, "right"));
        stringType.funMap.put("substring", substring);

        FuncInfo parseInt = new FuncInfo(gScope.intName, "parseInt", new Position(0, 0));
        stringType.funMap.put("parseInt", parseInt);

        FuncInfo ord = new FuncInfo(gScope.intName, "ord", new Position(0, 0));
        ord.paraList.add(new TypeIdPair(gScope.intName, "pos"));
        stringType.funMap.put("ord", ord);

        gScope.putType(intType);
        gScope.putType(stringType);
        gScope.putType(boolType);
        gScope.putType(nullType);
        gScope.putType(voidType);

    }

    public void builtinFuncInit() {
        FuncInfo _print = new FuncInfo(gScope.voidName, "print", new Position(0, 0));
        _print.paraList.add(new TypeIdPair(gScope.stringName, "str"));

        FuncInfo _println = new FuncInfo(gScope.voidName, "println", new Position(0, 0));
        _println.paraList.add(new TypeIdPair(gScope.stringName, "str"));

        FuncInfo _printInt = new FuncInfo(gScope.voidName, "printInt", new Position(0, 0));
        _printInt.paraList.add(new TypeIdPair(gScope.intName, "n"));

        FuncInfo _printlnInt = new FuncInfo(gScope.voidName, "printlnInt", new Position(0, 0));
        _printlnInt.paraList.add(new TypeIdPair(gScope.intName, "n"));

        FuncInfo _getString = new FuncInfo(gScope.stringName, "getString", new Position(0, 0));

        FuncInfo _getInt = new FuncInfo(gScope.intName, "getInt", new Position(0, 0));

        FuncInfo _toString = new FuncInfo(gScope.stringName, "toString", new Position(0, 0));
        _toString.paraList.add(new TypeIdPair(gScope.intName, "i"));

        gScope.putFunc(_print);
        gScope.putFunc(_println);
        gScope.putFunc(_printInt);
        gScope.putFunc(_printlnInt);
        gScope.putFunc(_getString);
        gScope.putFunc(_getInt);
        gScope.putFunc(_toString);
    }

    public ProgInit(GlobalScope gScope) {
        this.gScope = gScope;
        builtinTypeInit();
        builtinFuncInit();
    }

    @Override
    public void visit(ProgramNode it) {
        it.classList.forEach(v -> visit(v));
        it.funcList.forEach(v -> visit(v));
    }

    @Override
    public void visit(ClassDeclStmtNode it) {
        ClassType classType = new ClassType(it.classNameString, it.pos);

        if (it.constructor != null) {
            if (!it.constructor.consNameString.equals(it.classNameString)) {
                throw new SemanticError(
                        "Constructor must have the same name with class name : " + it.classNameString + " ",
                        it.constructor.pos);
            }
            classType.haveConst = true;
        } else
            classType.haveConst = false;

        // adding fields
        for (var declList : it.varDeclList)
            for (var decl : declList.varList)
                classType.putDef(decl.decl);

        inClass = true;
        funcCollector = classType.funMap;
        it.funcDeclList.forEach(v -> visit(v));
        inClass = false;
        funcCollector = null;

        gScope.putType(classType);
    }

    @Override
    public void visit(FuncDeclrStmtNode it) {
        FuncInfo funcInfo = new FuncInfo(it.retType, it.funcNameString, it.pos);
        for (var para : it.paraList)
            funcInfo.paraList.add(para);

        if (inClass) {
            if (funcCollector.containsKey(funcInfo.funcName))
                throw new SemanticError("Redefinition of function " + funcInfo.funcName + " in class", funcInfo.pos);
            funcCollector.put(it.funcNameString, funcInfo);
        } else
            gScope.putFunc(funcInfo);
    }

    @Override
    public void visit(SingleVarDeclStmtNode it) {
    }

    @Override
    public void visit(VarDeclStmtNode it) {
    }

    @Override
    public void visit(CreatorExprNode it) {

    }

    @Override
    public void visit(BinaryOpExprNode it) {

    }

    @Override
    public void visit(UnaryOpExprNode it) {

    }

    @Override
    public void visit(AssignExprNode it) {

    }

    @Override
    public void visit(CommaExprNode it) {

    }

    @Override
    public void visit(SubscExprNode it) {

    }

    @Override
    public void visit(FuncCallExprNode it) {

    }

    @Override
    public void visit(SelfConstructNode it) {

    }

    @Override
    public void visit(ExprStmtNode it) {

    }

    @Override
    public void visit(IfStmtNode it) {

    }

    @Override
    public void visit(WhileStmtNode it) {

    }

    @Override
    public void visit(ForStmtNode it) {

    }

    @Override
    public void visit(ReturnStmtNode it) {

    }

    @Override
    public void visit(ContinueStmtNode it) {

    }

    @Override
    public void visit(BreakStmtNode it) {

    }

    @Override
    public void visit(MemberExprNode it) {

    }

    @Override
    public void visit(LambdaExprNode it) {

    }

    @Override
    public void visit(ThisExprNode it) {

    }

    @Override
    public void visit(LiteralExprNode it) {
    }

    @Override
    public void visit(VarExprNode it) {
    }

    @Override
    public void visit(SuiteStmtNode it) {
    }

    @Override
    public void visit(EmptyStmtNode it) {
    }

    @Override
    public void visit(IdentiExprNode it) {
    }

}
