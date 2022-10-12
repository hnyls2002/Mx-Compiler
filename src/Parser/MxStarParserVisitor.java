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
	 * Visit a parse tree produced by the {@code suiteWithBrace}
	 * labeled alternative in {@link MxStarParser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuiteWithBrace(MxStarParser.SuiteWithBraceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code suiteWithoutBrace}
	 * labeled alternative in {@link MxStarParser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuiteWithoutBrace(MxStarParser.SuiteWithoutBraceContext ctx);
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
	 * Visit a parse tree produced by {@link MxStarParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(MxStarParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(MxStarParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(MxStarParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(MxStarParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(MxStarParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(MxStarParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#emptyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(MxStarParser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisExpr(MxStarParser.ThisExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subscriptExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptExpr(MxStarParser.SubscriptExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivModExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivModExpr(MxStarParser.MulDivModExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcCallExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCallExpr(MxStarParser.FuncCallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(MxStarParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitXorExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitXorExpr(MxStarParser.BitXorExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberParenExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberParenExpr(MxStarParser.MemberParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalNotExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalNotExpr(MxStarParser.LogicalNotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitNotExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitNotExpr(MxStarParser.BitNotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalAndNeqExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualAndNeqExpr(MxStarParser.EqualAndNeqExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sufIncDecExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSufIncDecExpr(MxStarParser.SufIncDecExprContext ctx);
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
	 * Visit a parse tree produced by the {@code creatorExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreatorExpr(MxStarParser.CreatorExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitAndExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitAndExpr(MxStarParser.BitAndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(MxStarParser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareExpr(MxStarParser.CompareExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(MxStarParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalAndExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpr(MxStarParser.LogicalAndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code preAddSubExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreAddSubExpr(MxStarParser.PreAddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberExpr(MxStarParser.MemberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code shiftExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftExpr(MxStarParser.ShiftExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalOrExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpr(MxStarParser.LogicalOrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitOrExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitOrExpr(MxStarParser.BitOrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code commaExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommaExpr(MxStarParser.CommaExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code preIncDecExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreIncDecExpr(MxStarParser.PreIncDecExprContext ctx);
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
	 * Visit a parse tree produced by the {@code arrayCreator}
	 * labeled alternative in {@link MxStarParser#creatorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreator(MxStarParser.ArrayCreatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code singleCreator}
	 * labeled alternative in {@link MxStarParser#creatorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleCreator(MxStarParser.SingleCreatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#lambdaExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExpression(MxStarParser.LambdaExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxStarParser#varDeclarStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclarStatement(MxStarParser.VarDeclarStatementContext ctx);
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
	 * Visit a parse tree produced by {@link MxStarParser#funcDeclarStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDeclarStatement(MxStarParser.FuncDeclarStatementContext ctx);
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
	 * Visit a parse tree produced by {@link MxStarParser#classDeclarStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclarStatement(MxStarParser.ClassDeclarStatementContext ctx);
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