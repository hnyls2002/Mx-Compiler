package MiddleEnd;

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
import IR.Module;

public class IRBuilder implements ASTVisitor {

    public Module IRTopModule;

    public Module buildIR(ASTNode progRoot) {
        progRoot.accept(this);
        return IRTopModule;
    }

    @Override
    public void visit(ProgramNode it) {
        // TODO Auto-generated method stub

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
