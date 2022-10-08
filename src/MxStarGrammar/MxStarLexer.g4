lexer grammar MxStarLexer;

// Standard Operators
Add: '+';
Sub: '-';
Mul: '*';
Div: '/';
Mod: '%';

// Relational Operators
Greater: '>';
Less: '<';
Equal: '==';
GreaterEqual: '>=';
LessEqual: '<=';
NotEqual: '!=';

// Logical Operators
LogicAnd: '&&';
LogicOr: '||';
LogicNot: '!';

// Bitwise Operators
ShiftRight: '>>';
ShiftLeft: '<<';
BitAnd: '&';
BitOr: '|';
BitNot: '~';
BitXor: '^';

// Assignment Operators
Assign: '=';

// Self Operators
Inc: '++';	// self increment
Dec: '--';	// self decrement

// Dot Operator
Dot: '.';

// Subscript Operator
LBracket: '[';
RBracket: ']';

// Parentheses
LParen: '(';
RParen: ')';

// Separator
Comma: ',';
Semicolon: ';';
LBrace: '{';
RBrace: '}';

// Other Lexemes
DoubleSlash: '//';
SlashStar: '/*';
StarSlash: '*/';
SingleQuote: '\'';
DoubleQuote: '"';
LambdaStart: '[&]';
RightArrow: '->';

// keywords
IntType: 'int';
BoolType: 'bool';
StringType: 'string';
Null: 'null';
Void: 'void';
True: 'true';
False: 'false';
If: 'if';
Else: 'else';
For: 'for';
While: 'while';
Break: 'break';
Continue: 'continue';
Return: 'return';
New: 'new';
Class: 'class';
This: 'this';

// Literals
IntLiteral: '0' | [1-9] [0-9]*; // No negative number in testcases.
// Regex non-greedy match : gobble \n \\ \" first
// and then no " behind, so keep gobbling until " shows up.
StringLiteral: DoubleQuote (Escape | .)*? DoubleQuote;

// fragment : not used in parser
fragment Digit: [0-9];
fragment Letter: [a-zA-Z];
fragment Underline: '_';
fragment Printable: [\u0020-\u007E];
fragment Escape: ('\n' | '\\\\' | '\\"');
fragment NewLine: '\r'? '\n';
// Maybe useless, as literals with '\' 'n' can be regarded as printable.

// Identifier
Identifier: Letter (Letter | Digit | Underline)*;

LineComment: DoubleSlash .*? (NewLine | EOF)	-> skip;
BlockComment: SlashStar .*? StarSlash			-> skip;
WhiteSpace: [ \t\r\n]+							-> skip;