package MiddleEnd;

import java.util.ArrayList;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.ProgramNode;
import AST.Expr.AssignExprNode;
import AST.Expr.BinaryOpExprNode;
import AST.Expr.CreatorExprNode;
import AST.Expr.FuncCallExprNode;
import AST.Expr.IdentiExprNode;
import AST.Expr.LambdaExprNode;
import AST.Expr.LiteralExprNode;
import AST.Expr.MemberExprNode;
import AST.Expr.SubscExprNode;
import AST.Expr.ThisExprNode;
import AST.Expr.UnaryOpExprNode;
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
import Frontend.Util.Scopes.GlobalScope;
import Frontend.Util.Types.FuncInfo;
import IR.Module;
import IR.IRType.IRFnType;
import IR.IRType.IRStructType;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;
import IR.Util.InfoManager;

public class IRBuilder implements ASTVisitor {

    public Module topModule;
    public ASTNode progRoot;
    public GlobalScope gScope;
    public InfoManager infoManager;

    public IRBuilder(ASTNode progRoot, GlobalScope gScope, InfoManager infoManager) {
        this.progRoot = progRoot;
        this.gScope = gScope;
        this.infoManager = infoManager;
    }

    public IRFnType collectFn(FuncInfo funcInfo) {
        var ret = new IRFnType(funcInfo.funcName);
        // TODO collect the type(retType and arguments)
        // Maybe I need a transfer function/class
        return ret;
    }

    public void collectInfo() {
        gScope.typeMap.forEach((name, agg) -> {
            var aClass = new IRStructType(name);
            aClass.isSolid = true;
            aClass.fnList = new ArrayList<>();
            aClass.fieldList = new ArrayList<>();
            aClass.infoManager = infoManager;
            // TODO collect the function
        });
        gScope.DefMap.forEach((name, gVar) -> {
            // TODO
            var globalVar = new GlobalVariable(null, null);
            topModule.globalVarList.add(null);
        });
    }

    public Module buildIR() {
        collectInfo();
        progRoot.accept(this);
        return topModule;
    }

    @Override
    public void visit(ProgramNode it) {
        it.blocks.forEach(v -> v.accept(this));

    }

    @Override
    public void visit(ClassDeclStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(VarDeclStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(FuncDeclrStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(CreatorExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(BinaryOpExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(UnaryOpExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(AssignExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(SubscExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(FuncCallExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(SelfConstructNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ExprStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(IfStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(WhileStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ForStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ReturnStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ContinueStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(BreakStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(MemberExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(LambdaExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ThisExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(LiteralExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(SuiteStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(EmptyStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(IdentiExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(SingleVarDeclStmtNode it) {
        // TODO Auto-generated method stub

    }
}
