package Share.Visitors;

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

public interface ASTVisitor {
    // For every type of derived AST nodes, define a visit interface.
    void visit(ProgramNode it);

    void visit(ClassDeclStmtNode it);

    void visit(VarDeclStmtNode it);

    void visit(FuncDeclrStmtNode it);

    void visit(CreatorExprNode it);

    void visit(BinaryOpExprNode it);

    void visit(UnaryOpExprNode it);

    void visit(AssignExprNode it);

    void visit(SubscExprNode it);

    void visit(FuncCallExprNode it);

    void visit(SelfConstructNode it);

    void visit(ExprStmtNode it);

    void visit(IfStmtNode it);

    void visit(WhileStmtNode it);

    void visit(ForStmtNode it);

    void visit(ReturnStmtNode it);

    void visit(ContinueStmtNode it);

    void visit(BreakStmtNode it);

    void visit(MemberExprNode it);

    void visit(LambdaExprNode it);

    void visit(ThisExprNode it);

    void visit(LiteralExprNode it);

    void visit(SuiteStmtNode it);

    void visit(EmptyStmtNode it);

    void visit(IdentiExprNode it);

    void visit(SingleVarDeclStmtNode it);
}
