parser grammar MxStarParser;

@header {
package Parser;
}
options {
	tokenVocab = MxStarLexer;
}

program: (
		funcDeclar
		| classDeclar Semicolon
		| varDeclar Semicolon
	)* EOF;

// REGULATION 
// suite must have {}
// statements contain suites
// which means suite is a type of statement
suite: LBrace statement* RBrace;

statement: // for side-effects
	suite					# suitStmt
	| expression Semicolon	# exprStmt
	| varDeclar Semicolon	# varDeclStmt
	| funcDeclar			# funcDeclStmt
	| classDeclar Semicolon	# classDeclStmt
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
	LParen expression RParen								# parenExpr
	| expression op = (Inc | Dec)							# unary1Expr
	| funcCall												# funcCallExpr
	| expression (LBracket expression RBracket)				# subscriptExpr
	| expression Dot (Identifier | funcCall)				# memberExpr
	| expression Dot LParen (Identifier | funcCall) RParen	# memberParenExpr
	| <assoc = right> op = (
		Inc
		| Dec
		| Add
		| Sub
		| LogicNot
		| BitNot
	) expression																# unary2Expr
	| expression op = (Mul | Div | Mod) expression								# binary1Expr
	| expression op = (Add | Sub) expression									# binary2Expr
	| expression op = (ShiftLeft | ShiftRight) expression						# binary3Expr
	| expression op = (Less | LessEqual | Greater | GreaterEqual) expression	# binary4Expr
	| expression op = (Equal | NotEqual) expression								# binary5Expr
	| expression op = BitAnd expression											# binary6Expr
	| expression op = BitXor expression											# binary7Expr
	| expression op = BitOr expression											# binary8Expr
	| expression op = LogicAnd expression										# binary9Expr
	| expression op = LogicOr expression										# binary10Expr
	| <assoc = right>expression Assign expression								# assignExpr
	| expression (Comma expression)+											# commaExpr
	// -----------------------------------
	// No conflicts with the previous rules
	| New typeNameUnit (LBracket expression RBracket)+ (
		LBracket RBracket
	)*									# arrayCreator
	| New typeNameUnit (LParen RParen)?	# singleCreator
	| lambdaExpression					# lambdaExpr
	| This								# thisExpr
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

/*
creatorExpression:
	New typeNameUnit (LBracket expression RBracket)+ (LParen RParen)?
	| New typeNameUnit (LBracket expression RBracket)+ (LBracket RBracket)*
	| New typeNameUnit (LParen RParen)?;
	*/
lambdaExpression:
	LambdaStart LParen parameterList RParen RightArrow suite LParen argumentList RParen;

// Variable and array declaration
varDeclar: typeName varSingleDeclar (',' varSingleDeclar)*;
varSingleDeclar: Identifier ('=' expression)?;
typeName: typeNameUnit (LBracket RBracket)*;
typeNameUnit: IntType | BoolType | StringType | Identifier;

// Function declaration:
funcDeclar:
	returnType Identifier LParen parameterList RParen suite;
parameterList: parameter (',' parameter)* |;
parameter: typeName Identifier;
returnType: typeName | Void;

// function call
funcCall: Identifier LParen argumentList RParen;
argumentList: expression (Comma expression)* |;

// Class Declaration
classDeclar:
	Class Identifier LBrace (
		varDeclar Semicolon
		| selfConstructor
		| funcDeclar
	)* RBrace;
selfConstructor: Identifier LParen RParen suite;