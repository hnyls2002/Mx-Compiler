// Generated from MxStarParser.g4 by ANTLR 4.7.1

package Parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxStarParser}.
 */
public interface MxStarParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxStarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxStarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxStarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suiteWithBrace}
	 * labeled alternative in {@link MxStarParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuiteWithBrace(MxStarParser.SuiteWithBraceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suiteWithBrace}
	 * labeled alternative in {@link MxStarParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuiteWithBrace(MxStarParser.SuiteWithBraceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suiteWithoutBrace}
	 * labeled alternative in {@link MxStarParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuiteWithoutBrace(MxStarParser.SuiteWithoutBraceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suiteWithoutBrace}
	 * labeled alternative in {@link MxStarParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuiteWithoutBrace(MxStarParser.SuiteWithoutBraceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(MxStarParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(MxStarParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDeclStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclStmt(MxStarParser.VarDeclStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDeclStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclStmt(MxStarParser.VarDeclStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcDeclStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterFuncDeclStmt(MxStarParser.FuncDeclStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcDeclStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitFuncDeclStmt(MxStarParser.FuncDeclStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classDeclStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclStmt(MxStarParser.ClassDeclStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classDeclStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclStmt(MxStarParser.ClassDeclStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MxStarParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MxStarParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(MxStarParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(MxStarParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(MxStarParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(MxStarParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(MxStarParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(MxStarParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(MxStarParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(MxStarParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(MxStarParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(MxStarParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStmt(MxStarParser.EmptyStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStmt(MxStarParser.EmptyStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(MxStarParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(MxStarParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MxStarParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MxStarParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(MxStarParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(MxStarParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MxStarParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MxStarParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(MxStarParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(MxStarParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(MxStarParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(MxStarParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(MxStarParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(MxStarParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterThisExpr(MxStarParser.ThisExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitThisExpr(MxStarParser.ThisExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscriptExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptExpr(MxStarParser.SubscriptExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscriptExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptExpr(MxStarParser.SubscriptExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivModExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivModExpr(MxStarParser.MulDivModExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivModExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivModExpr(MxStarParser.MulDivModExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcCallExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFuncCallExpr(MxStarParser.FuncCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcCallExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFuncCallExpr(MxStarParser.FuncCallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(MxStarParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(MxStarParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitXorExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitXorExpr(MxStarParser.BitXorExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitXorExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitXorExpr(MxStarParser.BitXorExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberParenExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMemberParenExpr(MxStarParser.MemberParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberParenExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMemberParenExpr(MxStarParser.MemberParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalNotExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalNotExpr(MxStarParser.LogicalNotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalNotExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalNotExpr(MxStarParser.LogicalNotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitNotExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitNotExpr(MxStarParser.BitNotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitNotExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitNotExpr(MxStarParser.BitNotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalAndNeqExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualAndNeqExpr(MxStarParser.EqualAndNeqExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalAndNeqExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualAndNeqExpr(MxStarParser.EqualAndNeqExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sufIncDecExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSufIncDecExpr(MxStarParser.SufIncDecExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sufIncDecExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSufIncDecExpr(MxStarParser.SufIncDecExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lambdaExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpr(MxStarParser.LambdaExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lambdaExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpr(MxStarParser.LambdaExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpr(MxStarParser.LiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpr(MxStarParser.LiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code creatorExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCreatorExpr(MxStarParser.CreatorExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code creatorExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCreatorExpr(MxStarParser.CreatorExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitAndExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitAndExpr(MxStarParser.BitAndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitAndExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitAndExpr(MxStarParser.BitAndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(MxStarParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(MxStarParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compareExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompareExpr(MxStarParser.CompareExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compareExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompareExpr(MxStarParser.CompareExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(MxStarParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(MxStarParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalAndExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpr(MxStarParser.LogicalAndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalAndExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpr(MxStarParser.LogicalAndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code preAddSubExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPreAddSubExpr(MxStarParser.PreAddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code preAddSubExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPreAddSubExpr(MxStarParser.PreAddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMemberExpr(MxStarParser.MemberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMemberExpr(MxStarParser.MemberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code shiftExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpr(MxStarParser.ShiftExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code shiftExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpr(MxStarParser.ShiftExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOrExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpr(MxStarParser.LogicalOrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOrExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpr(MxStarParser.LogicalOrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitOrExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitOrExpr(MxStarParser.BitOrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitOrExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitOrExpr(MxStarParser.BitOrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code commaExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCommaExpr(MxStarParser.CommaExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code commaExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCommaExpr(MxStarParser.CommaExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code preIncDecExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPreIncDecExpr(MxStarParser.PreIncDecExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code preIncDecExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPreIncDecExpr(MxStarParser.PreIncDecExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpr(MxStarParser.IdentifierExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpr}
	 * labeled alternative in {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpr(MxStarParser.IdentifierExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#literalExpression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpression(MxStarParser.LiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#literalExpression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpression(MxStarParser.LiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayCreator}
	 * labeled alternative in {@link MxStarParser#creatorExpression}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreator(MxStarParser.ArrayCreatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayCreator}
	 * labeled alternative in {@link MxStarParser#creatorExpression}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreator(MxStarParser.ArrayCreatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleCreator}
	 * labeled alternative in {@link MxStarParser#creatorExpression}.
	 * @param ctx the parse tree
	 */
	void enterSingleCreator(MxStarParser.SingleCreatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleCreator}
	 * labeled alternative in {@link MxStarParser#creatorExpression}.
	 * @param ctx the parse tree
	 */
	void exitSingleCreator(MxStarParser.SingleCreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpression(MxStarParser.LambdaExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpression(MxStarParser.LambdaExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#varDeclarStatement}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclarStatement(MxStarParser.VarDeclarStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#varDeclarStatement}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclarStatement(MxStarParser.VarDeclarStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#varDeclar}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclar(MxStarParser.VarDeclarContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#varDeclar}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclar(MxStarParser.VarDeclarContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#varSingleDeclar}.
	 * @param ctx the parse tree
	 */
	void enterVarSingleDeclar(MxStarParser.VarSingleDeclarContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#varSingleDeclar}.
	 * @param ctx the parse tree
	 */
	void exitVarSingleDeclar(MxStarParser.VarSingleDeclarContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(MxStarParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(MxStarParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#typeNameUnit}.
	 * @param ctx the parse tree
	 */
	void enterTypeNameUnit(MxStarParser.TypeNameUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#typeNameUnit}.
	 * @param ctx the parse tree
	 */
	void exitTypeNameUnit(MxStarParser.TypeNameUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#funcDeclarStatement}.
	 * @param ctx the parse tree
	 */
	void enterFuncDeclarStatement(MxStarParser.FuncDeclarStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#funcDeclarStatement}.
	 * @param ctx the parse tree
	 */
	void exitFuncDeclarStatement(MxStarParser.FuncDeclarStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(MxStarParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(MxStarParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(MxStarParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(MxStarParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(MxStarParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(MxStarParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(MxStarParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(MxStarParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(MxStarParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(MxStarParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#classDeclarStatement}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclarStatement(MxStarParser.ClassDeclarStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#classDeclarStatement}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclarStatement(MxStarParser.ClassDeclarStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#classDeclar}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclar(MxStarParser.ClassDeclarContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#classDeclar}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclar(MxStarParser.ClassDeclarContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#selfConstructor}.
	 * @param ctx the parse tree
	 */
	void enterSelfConstructor(MxStarParser.SelfConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#selfConstructor}.
	 * @param ctx the parse tree
	 */
	void exitSelfConstructor(MxStarParser.SelfConstructorContext ctx);
}