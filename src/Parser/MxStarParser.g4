parser grammar MxStarParser;

@header {
package Parser;
}
options {
	tokenVocab = MxStarLexer;
}

program: (funcDeclarStatement | classDeclarStatement | varDeclarStatement)* EOF;

// REGULATION : suite contains the statements
suite: LBrace (statement | suite)* RBrace # suiteWithBrace | statement # suiteWithoutBrace;

statement: // for side-effects
	expression Semicolon	# exprStmt
	| varDeclarStatement	# varDeclStmt
	| funcDeclarStatement	# funcDeclStmt
	| classDeclarStatement	# classDeclStmt
	| ifStatement			# ifStmt
	| whileStatement		# whileStmt
	| forStatement			# forStmt
	| returnStatement		# returnStmt
	| breakStatement		# breakStmt
	| continueStatement		# continueStmt
	| emptyStatement		# emptyStmt;

ifStatement: If LParen expression RParen suite (Else suite)?;
whileStatement: While LParen expression RParen suite;
forStatement:
	For LParen (expression | varDeclar)? Semicolon expression? Semicolon expression? RParen suite;
returnStatement: Return expression? Semicolon;
breakStatement: Break Semicolon;
continueStatement: Continue Semicolon;
emptyStatement: Semicolon;

// for create a value (with some possible side-effects)
expression:
	LParen expression RParen											# parenExpr
	| expression (Inc | Dec)											# sufIncDecExpr
	| funcCall															# funcCallExpr
	| expression (LBracket expression RBracket)							# subscriptExpr
	| expression Dot (Identifier | funcCall)							# memberExpr
	| expression Dot LParen (Identifier | funcCall) RParen				# memberParenExpr
	| <assoc = right>(Inc | Dec) expression								# preIncDecExpr
	| <assoc = right> (Add | Sub) expression							# preAddSubExpr
	| <assoc = right> LogicNot expression								# logicalNotExpr
	| <assoc = right> BitNot expression									# bitNotExpr
	| expression (Mul | Div | Mod) expression							# mulDivModExpr
	| expression (Add | Sub) expression									# addSubExpr
	| expression (ShiftLeft | ShiftRight) expression					# shiftExpr
	| expression (Less | LessEqual | Greater | GreaterEqual) expression	# compareExpr
	| expression (Equal | NotEqual) expression							# equalAndNeqExpr
	| expression BitAnd expression										# bitAndExpr
	| expression BitXor expression										# bitXorExpr
	| expression BitOr expression										# bitOrExpr
	| expression LogicAnd expression									# logicalAndExpr
	| expression LogicOr expression										# logicalOrExpr
	| <assoc = right>expression Assign expression						# assignExpr
	| expression (Comma expression)+									# commaExpr
	// -----------------------------------
	// No conflicts with the previous rules
	| creatorExpression	# creatorExpr
	| lambdaExpression	# lambdaExpr
	| This				# thisExpr
	// -----------------------------------
	| literalExpression	# literalExpr
	| Identifier		# identifierExpr;

literalExpression: (IntLiteral | StringLiteral | True | False | Null);

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
	New typeNameUnit (LBracket expression RBracket)+ (LBracket RBracket)*	# arrayCreator
	| New typeNameUnit (LParen RParen)?										# singleCreator;
/*
creatorExpression:
	New typeNameUnit (LBracket expression RBracket)+ (LParen RParen)?
	| New typeNameUnit (LBracket expression RBracket)+ (LBracket RBracket)*
	| New typeNameUnit (LParen RParen)?;
	*/

lambdaExpression:
	LambdaStart LParen parameterList RParen RightArrow suite LParen argumentList RParen;

// Variable and array declaration
varDeclarStatement: varDeclar Semicolon;
varDeclar: typeName varSingleDeclar (',' varSingleDeclar)*;
varSingleDeclar: Identifier ('=' expression)?;
typeName: typeNameUnit (LBracket RBracket)*;
typeNameUnit: IntType | BoolType | StringType | Identifier;

// Function declaration:
funcDeclarStatement: returnType Identifier LParen parameterList RParen suite;
parameterList: parameter (',' parameter)* |;
parameter: typeName Identifier;
returnType: typeName | Void;

// function call
funcCall: Identifier LParen argumentList RParen;
argumentList: expression (Comma expression)* |;

// Class Declaration
classDeclarStatement: classDeclar Semicolon;
classDeclar:
	Class Identifier LBrace (varDeclarStatement | selfConstructor | funcDeclarStatement)* RBrace;
selfConstructor: Identifier LParen RParen suite;