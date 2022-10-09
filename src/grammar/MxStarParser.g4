parser grammar MxStarParser;

@header {
package grammar;
}
options {
	tokenVocab = MxStarLexer;
}

program: (funcDeclarStatement | classDeclarStatement | varDeclarStatement)* EOF;

// REGULATION : suite contains the statements
suite: LBrace (statement | suite)* RBrace | statement;

statement: // for side-effects
	expression Semicolon
	| varDeclarStatement
	| funcDeclarStatement
	| classDeclarStatement
	| ifStatement		// If statement
	| whileStatement	// While statement
	| forStatement		// For statement
	| returnStatement	// return;
	| breakStatement	// break;
	| continueStatement	// continue;
	| emptyStatement;

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
	LParen expression RParen											// (self)
	| expression (Inc | Dec)											// suffix ++ --
	| funcCall															// ()
	| expression (LBracket expression RBracket)							// []...
	| expression Dot (Identifier | funcCall)							// a.b.c().d.e()
	| expression Dot LParen (Identifier | funcCall) RParen				// a.(b).(c()).(d)
	| <assoc = right>(Inc | Dec) expression								// prefix ++ --
	| <assoc = right> (Add | Sub) expression							// +a, -b
	| <assoc = right> LogicNot expression								// logical not !
	| <assoc = right> BitNot expression									// bitwise not ~
	| expression (Mul | Div | Mod) expression							// * / %
	| expression (Add | Sub) expression									// + -
	| expression (ShiftLeft | ShiftRight) expression					// << >>
	| expression (Less | LessEqual | Greater | GreaterEqual) expression	// < <= > >=
	| expression (Equal | NotEqual) expression							// == !=
	| expression BitAnd expression										// &
	| expression BitXor expression										// ^
	| expression BitOr expression										// |
	| expression LogicAnd expression									// &&
	| expression LogicOr expression										// ||
	| <assoc = right>expression Assign expression						// assignmnet
	| expression (Comma expression)+									// expr1 , expr2 , expr3 , ...
	// -----------------------------------
	// No conflicts with the previous rules
	| creatorExpression	// special case : create new variables
	| lambdaExpression	// [&] (args) -> type { body }();
	| This				// self-pointer expression
	// -----------------------------------
	| literalExpression	// literal
	| Identifier;		// just a single variable?

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
	New typeNameUnit (LBracket expression RBracket)+ (LBracket RBracket)*
	| New typeNameUnit (LParen RParen)?;
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