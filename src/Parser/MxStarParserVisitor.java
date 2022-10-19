// Generated from MxStarParser.g4 by ANTLR 4.7.1

package Parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxStarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxStarParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxStarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxStarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuite(MxStarParser.SuiteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code suitStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuitStmt(MxStarParser.SuitStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmt(MxStarParser.ExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDeclStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclStmt(MxStarParser.VarDeclStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcDeclStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDeclStmt(MxStarParser.FuncDeclStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classDeclStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclStmt(MxStarParser.ClassDeclStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(MxStarParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(MxStarParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(MxStarParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(MxStarParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(MxStarParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(MxStarParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStmt(MxStarParser.EmptyStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisExpr(MxStarParser.ThisExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code singleCreator}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleCreator(MxStarParser.SingleCreatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subscriptExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptExpr(MxStarParser.SubscriptExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binary9Expr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary9Expr(MxStarParser.Binary9ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcCallExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCallExpr(MxStarParser.FuncCallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binary4Expr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary4Expr(MxStarParser.Binary4ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(MxStarParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberParenExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberParenExpr(MxStarParser.MemberParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unary2Expr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary2Expr(MxStarParser.Unary2ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lambdaExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExpr(MxStarParser.LambdaExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpr(MxStarParser.LiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binary5Expr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary5Expr(MxStarParser.Binary5ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binary1Expr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary1Expr(MxStarParser.Binary1ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(MxStarParser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binary7Expr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary7Expr(MxStarParser.Binary7ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberExpr(MxStarParser.MemberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unary1Expr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary1Expr(MxStarParser.Unary1ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binary6Expr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary6Expr(MxStarParser.Binary6ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayCreator}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreator(MxStarParser.ArrayCreatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binary2Expr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary2Expr(MxStarParser.Binary2ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binary8Expr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary8Expr(MxStarParser.Binary8ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binary3Expr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary3Expr(MxStarParser.Binary3ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code commaExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommaExpr(MxStarParser.CommaExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binary10Expr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary10Expr(MxStarParser.Binary10ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpr(MxStarParser.IdentifierExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#literalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpression(MxStarParser.LiteralExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#lambdaExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExpression(MxStarParser.LambdaExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#varDeclar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclar(MxStarParser.VarDeclarContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#varSingleDeclar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarSingleDeclar(MxStarParser.VarSingleDeclarContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(MxStarParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#typeNameUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeNameUnit(MxStarParser.TypeNameUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#funcDeclar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDeclar(MxStarParser.FuncDeclarContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(MxStarParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(MxStarParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(MxStarParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#funcCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall(MxStarParser.FuncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(MxStarParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#classDeclar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclar(MxStarParser.ClassDeclarContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#selfConstructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfConstructor(MxStarParser.SelfConstructorContext ctx);
}