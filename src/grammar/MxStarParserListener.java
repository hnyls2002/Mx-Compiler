// Generated from MxStarParser.g4 by ANTLR 4.7.1

package grammar;

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
	 * Enter a parse tree produced by {@link MxStarParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(MxStarParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(MxStarParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MxStarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MxStarParser.StatementContext ctx);
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
	 * Enter a parse tree produced by {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MxStarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MxStarParser.ExpressionContext ctx);
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
	 * Enter a parse tree produced by {@link MxStarParser#creatorExpression}.
	 * @param ctx the parse tree
	 */
	void enterCreatorExpression(MxStarParser.CreatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxStarParser#creatorExpression}.
	 * @param ctx the parse tree
	 */
	void exitCreatorExpression(MxStarParser.CreatorExpressionContext ctx);
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