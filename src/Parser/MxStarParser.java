// Generated from MxStarParser.g4 by ANTLR 4.7.1

package Parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxStarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Add=1, Sub=2, Mul=3, Div=4, Mod=5, Greater=6, Less=7, Equal=8, GreaterEqual=9, 
		LessEqual=10, NotEqual=11, LogicAnd=12, LogicOr=13, LogicNot=14, ShiftRight=15, 
		ShiftLeft=16, BitAnd=17, BitOr=18, BitNot=19, BitXor=20, Assign=21, Inc=22, 
		Dec=23, Dot=24, LBracket=25, RBracket=26, LParen=27, RParen=28, Comma=29, 
		Semicolon=30, LBrace=31, RBrace=32, DoubleSlash=33, SlashStar=34, StarSlash=35, 
		SingleQuote=36, DoubleQuote=37, LambdaStart=38, RightArrow=39, IntType=40, 
		BoolType=41, StringType=42, Null=43, Void=44, True=45, False=46, If=47, 
		Else=48, For=49, While=50, Break=51, Continue=52, Return=53, New=54, Class=55, 
		This=56, IntLiteral=57, StringLiteral=58, Identifier=59, LineComment=60, 
		BlockComment=61, WhiteSpace=62;
	public static final int
		RULE_program = 0, RULE_suite = 1, RULE_statement = 2, RULE_expression = 3, 
		RULE_literalExpression = 4, RULE_creatorExpression = 5, RULE_lambdaExpression = 6, 
		RULE_varDeclarStatement = 7, RULE_varDeclar = 8, RULE_varSingleDeclar = 9, 
		RULE_typeName = 10, RULE_typeNameUnit = 11, RULE_funcDeclarStatement = 12, 
		RULE_parameterList = 13, RULE_parameter = 14, RULE_returnType = 15, RULE_funcCall = 16, 
		RULE_argumentList = 17, RULE_classDeclarStatement = 18, RULE_classDeclar = 19, 
		RULE_selfConstructor = 20;
	public static final String[] ruleNames = {
		"program", "suite", "statement", "expression", "literalExpression", "creatorExpression", 
		"lambdaExpression", "varDeclarStatement", "varDeclar", "varSingleDeclar", 
		"typeName", "typeNameUnit", "funcDeclarStatement", "parameterList", "parameter", 
		"returnType", "funcCall", "argumentList", "classDeclarStatement", "classDeclar", 
		"selfConstructor"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'<'", "'=='", "'>='", 
		"'<='", "'!='", "'&&'", "'||'", "'!'", "'>>'", "'<<'", "'&'", "'|'", "'~'", 
		"'^'", "'='", "'++'", "'--'", "'.'", "'['", "']'", "'('", "')'", "','", 
		"';'", "'{'", "'}'", "'//'", "'/*'", "'*/'", "'''", "'\"'", "'[&]'", "'->'", 
		"'int'", "'bool'", "'string'", "'null'", "'void'", "'true'", "'false'", 
		"'if'", "'else'", "'for'", "'while'", "'break'", "'continue'", "'return'", 
		"'new'", "'class'", "'this'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "Add", "Sub", "Mul", "Div", "Mod", "Greater", "Less", "Equal", "GreaterEqual", 
		"LessEqual", "NotEqual", "LogicAnd", "LogicOr", "LogicNot", "ShiftRight", 
		"ShiftLeft", "BitAnd", "BitOr", "BitNot", "BitXor", "Assign", "Inc", "Dec", 
		"Dot", "LBracket", "RBracket", "LParen", "RParen", "Comma", "Semicolon", 
		"LBrace", "RBrace", "DoubleSlash", "SlashStar", "StarSlash", "SingleQuote", 
		"DoubleQuote", "LambdaStart", "RightArrow", "IntType", "BoolType", "StringType", 
		"Null", "Void", "True", "False", "If", "Else", "For", "While", "Break", 
		"Continue", "Return", "New", "Class", "This", "IntLiteral", "StringLiteral", 
		"Identifier", "LineComment", "BlockComment", "WhiteSpace"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MxStarParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxStarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxStarParser.EOF, 0); }
		public List<FuncDeclarStatementContext> funcDeclarStatement() {
			return getRuleContexts(FuncDeclarStatementContext.class);
		}
		public FuncDeclarStatementContext funcDeclarStatement(int i) {
			return getRuleContext(FuncDeclarStatementContext.class,i);
		}
		public List<ClassDeclarStatementContext> classDeclarStatement() {
			return getRuleContexts(ClassDeclarStatementContext.class);
		}
		public ClassDeclarStatementContext classDeclarStatement(int i) {
			return getRuleContext(ClassDeclarStatementContext.class,i);
		}
		public List<VarDeclarStatementContext> varDeclarStatement() {
			return getRuleContexts(VarDeclarStatementContext.class);
		}
		public VarDeclarStatementContext varDeclarStatement(int i) {
			return getRuleContext(VarDeclarStatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntType) | (1L << BoolType) | (1L << StringType) | (1L << Void) | (1L << Class) | (1L << Identifier))) != 0)) {
				{
				setState(45);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(42);
					funcDeclarStatement();
					}
					break;
				case 2:
					{
					setState(43);
					classDeclarStatement();
					}
					break;
				case 3:
					{
					setState(44);
					varDeclarStatement();
					}
					break;
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuiteContext extends ParserRuleContext {
		public TerminalNode LBrace() { return getToken(MxStarParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(MxStarParser.RBrace, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(LBrace);
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LogicNot) | (1L << BitNot) | (1L << Inc) | (1L << Dec) | (1L << LParen) | (1L << Semicolon) | (1L << LBrace) | (1L << LambdaStart) | (1L << IntType) | (1L << BoolType) | (1L << StringType) | (1L << Null) | (1L << Void) | (1L << True) | (1L << False) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << Class) | (1L << This) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
				{
				{
				setState(53);
				statement();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
			match(RBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SuitStmtContext extends StatementContext {
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public SuitStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitSuitStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprStmtContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
		public ExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForStmtContext extends StatementContext {
		public ExpressionContext initExpr;
		public VarDeclarContext initVar;
		public ExpressionContext conditionExpr;
		public ExpressionContext increExpr;
		public TerminalNode For() { return getToken(MxStarParser.For, 0); }
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public List<TerminalNode> Semicolon() { return getTokens(MxStarParser.Semicolon); }
		public TerminalNode Semicolon(int i) {
			return getToken(MxStarParser.Semicolon, i);
		}
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public VarDeclarContext varDeclar() {
			return getRuleContext(VarDeclarContext.class,0);
		}
		public ForStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStmtContext extends StatementContext {
		public TerminalNode While() { return getToken(MxStarParser.While, 0); }
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStmtContext extends StatementContext {
		public StatementContext thenStmt;
		public StatementContext elseStmt;
		public TerminalNode If() { return getToken(MxStarParser.If, 0); }
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxStarParser.Else, 0); }
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassDeclStmtContext extends StatementContext {
		public ClassDeclarStatementContext classDeclarStatement() {
			return getRuleContext(ClassDeclarStatementContext.class,0);
		}
		public ClassDeclStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitClassDeclStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncDeclStmtContext extends StatementContext {
		public FuncDeclarStatementContext funcDeclarStatement() {
			return getRuleContext(FuncDeclarStatementContext.class,0);
		}
		public FuncDeclStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitFuncDeclStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakStmtContext extends StatementContext {
		public TerminalNode Break() { return getToken(MxStarParser.Break, 0); }
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
		public BreakStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyStmtContext extends StatementContext {
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
		public EmptyStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitEmptyStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStmtContext extends StatementContext {
		public TerminalNode Return() { return getToken(MxStarParser.Return, 0); }
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueStmtContext extends StatementContext {
		public TerminalNode Continue() { return getToken(MxStarParser.Continue, 0); }
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
		public ContinueStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarDeclStmtContext extends StatementContext {
		public VarDeclarStatementContext varDeclarStatement() {
			return getRuleContext(VarDeclarStatementContext.class,0);
		}
		public VarDeclStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitVarDeclStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		int _la;
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new SuitStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				suite();
				}
				break;
			case 2:
				_localctx = new ExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				expression(0);
				setState(63);
				match(Semicolon);
				}
				break;
			case 3:
				_localctx = new VarDeclStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(65);
				varDeclarStatement();
				}
				break;
			case 4:
				_localctx = new FuncDeclStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(66);
				funcDeclarStatement();
				}
				break;
			case 5:
				_localctx = new ClassDeclStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(67);
				classDeclarStatement();
				}
				break;
			case 6:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(68);
				match(If);
				setState(69);
				match(LParen);
				setState(70);
				expression(0);
				setState(71);
				match(RParen);
				setState(72);
				((IfStmtContext)_localctx).thenStmt = statement();
				setState(75);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(73);
					match(Else);
					setState(74);
					((IfStmtContext)_localctx).elseStmt = statement();
					}
					break;
				}
				}
				break;
			case 7:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(77);
				match(While);
				setState(78);
				match(LParen);
				setState(79);
				expression(0);
				setState(80);
				match(RParen);
				setState(81);
				statement();
				}
				break;
			case 8:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(83);
				match(For);
				setState(84);
				match(LParen);
				setState(87);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(85);
					((ForStmtContext)_localctx).initExpr = expression(0);
					}
					break;
				case 2:
					{
					setState(86);
					((ForStmtContext)_localctx).initVar = varDeclar();
					}
					break;
				}
				setState(89);
				match(Semicolon);
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LogicNot) | (1L << BitNot) | (1L << Inc) | (1L << Dec) | (1L << LParen) | (1L << LambdaStart) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
					{
					setState(90);
					((ForStmtContext)_localctx).conditionExpr = expression(0);
					}
				}

				setState(93);
				match(Semicolon);
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LogicNot) | (1L << BitNot) | (1L << Inc) | (1L << Dec) | (1L << LParen) | (1L << LambdaStart) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
					{
					setState(94);
					((ForStmtContext)_localctx).increExpr = expression(0);
					}
				}

				setState(97);
				match(RParen);
				setState(98);
				statement();
				}
				break;
			case 9:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(99);
				match(Return);
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LogicNot) | (1L << BitNot) | (1L << Inc) | (1L << Dec) | (1L << LParen) | (1L << LambdaStart) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
					{
					setState(100);
					expression(0);
					}
				}

				setState(103);
				match(Semicolon);
				}
				break;
			case 10:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(104);
				match(Break);
				setState(105);
				match(Semicolon);
				}
				break;
			case 11:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(106);
				match(Continue);
				setState(107);
				match(Semicolon);
				}
				break;
			case 12:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(108);
				match(Semicolon);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ThisExprContext extends ExpressionContext {
		public TerminalNode This() { return getToken(MxStarParser.This, 0); }
		public ThisExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitThisExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubscriptExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LBracket() { return getToken(MxStarParser.LBracket, 0); }
		public TerminalNode RBracket() { return getToken(MxStarParser.RBracket, 0); }
		public SubscriptExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitSubscriptExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulDivModExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Mul() { return getToken(MxStarParser.Mul, 0); }
		public TerminalNode Div() { return getToken(MxStarParser.Div, 0); }
		public TerminalNode Mod() { return getToken(MxStarParser.Mod, 0); }
		public MulDivModExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitMulDivModExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncCallExprContext extends ExpressionContext {
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public FuncCallExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitFuncCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExprContext extends ExpressionContext {
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public ParenExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitXorExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BitXor() { return getToken(MxStarParser.BitXor, 0); }
		public BitXorExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBitXorExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberParenExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Dot() { return getToken(MxStarParser.Dot, 0); }
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public TerminalNode Identifier() { return getToken(MxStarParser.Identifier, 0); }
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public MemberParenExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitMemberParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalNotExprContext extends ExpressionContext {
		public TerminalNode LogicNot() { return getToken(MxStarParser.LogicNot, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LogicalNotExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitLogicalNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitNotExprContext extends ExpressionContext {
		public TerminalNode BitNot() { return getToken(MxStarParser.BitNot, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BitNotExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualAndNeqExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Equal() { return getToken(MxStarParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(MxStarParser.NotEqual, 0); }
		public EqualAndNeqExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitEqualAndNeqExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SufIncDecExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Inc() { return getToken(MxStarParser.Inc, 0); }
		public TerminalNode Dec() { return getToken(MxStarParser.Dec, 0); }
		public SufIncDecExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitSufIncDecExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LambdaExprContext extends ExpressionContext {
		public LambdaExpressionContext lambdaExpression() {
			return getRuleContext(LambdaExpressionContext.class,0);
		}
		public LambdaExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitLambdaExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralExprContext extends ExpressionContext {
		public LiteralExpressionContext literalExpression() {
			return getRuleContext(LiteralExpressionContext.class,0);
		}
		public LiteralExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CreatorExprContext extends ExpressionContext {
		public CreatorExpressionContext creatorExpression() {
			return getRuleContext(CreatorExpressionContext.class,0);
		}
		public CreatorExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitCreatorExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitAndExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BitAnd() { return getToken(MxStarParser.BitAnd, 0); }
		public BitAndExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Assign() { return getToken(MxStarParser.Assign, 0); }
		public AssignExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompareExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Less() { return getToken(MxStarParser.Less, 0); }
		public TerminalNode LessEqual() { return getToken(MxStarParser.LessEqual, 0); }
		public TerminalNode Greater() { return getToken(MxStarParser.Greater, 0); }
		public TerminalNode GreaterEqual() { return getToken(MxStarParser.GreaterEqual, 0); }
		public CompareExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitCompareExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Add() { return getToken(MxStarParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxStarParser.Sub, 0); }
		public AddSubExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitAddSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalAndExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LogicAnd() { return getToken(MxStarParser.LogicAnd, 0); }
		public LogicalAndExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitLogicalAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreAddSubExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Add() { return getToken(MxStarParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxStarParser.Sub, 0); }
		public PreAddSubExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitPreAddSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Dot() { return getToken(MxStarParser.Dot, 0); }
		public TerminalNode Identifier() { return getToken(MxStarParser.Identifier, 0); }
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public MemberExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitMemberExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ShiftExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ShiftLeft() { return getToken(MxStarParser.ShiftLeft, 0); }
		public TerminalNode ShiftRight() { return getToken(MxStarParser.ShiftRight, 0); }
		public ShiftExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitShiftExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalOrExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LogicOr() { return getToken(MxStarParser.LogicOr, 0); }
		public LogicalOrExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitLogicalOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitOrExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BitOr() { return getToken(MxStarParser.BitOr, 0); }
		public BitOrExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CommaExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxStarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxStarParser.Comma, i);
		}
		public CommaExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitCommaExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreIncDecExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Inc() { return getToken(MxStarParser.Inc, 0); }
		public TerminalNode Dec() { return getToken(MxStarParser.Dec, 0); }
		public PreIncDecExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitPreIncDecExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierExprContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(MxStarParser.Identifier, 0); }
		public IdentifierExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitIdentifierExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(112);
				match(LParen);
				setState(113);
				expression(0);
				setState(114);
				match(RParen);
				}
				break;
			case 2:
				{
				_localctx = new FuncCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116);
				funcCall();
				}
				break;
			case 3:
				{
				_localctx = new PreIncDecExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(117);
				((PreIncDecExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Inc || _la==Dec) ) {
					((PreIncDecExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(118);
				expression(21);
				}
				break;
			case 4:
				{
				_localctx = new PreAddSubExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(119);
				((PreAddSubExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Add || _la==Sub) ) {
					((PreAddSubExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(120);
				expression(20);
				}
				break;
			case 5:
				{
				_localctx = new LogicalNotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(121);
				match(LogicNot);
				setState(122);
				expression(19);
				}
				break;
			case 6:
				{
				_localctx = new BitNotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(123);
				match(BitNot);
				setState(124);
				expression(18);
				}
				break;
			case 7:
				{
				_localctx = new CreatorExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(125);
				creatorExpression();
				}
				break;
			case 8:
				{
				_localctx = new LambdaExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(126);
				lambdaExpression();
				}
				break;
			case 9:
				{
				_localctx = new ThisExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(127);
				match(This);
				}
				break;
			case 10:
				{
				_localctx = new LiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				literalExpression();
				}
				break;
			case 11:
				{
				_localctx = new IdentifierExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129);
				match(Identifier);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(195);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(193);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivModExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(132);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(133);
						((MulDivModExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) ) {
							((MulDivModExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(134);
						expression(18);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(135);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(136);
						((AddSubExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Add || _la==Sub) ) {
							((AddSubExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(137);
						expression(17);
						}
						break;
					case 3:
						{
						_localctx = new ShiftExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(138);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(139);
						((ShiftExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ShiftRight || _la==ShiftLeft) ) {
							((ShiftExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(140);
						expression(16);
						}
						break;
					case 4:
						{
						_localctx = new CompareExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(141);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(142);
						((CompareExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Greater) | (1L << Less) | (1L << GreaterEqual) | (1L << LessEqual))) != 0)) ) {
							((CompareExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(143);
						expression(15);
						}
						break;
					case 5:
						{
						_localctx = new EqualAndNeqExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(144);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(145);
						((EqualAndNeqExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==NotEqual) ) {
							((EqualAndNeqExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(146);
						expression(14);
						}
						break;
					case 6:
						{
						_localctx = new BitAndExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(147);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(148);
						match(BitAnd);
						setState(149);
						expression(13);
						}
						break;
					case 7:
						{
						_localctx = new BitXorExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(150);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(151);
						match(BitXor);
						setState(152);
						expression(12);
						}
						break;
					case 8:
						{
						_localctx = new BitOrExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(153);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(154);
						match(BitOr);
						setState(155);
						expression(11);
						}
						break;
					case 9:
						{
						_localctx = new LogicalAndExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(156);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(157);
						match(LogicAnd);
						setState(158);
						expression(10);
						}
						break;
					case 10:
						{
						_localctx = new LogicalOrExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(159);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(160);
						match(LogicOr);
						setState(161);
						expression(9);
						}
						break;
					case 11:
						{
						_localctx = new AssignExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(162);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(163);
						match(Assign);
						setState(164);
						expression(7);
						}
						break;
					case 12:
						{
						_localctx = new SufIncDecExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(165);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(166);
						((SufIncDecExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Inc || _la==Dec) ) {
							((SufIncDecExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 13:
						{
						_localctx = new SubscriptExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(167);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						{
						setState(168);
						match(LBracket);
						setState(169);
						expression(0);
						setState(170);
						match(RBracket);
						}
						}
						break;
					case 14:
						{
						_localctx = new MemberExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(172);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(173);
						match(Dot);
						setState(176);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
						case 1:
							{
							setState(174);
							match(Identifier);
							}
							break;
						case 2:
							{
							setState(175);
							funcCall();
							}
							break;
						}
						}
						break;
					case 15:
						{
						_localctx = new MemberParenExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(178);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(179);
						match(Dot);
						setState(180);
						match(LParen);
						setState(183);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
						case 1:
							{
							setState(181);
							match(Identifier);
							}
							break;
						case 2:
							{
							setState(182);
							funcCall();
							}
							break;
						}
						setState(185);
						match(RParen);
						}
						break;
					case 16:
						{
						_localctx = new CommaExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(186);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(189); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(187);
								match(Comma);
								setState(188);
								expression(0);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(191); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					}
					} 
				}
				setState(197);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LiteralExpressionContext extends ParserRuleContext {
		public TerminalNode IntLiteral() { return getToken(MxStarParser.IntLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(MxStarParser.StringLiteral, 0); }
		public TerminalNode True() { return getToken(MxStarParser.True, 0); }
		public TerminalNode False() { return getToken(MxStarParser.False, 0); }
		public TerminalNode Null() { return getToken(MxStarParser.Null, 0); }
		public LiteralExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralExpressionContext literalExpression() throws RecognitionException {
		LiteralExpressionContext _localctx = new LiteralExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_literalExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << IntLiteral) | (1L << StringLiteral))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreatorExpressionContext extends ParserRuleContext {
		public CreatorExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creatorExpression; }
	 
		public CreatorExpressionContext() { }
		public void copyFrom(CreatorExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SingleCreatorContext extends CreatorExpressionContext {
		public TerminalNode New() { return getToken(MxStarParser.New, 0); }
		public TypeNameUnitContext typeNameUnit() {
			return getRuleContext(TypeNameUnitContext.class,0);
		}
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public SingleCreatorContext(CreatorExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitSingleCreator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayCreatorContext extends CreatorExpressionContext {
		public TerminalNode New() { return getToken(MxStarParser.New, 0); }
		public TypeNameUnitContext typeNameUnit() {
			return getRuleContext(TypeNameUnitContext.class,0);
		}
		public List<TerminalNode> LBracket() { return getTokens(MxStarParser.LBracket); }
		public TerminalNode LBracket(int i) {
			return getToken(MxStarParser.LBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RBracket() { return getTokens(MxStarParser.RBracket); }
		public TerminalNode RBracket(int i) {
			return getToken(MxStarParser.RBracket, i);
		}
		public ArrayCreatorContext(CreatorExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitArrayCreator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreatorExpressionContext creatorExpression() throws RecognitionException {
		CreatorExpressionContext _localctx = new CreatorExpressionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_creatorExpression);
		try {
			int _alt;
			setState(223);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new ArrayCreatorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				match(New);
				setState(201);
				typeNameUnit();
				setState(206); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(202);
						match(LBracket);
						setState(203);
						expression(0);
						setState(204);
						match(RBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(208); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(214);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(210);
						match(LBracket);
						setState(211);
						match(RBracket);
						}
						} 
					}
					setState(216);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
				}
				break;
			case 2:
				_localctx = new SingleCreatorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(217);
				match(New);
				setState(218);
				typeNameUnit();
				setState(221);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(219);
					match(LParen);
					setState(220);
					match(RParen);
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaExpressionContext extends ParserRuleContext {
		public TerminalNode LambdaStart() { return getToken(MxStarParser.LambdaStart, 0); }
		public List<TerminalNode> LParen() { return getTokens(MxStarParser.LParen); }
		public TerminalNode LParen(int i) {
			return getToken(MxStarParser.LParen, i);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public List<TerminalNode> RParen() { return getTokens(MxStarParser.RParen); }
		public TerminalNode RParen(int i) {
			return getToken(MxStarParser.RParen, i);
		}
		public TerminalNode RightArrow() { return getToken(MxStarParser.RightArrow, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public LambdaExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitLambdaExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaExpressionContext lambdaExpression() throws RecognitionException {
		LambdaExpressionContext _localctx = new LambdaExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_lambdaExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			match(LambdaStart);
			setState(226);
			match(LParen);
			setState(227);
			parameterList();
			setState(228);
			match(RParen);
			setState(229);
			match(RightArrow);
			setState(230);
			statement();
			setState(231);
			match(LParen);
			setState(232);
			argumentList();
			setState(233);
			match(RParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarStatementContext extends ParserRuleContext {
		public VarDeclarContext varDeclar() {
			return getRuleContext(VarDeclarContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
		public VarDeclarStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclarStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitVarDeclarStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarStatementContext varDeclarStatement() throws RecognitionException {
		VarDeclarStatementContext _localctx = new VarDeclarStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varDeclarStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			varDeclar();
			setState(236);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<VarSingleDeclarContext> varSingleDeclar() {
			return getRuleContexts(VarSingleDeclarContext.class);
		}
		public VarSingleDeclarContext varSingleDeclar(int i) {
			return getRuleContext(VarSingleDeclarContext.class,i);
		}
		public VarDeclarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitVarDeclar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarContext varDeclar() throws RecognitionException {
		VarDeclarContext _localctx = new VarDeclarContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_varDeclar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			typeName();
			setState(239);
			varSingleDeclar();
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(240);
				match(Comma);
				setState(241);
				varSingleDeclar();
				}
				}
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarSingleDeclarContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxStarParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarSingleDeclarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varSingleDeclar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitVarSingleDeclar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarSingleDeclarContext varSingleDeclar() throws RecognitionException {
		VarSingleDeclarContext _localctx = new VarSingleDeclarContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_varSingleDeclar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(Identifier);
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(248);
				match(Assign);
				setState(249);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeNameContext extends ParserRuleContext {
		public TypeNameUnitContext typeNameUnit() {
			return getRuleContext(TypeNameUnitContext.class,0);
		}
		public List<TerminalNode> LBracket() { return getTokens(MxStarParser.LBracket); }
		public TerminalNode LBracket(int i) {
			return getToken(MxStarParser.LBracket, i);
		}
		public List<TerminalNode> RBracket() { return getTokens(MxStarParser.RBracket); }
		public TerminalNode RBracket(int i) {
			return getToken(MxStarParser.RBracket, i);
		}
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_typeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			typeNameUnit();
			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBracket) {
				{
				{
				setState(253);
				match(LBracket);
				setState(254);
				match(RBracket);
				}
				}
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeNameUnitContext extends ParserRuleContext {
		public TerminalNode IntType() { return getToken(MxStarParser.IntType, 0); }
		public TerminalNode BoolType() { return getToken(MxStarParser.BoolType, 0); }
		public TerminalNode StringType() { return getToken(MxStarParser.StringType, 0); }
		public TerminalNode Identifier() { return getToken(MxStarParser.Identifier, 0); }
		public TypeNameUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeNameUnit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitTypeNameUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameUnitContext typeNameUnit() throws RecognitionException {
		TypeNameUnitContext _localctx = new TypeNameUnitContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_typeNameUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntType) | (1L << BoolType) | (1L << StringType) | (1L << Identifier))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDeclarStatementContext extends ParserRuleContext {
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxStarParser.Identifier, 0); }
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public FuncDeclarStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDeclarStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitFuncDeclarStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDeclarStatementContext funcDeclarStatement() throws RecognitionException {
		FuncDeclarStatementContext _localctx = new FuncDeclarStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_funcDeclarStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			returnType();
			setState(263);
			match(Identifier);
			setState(264);
			match(LParen);
			setState(265);
			parameterList();
			setState(266);
			match(RParen);
			setState(267);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_parameterList);
		int _la;
		try {
			setState(278);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntType:
			case BoolType:
			case StringType:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(269);
				parameter();
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(270);
					match(Comma);
					setState(271);
					parameter();
					}
					}
					setState(276);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case RParen:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxStarParser.Identifier, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			typeName();
			setState(281);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnTypeContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode Void() { return getToken(MxStarParser.Void, 0); }
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_returnType);
		try {
			setState(285);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntType:
			case BoolType:
			case StringType:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(283);
				typeName();
				}
				break;
			case Void:
				enterOuterAlt(_localctx, 2);
				{
				setState(284);
				match(Void);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncCallContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxStarParser.Identifier, 0); }
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public FuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_funcCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			match(Identifier);
			setState(288);
			match(LParen);
			setState(289);
			argumentList();
			setState(290);
			match(RParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxStarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxStarParser.Comma, i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitArgumentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_argumentList);
		int _la;
		try {
			setState(301);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Add:
			case Sub:
			case LogicNot:
			case BitNot:
			case Inc:
			case Dec:
			case LParen:
			case LambdaStart:
			case Null:
			case True:
			case False:
			case New:
			case This:
			case IntLiteral:
			case StringLiteral:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(292);
				expression(0);
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(293);
					match(Comma);
					setState(294);
					expression(0);
					}
					}
					setState(299);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case RParen:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarStatementContext extends ParserRuleContext {
		public ClassDeclarContext classDeclar() {
			return getRuleContext(ClassDeclarContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
		public ClassDeclarStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclarStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitClassDeclarStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarStatementContext classDeclarStatement() throws RecognitionException {
		ClassDeclarStatementContext _localctx = new ClassDeclarStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_classDeclarStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			classDeclar();
			setState(304);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxStarParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxStarParser.Identifier, 0); }
		public TerminalNode LBrace() { return getToken(MxStarParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(MxStarParser.RBrace, 0); }
		public List<VarDeclarStatementContext> varDeclarStatement() {
			return getRuleContexts(VarDeclarStatementContext.class);
		}
		public VarDeclarStatementContext varDeclarStatement(int i) {
			return getRuleContext(VarDeclarStatementContext.class,i);
		}
		public List<SelfConstructorContext> selfConstructor() {
			return getRuleContexts(SelfConstructorContext.class);
		}
		public SelfConstructorContext selfConstructor(int i) {
			return getRuleContext(SelfConstructorContext.class,i);
		}
		public List<FuncDeclarStatementContext> funcDeclarStatement() {
			return getRuleContexts(FuncDeclarStatementContext.class);
		}
		public FuncDeclarStatementContext funcDeclarStatement(int i) {
			return getRuleContext(FuncDeclarStatementContext.class,i);
		}
		public ClassDeclarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitClassDeclar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarContext classDeclar() throws RecognitionException {
		ClassDeclarContext _localctx = new ClassDeclarContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_classDeclar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			match(Class);
			setState(307);
			match(Identifier);
			setState(308);
			match(LBrace);
			setState(314);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntType) | (1L << BoolType) | (1L << StringType) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(312);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(309);
					varDeclarStatement();
					}
					break;
				case 2:
					{
					setState(310);
					selfConstructor();
					}
					break;
				case 3:
					{
					setState(311);
					funcDeclarStatement();
					}
					break;
				}
				}
				setState(316);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(317);
			match(RBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelfConstructorContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxStarParser.Identifier, 0); }
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public SelfConstructorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selfConstructor; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitSelfConstructor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelfConstructorContext selfConstructor() throws RecognitionException {
		SelfConstructorContext _localctx = new SelfConstructorContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_selfConstructor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			match(Identifier);
			setState(320);
			match(LParen);
			setState(321);
			match(RParen);
			setState(322);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 17);
		case 1:
			return precpred(_ctx, 16);
		case 2:
			return precpred(_ctx, 15);
		case 3:
			return precpred(_ctx, 14);
		case 4:
			return precpred(_ctx, 13);
		case 5:
			return precpred(_ctx, 12);
		case 6:
			return precpred(_ctx, 11);
		case 7:
			return precpred(_ctx, 10);
		case 8:
			return precpred(_ctx, 9);
		case 9:
			return precpred(_ctx, 8);
		case 10:
			return precpred(_ctx, 7);
		case 11:
			return precpred(_ctx, 26);
		case 12:
			return precpred(_ctx, 24);
		case 13:
			return precpred(_ctx, 23);
		case 14:
			return precpred(_ctx, 22);
		case 15:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3@\u0147\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\7\2\60\n\2\f\2\16"+
		"\2\63\13\2\3\2\3\2\3\3\3\3\7\39\n\3\f\3\16\3<\13\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4N\n\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\5\4Z\n\4\3\4\3\4\5\4^\n\4\3\4\3\4\5\4b\n\4\3"+
		"\4\3\4\3\4\3\4\5\4h\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4p\n\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u0085"+
		"\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00b3\n\5\3\5\3\5\3\5\3\5"+
		"\3\5\5\5\u00ba\n\5\3\5\3\5\3\5\3\5\6\5\u00c0\n\5\r\5\16\5\u00c1\7\5\u00c4"+
		"\n\5\f\5\16\5\u00c7\13\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\6\7\u00d1\n\7"+
		"\r\7\16\7\u00d2\3\7\3\7\7\7\u00d7\n\7\f\7\16\7\u00da\13\7\3\7\3\7\3\7"+
		"\3\7\5\7\u00e0\n\7\5\7\u00e2\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\7\n\u00f5\n\n\f\n\16\n\u00f8\13\n\3\13"+
		"\3\13\3\13\5\13\u00fd\n\13\3\f\3\f\3\f\7\f\u0102\n\f\f\f\16\f\u0105\13"+
		"\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\7\17\u0113"+
		"\n\17\f\17\16\17\u0116\13\17\3\17\5\17\u0119\n\17\3\20\3\20\3\20\3\21"+
		"\3\21\5\21\u0120\n\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\7\23\u012a"+
		"\n\23\f\23\16\23\u012d\13\23\3\23\5\23\u0130\n\23\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\7\25\u013b\n\25\f\25\16\25\u013e\13\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\3\26\3\26\2\3\b\27\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*\2\n\3\2\30\31\3\2\3\4\3\2\5\7\3\2\21\22\4\2\b\t\13"+
		"\f\4\2\n\n\r\r\5\2--/\60;<\4\2*,==\2\u0172\2\61\3\2\2\2\4\66\3\2\2\2\6"+
		"o\3\2\2\2\b\u0084\3\2\2\2\n\u00c8\3\2\2\2\f\u00e1\3\2\2\2\16\u00e3\3\2"+
		"\2\2\20\u00ed\3\2\2\2\22\u00f0\3\2\2\2\24\u00f9\3\2\2\2\26\u00fe\3\2\2"+
		"\2\30\u0106\3\2\2\2\32\u0108\3\2\2\2\34\u0118\3\2\2\2\36\u011a\3\2\2\2"+
		" \u011f\3\2\2\2\"\u0121\3\2\2\2$\u012f\3\2\2\2&\u0131\3\2\2\2(\u0134\3"+
		"\2\2\2*\u0141\3\2\2\2,\60\5\32\16\2-\60\5&\24\2.\60\5\20\t\2/,\3\2\2\2"+
		"/-\3\2\2\2/.\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\64\3\2"+
		"\2\2\63\61\3\2\2\2\64\65\7\2\2\3\65\3\3\2\2\2\66:\7!\2\2\679\5\6\4\28"+
		"\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;=\3\2\2\2<:\3\2\2\2=>\7\"\2"+
		"\2>\5\3\2\2\2?p\5\4\3\2@A\5\b\5\2AB\7 \2\2Bp\3\2\2\2Cp\5\20\t\2Dp\5\32"+
		"\16\2Ep\5&\24\2FG\7\61\2\2GH\7\35\2\2HI\5\b\5\2IJ\7\36\2\2JM\5\6\4\2K"+
		"L\7\62\2\2LN\5\6\4\2MK\3\2\2\2MN\3\2\2\2Np\3\2\2\2OP\7\64\2\2PQ\7\35\2"+
		"\2QR\5\b\5\2RS\7\36\2\2ST\5\6\4\2Tp\3\2\2\2UV\7\63\2\2VY\7\35\2\2WZ\5"+
		"\b\5\2XZ\5\22\n\2YW\3\2\2\2YX\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2[]\7 \2\2\\^"+
		"\5\b\5\2]\\\3\2\2\2]^\3\2\2\2^_\3\2\2\2_a\7 \2\2`b\5\b\5\2a`\3\2\2\2a"+
		"b\3\2\2\2bc\3\2\2\2cd\7\36\2\2dp\5\6\4\2eg\7\67\2\2fh\5\b\5\2gf\3\2\2"+
		"\2gh\3\2\2\2hi\3\2\2\2ip\7 \2\2jk\7\65\2\2kp\7 \2\2lm\7\66\2\2mp\7 \2"+
		"\2np\7 \2\2o?\3\2\2\2o@\3\2\2\2oC\3\2\2\2oD\3\2\2\2oE\3\2\2\2oF\3\2\2"+
		"\2oO\3\2\2\2oU\3\2\2\2oe\3\2\2\2oj\3\2\2\2ol\3\2\2\2on\3\2\2\2p\7\3\2"+
		"\2\2qr\b\5\1\2rs\7\35\2\2st\5\b\5\2tu\7\36\2\2u\u0085\3\2\2\2v\u0085\5"+
		"\"\22\2wx\t\2\2\2x\u0085\5\b\5\27yz\t\3\2\2z\u0085\5\b\5\26{|\7\20\2\2"+
		"|\u0085\5\b\5\25}~\7\25\2\2~\u0085\5\b\5\24\177\u0085\5\f\7\2\u0080\u0085"+
		"\5\16\b\2\u0081\u0085\7:\2\2\u0082\u0085\5\n\6\2\u0083\u0085\7=\2\2\u0084"+
		"q\3\2\2\2\u0084v\3\2\2\2\u0084w\3\2\2\2\u0084y\3\2\2\2\u0084{\3\2\2\2"+
		"\u0084}\3\2\2\2\u0084\177\3\2\2\2\u0084\u0080\3\2\2\2\u0084\u0081\3\2"+
		"\2\2\u0084\u0082\3\2\2\2\u0084\u0083\3\2\2\2\u0085\u00c5\3\2\2\2\u0086"+
		"\u0087\f\23\2\2\u0087\u0088\t\4\2\2\u0088\u00c4\5\b\5\24\u0089\u008a\f"+
		"\22\2\2\u008a\u008b\t\3\2\2\u008b\u00c4\5\b\5\23\u008c\u008d\f\21\2\2"+
		"\u008d\u008e\t\5\2\2\u008e\u00c4\5\b\5\22\u008f\u0090\f\20\2\2\u0090\u0091"+
		"\t\6\2\2\u0091\u00c4\5\b\5\21\u0092\u0093\f\17\2\2\u0093\u0094\t\7\2\2"+
		"\u0094\u00c4\5\b\5\20\u0095\u0096\f\16\2\2\u0096\u0097\7\23\2\2\u0097"+
		"\u00c4\5\b\5\17\u0098\u0099\f\r\2\2\u0099\u009a\7\26\2\2\u009a\u00c4\5"+
		"\b\5\16\u009b\u009c\f\f\2\2\u009c\u009d\7\24\2\2\u009d\u00c4\5\b\5\r\u009e"+
		"\u009f\f\13\2\2\u009f\u00a0\7\16\2\2\u00a0\u00c4\5\b\5\f\u00a1\u00a2\f"+
		"\n\2\2\u00a2\u00a3\7\17\2\2\u00a3\u00c4\5\b\5\13\u00a4\u00a5\f\t\2\2\u00a5"+
		"\u00a6\7\27\2\2\u00a6\u00c4\5\b\5\t\u00a7\u00a8\f\34\2\2\u00a8\u00c4\t"+
		"\2\2\2\u00a9\u00aa\f\32\2\2\u00aa\u00ab\7\33\2\2\u00ab\u00ac\5\b\5\2\u00ac"+
		"\u00ad\7\34\2\2\u00ad\u00c4\3\2\2\2\u00ae\u00af\f\31\2\2\u00af\u00b2\7"+
		"\32\2\2\u00b0\u00b3\7=\2\2\u00b1\u00b3\5\"\22\2\u00b2\u00b0\3\2\2\2\u00b2"+
		"\u00b1\3\2\2\2\u00b3\u00c4\3\2\2\2\u00b4\u00b5\f\30\2\2\u00b5\u00b6\7"+
		"\32\2\2\u00b6\u00b9\7\35\2\2\u00b7\u00ba\7=\2\2\u00b8\u00ba\5\"\22\2\u00b9"+
		"\u00b7\3\2\2\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00c4\7\36"+
		"\2\2\u00bc\u00bf\f\b\2\2\u00bd\u00be\7\37\2\2\u00be\u00c0\5\b\5\2\u00bf"+
		"\u00bd\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2"+
		"\2\2\u00c2\u00c4\3\2\2\2\u00c3\u0086\3\2\2\2\u00c3\u0089\3\2\2\2\u00c3"+
		"\u008c\3\2\2\2\u00c3\u008f\3\2\2\2\u00c3\u0092\3\2\2\2\u00c3\u0095\3\2"+
		"\2\2\u00c3\u0098\3\2\2\2\u00c3\u009b\3\2\2\2\u00c3\u009e\3\2\2\2\u00c3"+
		"\u00a1\3\2\2\2\u00c3\u00a4\3\2\2\2\u00c3\u00a7\3\2\2\2\u00c3\u00a9\3\2"+
		"\2\2\u00c3\u00ae\3\2\2\2\u00c3\u00b4\3\2\2\2\u00c3\u00bc\3\2\2\2\u00c4"+
		"\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\t\3\2\2\2"+
		"\u00c7\u00c5\3\2\2\2\u00c8\u00c9\t\b\2\2\u00c9\13\3\2\2\2\u00ca\u00cb"+
		"\78\2\2\u00cb\u00d0\5\30\r\2\u00cc\u00cd\7\33\2\2\u00cd\u00ce\5\b\5\2"+
		"\u00ce\u00cf\7\34\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00cc\3\2\2\2\u00d1\u00d2"+
		"\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d8\3\2\2\2\u00d4"+
		"\u00d5\7\33\2\2\u00d5\u00d7\7\34\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00da\3"+
		"\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00e2\3\2\2\2\u00da"+
		"\u00d8\3\2\2\2\u00db\u00dc\78\2\2\u00dc\u00df\5\30\r\2\u00dd\u00de\7\35"+
		"\2\2\u00de\u00e0\7\36\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0"+
		"\u00e2\3\2\2\2\u00e1\u00ca\3\2\2\2\u00e1\u00db\3\2\2\2\u00e2\r\3\2\2\2"+
		"\u00e3\u00e4\7(\2\2\u00e4\u00e5\7\35\2\2\u00e5\u00e6\5\34\17\2\u00e6\u00e7"+
		"\7\36\2\2\u00e7\u00e8\7)\2\2\u00e8\u00e9\5\6\4\2\u00e9\u00ea\7\35\2\2"+
		"\u00ea\u00eb\5$\23\2\u00eb\u00ec\7\36\2\2\u00ec\17\3\2\2\2\u00ed\u00ee"+
		"\5\22\n\2\u00ee\u00ef\7 \2\2\u00ef\21\3\2\2\2\u00f0\u00f1\5\26\f\2\u00f1"+
		"\u00f6\5\24\13\2\u00f2\u00f3\7\37\2\2\u00f3\u00f5\5\24\13\2\u00f4\u00f2"+
		"\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7"+
		"\23\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9\u00fc\7=\2\2\u00fa\u00fb\7\27\2"+
		"\2\u00fb\u00fd\5\b\5\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\25"+
		"\3\2\2\2\u00fe\u0103\5\30\r\2\u00ff\u0100\7\33\2\2\u0100\u0102\7\34\2"+
		"\2\u0101\u00ff\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104"+
		"\3\2\2\2\u0104\27\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u0107\t\t\2\2\u0107"+
		"\31\3\2\2\2\u0108\u0109\5 \21\2\u0109\u010a\7=\2\2\u010a\u010b\7\35\2"+
		"\2\u010b\u010c\5\34\17\2\u010c\u010d\7\36\2\2\u010d\u010e\5\6\4\2\u010e"+
		"\33\3\2\2\2\u010f\u0114\5\36\20\2\u0110\u0111\7\37\2\2\u0111\u0113\5\36"+
		"\20\2\u0112\u0110\3\2\2\2\u0113\u0116\3\2\2\2\u0114\u0112\3\2\2\2\u0114"+
		"\u0115\3\2\2\2\u0115\u0119\3\2\2\2\u0116\u0114\3\2\2\2\u0117\u0119\3\2"+
		"\2\2\u0118\u010f\3\2\2\2\u0118\u0117\3\2\2\2\u0119\35\3\2\2\2\u011a\u011b"+
		"\5\26\f\2\u011b\u011c\7=\2\2\u011c\37\3\2\2\2\u011d\u0120\5\26\f\2\u011e"+
		"\u0120\7.\2\2\u011f\u011d\3\2\2\2\u011f\u011e\3\2\2\2\u0120!\3\2\2\2\u0121"+
		"\u0122\7=\2\2\u0122\u0123\7\35\2\2\u0123\u0124\5$\23\2\u0124\u0125\7\36"+
		"\2\2\u0125#\3\2\2\2\u0126\u012b\5\b\5\2\u0127\u0128\7\37\2\2\u0128\u012a"+
		"\5\b\5\2\u0129\u0127\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b"+
		"\u012c\3\2\2\2\u012c\u0130\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u0130\3\2"+
		"\2\2\u012f\u0126\3\2\2\2\u012f\u012e\3\2\2\2\u0130%\3\2\2\2\u0131\u0132"+
		"\5(\25\2\u0132\u0133\7 \2\2\u0133\'\3\2\2\2\u0134\u0135\79\2\2\u0135\u0136"+
		"\7=\2\2\u0136\u013c\7!\2\2\u0137\u013b\5\20\t\2\u0138\u013b\5*\26\2\u0139"+
		"\u013b\5\32\16\2\u013a\u0137\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u0139\3"+
		"\2\2\2\u013b\u013e\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d"+
		"\u013f\3\2\2\2\u013e\u013c\3\2\2\2\u013f\u0140\7\"\2\2\u0140)\3\2\2\2"+
		"\u0141\u0142\7=\2\2\u0142\u0143\7\35\2\2\u0143\u0144\7\36\2\2\u0144\u0145"+
		"\5\6\4\2\u0145+\3\2\2\2\37/\61:MY]ago\u0084\u00b2\u00b9\u00c1\u00c3\u00c5"+
		"\u00d2\u00d8\u00df\u00e1\u00f6\u00fc\u0103\u0114\u0118\u011f\u012b\u012f"+
		"\u013a\u013c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}