parser grammar MxStarParser;

@header {
package Parser;
}
options {
	tokenVocab = MxStarLexer;
}

program: (
		funcDeclarStatement
		| classDeclarStatement
		| varDeclarStatement
	)* EOF;

// REGULATION 
// suite must have {}
// statements contain suites
// which means suite is a type of statement
suite: LBrace statement* RBrace;

statement: // for side-effects
	suite					# suitStmt
	| expression Semicolon	# exprStmt
	| varDeclarStatement	# varDeclStmt
	| funcDeclarStatement	# funcDeclStmt
	| classDeclarStatement	# classDeclStmt
	| If LParen expression RParen thenStmt = statement (
		Else elseStmt = statement
	)?											# ifStmt
	| While LParen expression RParen statement	# whileStmt
	| For LParen (initExpr = expression | initVar = varDeclar)? Semicolon conditionExpr = expression
		? Semicolon increExpr = expression? RParen statement	# forStmt
	| Return expression? Semicolon								# returnStmt
	| Break Semicolon											# breakStmt
	| Continue Semicolon										# continueStmt
	| Semicolon													# emptyStmt;

// for create a value (with some possible side-effects)
expression:
	LParen expression RParen													# parenExpr
	| expression op = (Inc | Dec)												# sufIncDecExpr
	| funcCall																	# funcCallExpr
	| expression (LBracket expression RBracket)									# subscriptExpr
	| expression Dot (Identifier | funcCall)									# memberExpr
	| expression Dot LParen (Identifier | funcCall) RParen						# memberParenExpr
	| <assoc = right> op = (Inc | Dec) expression								# preIncDecExpr
	| <assoc = right> op = (Add | Sub) expression								# preAddSubExpr
	| <assoc = right> LogicNot expression										# logicalNotExpr
	| <assoc = right> BitNot expression											# bitNotExpr
	| expression op = (Mul | Div | Mod) expression								# mulDivModExpr
	| expression op = (Add | Sub) expression									# addSubExpr
	| expression op = (ShiftLeft | ShiftRight) expression						# shiftExpr
	| expression op = (Less | LessEqual | Greater | GreaterEqual) expression	# compareExpr
	| expression op = (Equal | NotEqual) expression								# equalAndNeqExpr
	| expression BitAnd expression												# bitAndExpr
	| expression BitXor expression												# bitXorExpr
	| expression BitOr expression												# bitOrExpr
	| expression LogicAnd expression											# logicalAndExpr
	| expression LogicOr expression												# logicalOrExpr
	| <assoc = right>expression Assign expression								# assignExpr
	| expression (Comma expression)+											# commaExpr
	// -----------------------------------
	// No conflicts with the previous rules
	| creatorExpression	# creatorExpr
	| lambdaExpression	# lambdaExpr
	| This				# thisExpr
	// -----------------------------------
	| literalExpression	# literalExpr
	| Identifier		# identifierExpr;

literalExpression: (
		IntLiteral
		| StringLiteral
		| True
		| False
		| Null
	);

// ------builtin-types------
// int a; undefined value
// int a = <expression>; cannot be "new int"
// int [] a = new int[10]; not (new int)[10];!!!
// int [][] a = new int[10][];

// ------class-types--------
// A a; means "a = null"
// A a = new A(); FUCK THE GRAMMAR!!! "A a = new A" is ok;
// Then, "A [] a = new A[10]" can conflict with "A [] a = (new A)[10]"
// A []a = new A[10];
// A [][]a = new A[10][];

creatorExpression:
	New typeNameUnit (LBracket expression RBracket)+ (
		LBracket RBracket
	)*									# arrayCreator
	| New typeNameUnit (LParen RParen)?	# singleCreator;
/*
creatorExpression:
	New typeNameUnit (LBracket expression RBracket)+ (LParen RParen)?
	| New typeNameUnit (LBracket expression RBracket)+ (LBracket RBracket)*
	| New typeNameUnit (LParen RParen)?;
	*/

lambdaExpression:
	LambdaStart LParen parameterList RParen RightArrow statement LParen argumentList RParen;

// Variable and array declaration
varDeclarStatement: varDeclar Semicolon;
varDeclar: typeName varSingleDeclar (',' varSingleDeclar)*;
varSingleDeclar: Identifier ('=' expression)?;
typeName: typeNameUnit (LBracket RBracket)*;
typeNameUnit: IntType | BoolType | StringType | Identifier;

// Function declaration:
funcDeclarStatement:
	returnType Identifier LParen parameterList RParen statement;
parameterList: parameter (',' parameter)* |;
parameter: typeName Identifier;
returnType: typeName | Void;

// function call
funcCall: Identifier LParen argumentList RParen;
argumentList: expression (Comma expression)* |;

// Class Declaration
classDeclarStatement: classDeclar Semicolon;
classDeclar:
	Class Identifier LBrace (
		varDeclarStatement
		| selfConstructor
		| funcDeclarStatement
	)* RBrace;
selfConstructor: Identifier LParen RParen statement;