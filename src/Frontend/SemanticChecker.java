package Frontend;

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
import Util.MxStarErrors.SemanticError;
import Util.Scopes.GlobalScope;
import Util.Scopes.Scope;

public class SemanticChecker implements ASTVisitor {

    public GlobalScope gScope = null;

    public Scope curScope = null;

    public SemanticChecker(GlobalScope gScope) {
        this.gScope = gScope;
    }

    @Override
    public void visit(ProgramNode it) {
        curScope = gScope;

        // check the main function
        var mainFun = gScope.getFuncInfo("main", null);
        System.err.println(mainFun.retType.toString());
        if (!mainFun.retType.typeNameString.equals("int"))
            throw new SemanticError("main function can only be int type", mainFun.pos);
        if (mainFun.retType.dimen != 0)
            throw new SemanticError("main function cannot return an array", mainFun.pos);

        it.blocks.forEach(v -> {
            if (v instanceof ClassDeclStmtNode nv)
                visit(nv);
            else if (v instanceof FuncDeclrStmtNode nv)
                visit(nv);
            else if (v instanceof VarDeclStmtNode nv)
                visit(nv);

        });

    }

    @Override
    public void visit(ClassDeclStmtNode it) {
        if (it.constructor != null) {
            for (var stmt : it.constructor.body.StmtList)
                if (stmt instanceof ReturnStmtNode)
                    throw new SemanticError("Cannot have return statement in a constructor ", stmt.pos);
        }
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
    public void visit(CommaExprNode it) {
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
    public void visit(VarExprNode it) {
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
