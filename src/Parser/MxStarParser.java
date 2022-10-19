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
		RULE_literalExpression = 4, RULE_lambdaExpression = 5, RULE_varDeclar = 6, 
		RULE_varSingleDeclar = 7, RULE_typeName = 8, RULE_typeNameUnit = 9, RULE_funcDeclar = 10, 
		RULE_parameterList = 11, RULE_parameter = 12, RULE_returnType = 13, RULE_funcCall = 14, 
		RULE_argumentList = 15, RULE_classDeclar = 16, RULE_selfConstructor = 17;
	public static final String[] ruleNames = {
		"program", "suite", "statement", "expression", "literalExpression", "lambdaExpression", 
		"varDeclar", "varSingleDeclar", "typeName", "typeNameUnit", "funcDeclar", 
		"parameterList", "parameter", "returnType", "funcCall", "argumentList", 
		"classDeclar", "selfConstructor"
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
		public List<FuncDeclarContext> funcDeclar() {
			return getRuleContexts(FuncDeclarContext.class);
		}
		public FuncDeclarContext funcDeclar(int i) {
			return getRuleContext(FuncDeclarContext.class,i);
		}
		public List<ClassDeclarContext> classDeclar() {
			return getRuleContexts(ClassDeclarContext.class);
		}
		public ClassDeclarContext classDeclar(int i) {
			return getRuleContext(ClassDeclarContext.class,i);
		}
		public List<TerminalNode> Semicolon() { return getTokens(MxStarParser.Semicolon); }
		public TerminalNode Semicolon(int i) {
			return getToken(MxStarParser.Semicolon, i);
		}
		public List<VarDeclarContext> varDeclar() {
			return getRuleContexts(VarDeclarContext.class);
		}
		public VarDeclarContext varDeclar(int i) {
			return getRuleContext(VarDeclarContext.class,i);
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
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntType) | (1L << BoolType) | (1L << StringType) | (1L << Void) | (1L << Class) | (1L << Identifier))) != 0)) {
				{
				setState(43);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(36);
					funcDeclar();
					}
					break;
				case 2:
					{
					setState(37);
					classDeclar();
					setState(38);
					match(Semicolon);
					}
					break;
				case 3:
					{
					setState(40);
					varDeclar();
					setState(41);
					match(Semicolon);
					}
					break;
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
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
			setState(50);
			match(LBrace);
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LogicNot) | (1L << BitNot) | (1L << Inc) | (1L << Dec) | (1L << LParen) | (1L << Semicolon) | (1L << LBrace) | (1L << LambdaStart) | (1L << IntType) | (1L << BoolType) | (1L << StringType) | (1L << Null) | (1L << Void) | (1L << True) | (1L << False) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << Class) | (1L << This) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
				{
				{
				setState(51);
				statement();
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(57);
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
		public ClassDeclarContext classDeclar() {
			return getRuleContext(ClassDeclarContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
		public ClassDeclStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitClassDeclStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncDeclStmtContext extends StatementContext {
		public FuncDeclarContext funcDeclar() {
			return getRuleContext(FuncDeclarContext.class,0);
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
		public VarDeclarContext varDeclar() {
			return getRuleContext(VarDeclarContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
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
			setState(111);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new SuitStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				suite();
				}
				break;
			case 2:
				_localctx = new ExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				expression(0);
				setState(61);
				match(Semicolon);
				}
				break;
			case 3:
				_localctx = new VarDeclStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				varDeclar();
				setState(64);
				match(Semicolon);
				}
				break;
			case 4:
				_localctx = new FuncDeclStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(66);
				funcDeclar();
				}
				break;
			case 5:
				_localctx = new ClassDeclStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(67);
				classDeclar();
				setState(68);
				match(Semicolon);
				}
				break;
			case 6:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(70);
				match(If);
				setState(71);
				match(LParen);
				setState(72);
				expression(0);
				setState(73);
				match(RParen);
				setState(74);
				((IfStmtContext)_localctx).thenStmt = statement();
				setState(77);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(75);
					match(Else);
					setState(76);
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
				setState(79);
				match(While);
				setState(80);
				match(LParen);
				setState(81);
				expression(0);
				setState(82);
				match(RParen);
				setState(83);
				statement();
				}
				break;
			case 8:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(85);
				match(For);
				setState(86);
				match(LParen);
				setState(89);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(87);
					((ForStmtContext)_localctx).initExpr = expression(0);
					}
					break;
				case 2:
					{
					setState(88);
					((ForStmtContext)_localctx).initVar = varDeclar();
					}
					break;
				}
				setState(91);
				match(Semicolon);
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LogicNot) | (1L << BitNot) | (1L << Inc) | (1L << Dec) | (1L << LParen) | (1L << LambdaStart) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
					{
					setState(92);
					((ForStmtContext)_localctx).conditionExpr = expression(0);
					}
				}

				setState(95);
				match(Semicolon);
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LogicNot) | (1L << BitNot) | (1L << Inc) | (1L << Dec) | (1L << LParen) | (1L << LambdaStart) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
					{
					setState(96);
					((ForStmtContext)_localctx).increExpr = expression(0);
					}
				}

				setState(99);
				match(RParen);
				setState(100);
				statement();
				}
				break;
			case 9:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(101);
				match(Return);
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LogicNot) | (1L << BitNot) | (1L << Inc) | (1L << Dec) | (1L << LParen) | (1L << LambdaStart) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
					{
					setState(102);
					expression(0);
					}
				}

				setState(105);
				match(Semicolon);
				}
				break;
			case 10:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(106);
				match(Break);
				setState(107);
				match(Semicolon);
				}
				break;
			case 11:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(108);
				match(Continue);
				setState(109);
				match(Semicolon);
				}
				break;
			case 12:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(110);
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
	public static class SingleCreatorContext extends ExpressionContext {
		public TerminalNode New() { return getToken(MxStarParser.New, 0); }
		public TypeNameUnitContext typeNameUnit() {
			return getRuleContext(TypeNameUnitContext.class,0);
		}
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public SingleCreatorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitSingleCreator(this);
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
	public static class Binary9ExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LogicAnd() { return getToken(MxStarParser.LogicAnd, 0); }
		public Binary9ExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBinary9Expr(this);
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
	public static class Binary4ExprContext extends ExpressionContext {
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
		public Binary4ExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBinary4Expr(this);
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
	public static class Unary2ExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Inc() { return getToken(MxStarParser.Inc, 0); }
		public TerminalNode Dec() { return getToken(MxStarParser.Dec, 0); }
		public TerminalNode Add() { return getToken(MxStarParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxStarParser.Sub, 0); }
		public TerminalNode LogicNot() { return getToken(MxStarParser.LogicNot, 0); }
		public TerminalNode BitNot() { return getToken(MxStarParser.BitNot, 0); }
		public Unary2ExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitUnary2Expr(this);
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
	public static class Binary5ExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Equal() { return getToken(MxStarParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(MxStarParser.NotEqual, 0); }
		public Binary5ExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBinary5Expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Binary1ExprContext extends ExpressionContext {
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
		public Binary1ExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBinary1Expr(this);
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
	public static class Binary7ExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BitXor() { return getToken(MxStarParser.BitXor, 0); }
		public Binary7ExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBinary7Expr(this);
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
	public static class Unary1ExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Inc() { return getToken(MxStarParser.Inc, 0); }
		public TerminalNode Dec() { return getToken(MxStarParser.Dec, 0); }
		public Unary1ExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitUnary1Expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Binary6ExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BitAnd() { return getToken(MxStarParser.BitAnd, 0); }
		public Binary6ExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBinary6Expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayCreatorContext extends ExpressionContext {
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
		public ArrayCreatorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitArrayCreator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Binary2ExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Add() { return getToken(MxStarParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxStarParser.Sub, 0); }
		public Binary2ExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBinary2Expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Binary8ExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BitOr() { return getToken(MxStarParser.BitOr, 0); }
		public Binary8ExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBinary8Expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Binary3ExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ShiftLeft() { return getToken(MxStarParser.ShiftLeft, 0); }
		public TerminalNode ShiftRight() { return getToken(MxStarParser.ShiftRight, 0); }
		public Binary3ExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBinary3Expr(this);
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
	public static class Binary10ExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LogicOr() { return getToken(MxStarParser.LogicOr, 0); }
		public Binary10ExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitBinary10Expr(this);
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
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(114);
				match(LParen);
				setState(115);
				expression(0);
				setState(116);
				match(RParen);
				}
				break;
			case 2:
				{
				_localctx = new FuncCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(118);
				funcCall();
				}
				break;
			case 3:
				{
				_localctx = new Unary2ExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(119);
				((Unary2ExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LogicNot) | (1L << BitNot) | (1L << Inc) | (1L << Dec))) != 0)) ) {
					((Unary2ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(120);
				expression(19);
				}
				break;
			case 4:
				{
				_localctx = new ArrayCreatorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(121);
				match(New);
				setState(122);
				typeNameUnit();
				setState(127); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(123);
						match(LBracket);
						setState(124);
						expression(0);
						setState(125);
						match(RBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(129); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(135);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(131);
						match(LBracket);
						setState(132);
						match(RBracket);
						}
						} 
					}
					setState(137);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				}
				break;
			case 5:
				{
				_localctx = new SingleCreatorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(138);
				match(New);
				setState(139);
				typeNameUnit();
				setState(142);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(140);
					match(LParen);
					setState(141);
					match(RParen);
					}
					break;
				}
				}
				break;
			case 6:
				{
				_localctx = new LambdaExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(144);
				lambdaExpression();
				}
				break;
			case 7:
				{
				_localctx = new ThisExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145);
				match(This);
				}
				break;
			case 8:
				{
				_localctx = new LiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(146);
				literalExpression();
				}
				break;
			case 9:
				{
				_localctx = new IdentifierExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(147);
				match(Identifier);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(213);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(211);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new Binary1ExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(150);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(151);
						((Binary1ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) ) {
							((Binary1ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(152);
						expression(19);
						}
						break;
					case 2:
						{
						_localctx = new Binary2ExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(153);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(154);
						((Binary2ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Add || _la==Sub) ) {
							((Binary2ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(155);
						expression(18);
						}
						break;
					case 3:
						{
						_localctx = new Binary3ExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(156);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(157);
						((Binary3ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ShiftRight || _la==ShiftLeft) ) {
							((Binary3ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(158);
						expression(17);
						}
						break;
					case 4:
						{
						_localctx = new Binary4ExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(159);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(160);
						((Binary4ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Greater) | (1L << Less) | (1L << GreaterEqual) | (1L << LessEqual))) != 0)) ) {
							((Binary4ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(161);
						expression(16);
						}
						break;
					case 5:
						{
						_localctx = new Binary5ExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(162);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(163);
						((Binary5ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==NotEqual) ) {
							((Binary5ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(164);
						expression(15);
						}
						break;
					case 6:
						{
						_localctx = new Binary6ExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(165);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(166);
						((Binary6ExprContext)_localctx).op = match(BitAnd);
						setState(167);
						expression(14);
						}
						break;
					case 7:
						{
						_localctx = new Binary7ExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(168);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(169);
						((Binary7ExprContext)_localctx).op = match(BitXor);
						setState(170);
						expression(13);
						}
						break;
					case 8:
						{
						_localctx = new Binary8ExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(171);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(172);
						((Binary8ExprContext)_localctx).op = match(BitOr);
						setState(173);
						expression(12);
						}
						break;
					case 9:
						{
						_localctx = new Binary9ExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(174);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(175);
						((Binary9ExprContext)_localctx).op = match(LogicAnd);
						setState(176);
						expression(11);
						}
						break;
					case 10:
						{
						_localctx = new Binary10ExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(177);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(178);
						((Binary10ExprContext)_localctx).op = match(LogicOr);
						setState(179);
						expression(10);
						}
						break;
					case 11:
						{
						_localctx = new AssignExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(180);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(181);
						match(Assign);
						setState(182);
						expression(8);
						}
						break;
					case 12:
						{
						_localctx = new Unary1ExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(183);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(184);
						((Unary1ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Inc || _la==Dec) ) {
							((Unary1ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
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
						setState(185);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						{
						setState(186);
						match(LBracket);
						setState(187);
						expression(0);
						setState(188);
						match(RBracket);
						}
						}
						break;
					case 14:
						{
						_localctx = new MemberExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(190);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(191);
						match(Dot);
						setState(194);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
						case 1:
							{
							setState(192);
							match(Identifier);
							}
							break;
						case 2:
							{
							setState(193);
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
						setState(196);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(197);
						match(Dot);
						setState(198);
						match(LParen);
						setState(201);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
						case 1:
							{
							setState(199);
							match(Identifier);
							}
							break;
						case 2:
							{
							setState(200);
							funcCall();
							}
							break;
						}
						setState(203);
						match(RParen);
						}
						break;
					case 16:
						{
						_localctx = new CommaExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(204);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(207); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(205);
								match(Comma);
								setState(206);
								expression(0);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(209); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					}
					} 
				}
				setState(215);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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
			setState(216);
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
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
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
		enterRule(_localctx, 10, RULE_lambdaExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(LambdaStart);
			setState(219);
			match(LParen);
			setState(220);
			parameterList();
			setState(221);
			match(RParen);
			setState(222);
			match(RightArrow);
			setState(223);
			suite();
			setState(224);
			match(LParen);
			setState(225);
			argumentList();
			setState(226);
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
		enterRule(_localctx, 12, RULE_varDeclar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			typeName();
			setState(229);
			varSingleDeclar();
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(230);
				match(Comma);
				setState(231);
				varSingleDeclar();
				}
				}
				setState(236);
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
		enterRule(_localctx, 14, RULE_varSingleDeclar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(Identifier);
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(238);
				match(Assign);
				setState(239);
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
		enterRule(_localctx, 16, RULE_typeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			typeNameUnit();
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBracket) {
				{
				{
				setState(243);
				match(LBracket);
				setState(244);
				match(RBracket);
				}
				}
				setState(249);
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
		enterRule(_localctx, 18, RULE_typeNameUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
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

	public static class FuncDeclarContext extends ParserRuleContext {
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxStarParser.Identifier, 0); }
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public FuncDeclarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDeclar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxStarParserVisitor ) return ((MxStarParserVisitor<? extends T>)visitor).visitFuncDeclar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDeclarContext funcDeclar() throws RecognitionException {
		FuncDeclarContext _localctx = new FuncDeclarContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_funcDeclar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			returnType();
			setState(253);
			match(Identifier);
			setState(254);
			match(LParen);
			setState(255);
			parameterList();
			setState(256);
			match(RParen);
			setState(257);
			suite();
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
		enterRule(_localctx, 22, RULE_parameterList);
		int _la;
		try {
			setState(268);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntType:
			case BoolType:
			case StringType:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				parameter();
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(260);
					match(Comma);
					setState(261);
					parameter();
					}
					}
					setState(266);
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
		enterRule(_localctx, 24, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			typeName();
			setState(271);
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
		enterRule(_localctx, 26, RULE_returnType);
		try {
			setState(275);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntType:
			case BoolType:
			case StringType:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(273);
				typeName();
				}
				break;
			case Void:
				enterOuterAlt(_localctx, 2);
				{
				setState(274);
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
		enterRule(_localctx, 28, RULE_funcCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(Identifier);
			setState(278);
			match(LParen);
			setState(279);
			argumentList();
			setState(280);
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
		enterRule(_localctx, 30, RULE_argumentList);
		int _la;
		try {
			setState(291);
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
				setState(282);
				expression(0);
				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(283);
					match(Comma);
					setState(284);
					expression(0);
					}
					}
					setState(289);
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

	public static class ClassDeclarContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxStarParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxStarParser.Identifier, 0); }
		public TerminalNode LBrace() { return getToken(MxStarParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(MxStarParser.RBrace, 0); }
		public List<VarDeclarContext> varDeclar() {
			return getRuleContexts(VarDeclarContext.class);
		}
		public VarDeclarContext varDeclar(int i) {
			return getRuleContext(VarDeclarContext.class,i);
		}
		public List<TerminalNode> Semicolon() { return getTokens(MxStarParser.Semicolon); }
		public TerminalNode Semicolon(int i) {
			return getToken(MxStarParser.Semicolon, i);
		}
		public List<SelfConstructorContext> selfConstructor() {
			return getRuleContexts(SelfConstructorContext.class);
		}
		public SelfConstructorContext selfConstructor(int i) {
			return getRuleContext(SelfConstructorContext.class,i);
		}
		public List<FuncDeclarContext> funcDeclar() {
			return getRuleContexts(FuncDeclarContext.class);
		}
		public FuncDeclarContext funcDeclar(int i) {
			return getRuleContext(FuncDeclarContext.class,i);
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
		enterRule(_localctx, 32, RULE_classDeclar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(Class);
			setState(294);
			match(Identifier);
			setState(295);
			match(LBrace);
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntType) | (1L << BoolType) | (1L << StringType) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(301);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(296);
					varDeclar();
					setState(297);
					match(Semicolon);
					}
					break;
				case 2:
					{
					setState(299);
					selfConstructor();
					}
					break;
				case 3:
					{
					setState(300);
					funcDeclar();
					}
					break;
				}
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(306);
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
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
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
		enterRule(_localctx, 34, RULE_selfConstructor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(Identifier);
			setState(309);
			match(LParen);
			setState(310);
			match(RParen);
			setState(311);
			suite();
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
			return precpred(_ctx, 18);
		case 1:
			return precpred(_ctx, 17);
		case 2:
			return precpred(_ctx, 16);
		case 3:
			return precpred(_ctx, 15);
		case 4:
			return precpred(_ctx, 14);
		case 5:
			return precpred(_ctx, 13);
		case 6:
			return precpred(_ctx, 12);
		case 7:
			return precpred(_ctx, 11);
		case 8:
			return precpred(_ctx, 10);
		case 9:
			return precpred(_ctx, 9);
		case 10:
			return precpred(_ctx, 8);
		case 11:
			return precpred(_ctx, 24);
		case 12:
			return precpred(_ctx, 22);
		case 13:
			return precpred(_ctx, 21);
		case 14:
			return precpred(_ctx, 20);
		case 15:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3@\u013c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2.\n\2\f\2\16\2\61\13\2\3\2\3"+
		"\2\3\3\3\3\7\3\67\n\3\f\3\16\3:\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4P\n\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\\\n\4\3\4\3\4\5\4`\n\4\3\4\3\4\5\4d\n\4"+
		"\3\4\3\4\3\4\3\4\5\4j\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4r\n\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\6\5\u0082\n\5\r\5\16\5\u0083"+
		"\3\5\3\5\7\5\u0088\n\5\f\5\16\5\u008b\13\5\3\5\3\5\3\5\3\5\5\5\u0091\n"+
		"\5\3\5\3\5\3\5\3\5\5\5\u0097\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5"+
		"\5\u00c5\n\5\3\5\3\5\3\5\3\5\3\5\5\5\u00cc\n\5\3\5\3\5\3\5\3\5\6\5\u00d2"+
		"\n\5\r\5\16\5\u00d3\7\5\u00d6\n\5\f\5\16\5\u00d9\13\5\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\7\b\u00eb\n\b\f\b\16"+
		"\b\u00ee\13\b\3\t\3\t\3\t\5\t\u00f3\n\t\3\n\3\n\3\n\7\n\u00f8\n\n\f\n"+
		"\16\n\u00fb\13\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\7\r"+
		"\u0109\n\r\f\r\16\r\u010c\13\r\3\r\5\r\u010f\n\r\3\16\3\16\3\16\3\17\3"+
		"\17\5\17\u0116\n\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\7\21\u0120"+
		"\n\21\f\21\16\21\u0123\13\21\3\21\5\21\u0126\n\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\7\22\u0130\n\22\f\22\16\22\u0133\13\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\2\3\b\24\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$\2\13\6\2\3\4\20\20\25\25\30\31\3\2\5\7\3\2\3\4\3\2\21\22"+
		"\4\2\b\t\13\f\4\2\n\n\r\r\3\2\30\31\5\2--/\60;<\4\2*,==\2\u0167\2/\3\2"+
		"\2\2\4\64\3\2\2\2\6q\3\2\2\2\b\u0096\3\2\2\2\n\u00da\3\2\2\2\f\u00dc\3"+
		"\2\2\2\16\u00e6\3\2\2\2\20\u00ef\3\2\2\2\22\u00f4\3\2\2\2\24\u00fc\3\2"+
		"\2\2\26\u00fe\3\2\2\2\30\u010e\3\2\2\2\32\u0110\3\2\2\2\34\u0115\3\2\2"+
		"\2\36\u0117\3\2\2\2 \u0125\3\2\2\2\"\u0127\3\2\2\2$\u0136\3\2\2\2&.\5"+
		"\26\f\2\'(\5\"\22\2()\7 \2\2).\3\2\2\2*+\5\16\b\2+,\7 \2\2,.\3\2\2\2-"+
		"&\3\2\2\2-\'\3\2\2\2-*\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\62"+
		"\3\2\2\2\61/\3\2\2\2\62\63\7\2\2\3\63\3\3\2\2\2\648\7!\2\2\65\67\5\6\4"+
		"\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29;\3\2\2\2:8\3\2\2\2"+
		";<\7\"\2\2<\5\3\2\2\2=r\5\4\3\2>?\5\b\5\2?@\7 \2\2@r\3\2\2\2AB\5\16\b"+
		"\2BC\7 \2\2Cr\3\2\2\2Dr\5\26\f\2EF\5\"\22\2FG\7 \2\2Gr\3\2\2\2HI\7\61"+
		"\2\2IJ\7\35\2\2JK\5\b\5\2KL\7\36\2\2LO\5\6\4\2MN\7\62\2\2NP\5\6\4\2OM"+
		"\3\2\2\2OP\3\2\2\2Pr\3\2\2\2QR\7\64\2\2RS\7\35\2\2ST\5\b\5\2TU\7\36\2"+
		"\2UV\5\6\4\2Vr\3\2\2\2WX\7\63\2\2X[\7\35\2\2Y\\\5\b\5\2Z\\\5\16\b\2[Y"+
		"\3\2\2\2[Z\3\2\2\2[\\\3\2\2\2\\]\3\2\2\2]_\7 \2\2^`\5\b\5\2_^\3\2\2\2"+
		"_`\3\2\2\2`a\3\2\2\2ac\7 \2\2bd\5\b\5\2cb\3\2\2\2cd\3\2\2\2de\3\2\2\2"+
		"ef\7\36\2\2fr\5\6\4\2gi\7\67\2\2hj\5\b\5\2ih\3\2\2\2ij\3\2\2\2jk\3\2\2"+
		"\2kr\7 \2\2lm\7\65\2\2mr\7 \2\2no\7\66\2\2or\7 \2\2pr\7 \2\2q=\3\2\2\2"+
		"q>\3\2\2\2qA\3\2\2\2qD\3\2\2\2qE\3\2\2\2qH\3\2\2\2qQ\3\2\2\2qW\3\2\2\2"+
		"qg\3\2\2\2ql\3\2\2\2qn\3\2\2\2qp\3\2\2\2r\7\3\2\2\2st\b\5\1\2tu\7\35\2"+
		"\2uv\5\b\5\2vw\7\36\2\2w\u0097\3\2\2\2x\u0097\5\36\20\2yz\t\2\2\2z\u0097"+
		"\5\b\5\25{|\78\2\2|\u0081\5\24\13\2}~\7\33\2\2~\177\5\b\5\2\177\u0080"+
		"\7\34\2\2\u0080\u0082\3\2\2\2\u0081}\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0089\3\2\2\2\u0085\u0086\7\33"+
		"\2\2\u0086\u0088\7\34\2\2\u0087\u0085\3\2\2\2\u0088\u008b\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0097\3\2\2\2\u008b\u0089\3\2"+
		"\2\2\u008c\u008d\78\2\2\u008d\u0090\5\24\13\2\u008e\u008f\7\35\2\2\u008f"+
		"\u0091\7\36\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0097\3"+
		"\2\2\2\u0092\u0097\5\f\7\2\u0093\u0097\7:\2\2\u0094\u0097\5\n\6\2\u0095"+
		"\u0097\7=\2\2\u0096s\3\2\2\2\u0096x\3\2\2\2\u0096y\3\2\2\2\u0096{\3\2"+
		"\2\2\u0096\u008c\3\2\2\2\u0096\u0092\3\2\2\2\u0096\u0093\3\2\2\2\u0096"+
		"\u0094\3\2\2\2\u0096\u0095\3\2\2\2\u0097\u00d7\3\2\2\2\u0098\u0099\f\24"+
		"\2\2\u0099\u009a\t\3\2\2\u009a\u00d6\5\b\5\25\u009b\u009c\f\23\2\2\u009c"+
		"\u009d\t\4\2\2\u009d\u00d6\5\b\5\24\u009e\u009f\f\22\2\2\u009f\u00a0\t"+
		"\5\2\2\u00a0\u00d6\5\b\5\23\u00a1\u00a2\f\21\2\2\u00a2\u00a3\t\6\2\2\u00a3"+
		"\u00d6\5\b\5\22\u00a4\u00a5\f\20\2\2\u00a5\u00a6\t\7\2\2\u00a6\u00d6\5"+
		"\b\5\21\u00a7\u00a8\f\17\2\2\u00a8\u00a9\7\23\2\2\u00a9\u00d6\5\b\5\20"+
		"\u00aa\u00ab\f\16\2\2\u00ab\u00ac\7\26\2\2\u00ac\u00d6\5\b\5\17\u00ad"+
		"\u00ae\f\r\2\2\u00ae\u00af\7\24\2\2\u00af\u00d6\5\b\5\16\u00b0\u00b1\f"+
		"\f\2\2\u00b1\u00b2\7\16\2\2\u00b2\u00d6\5\b\5\r\u00b3\u00b4\f\13\2\2\u00b4"+
		"\u00b5\7\17\2\2\u00b5\u00d6\5\b\5\f\u00b6\u00b7\f\n\2\2\u00b7\u00b8\7"+
		"\27\2\2\u00b8\u00d6\5\b\5\n\u00b9\u00ba\f\32\2\2\u00ba\u00d6\t\b\2\2\u00bb"+
		"\u00bc\f\30\2\2\u00bc\u00bd\7\33\2\2\u00bd\u00be\5\b\5\2\u00be\u00bf\7"+
		"\34\2\2\u00bf\u00d6\3\2\2\2\u00c0\u00c1\f\27\2\2\u00c1\u00c4\7\32\2\2"+
		"\u00c2\u00c5\7=\2\2\u00c3\u00c5\5\36\20\2\u00c4\u00c2\3\2\2\2\u00c4\u00c3"+
		"\3\2\2\2\u00c5\u00d6\3\2\2\2\u00c6\u00c7\f\26\2\2\u00c7\u00c8\7\32\2\2"+
		"\u00c8\u00cb\7\35\2\2\u00c9\u00cc\7=\2\2\u00ca\u00cc\5\36\20\2\u00cb\u00c9"+
		"\3\2\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00d6\7\36\2\2"+
		"\u00ce\u00d1\f\t\2\2\u00cf\u00d0\7\37\2\2\u00d0\u00d2\5\b\5\2\u00d1\u00cf"+
		"\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4"+
		"\u00d6\3\2\2\2\u00d5\u0098\3\2\2\2\u00d5\u009b\3\2\2\2\u00d5\u009e\3\2"+
		"\2\2\u00d5\u00a1\3\2\2\2\u00d5\u00a4\3\2\2\2\u00d5\u00a7\3\2\2\2\u00d5"+
		"\u00aa\3\2\2\2\u00d5\u00ad\3\2\2\2\u00d5\u00b0\3\2\2\2\u00d5\u00b3\3\2"+
		"\2\2\u00d5\u00b6\3\2\2\2\u00d5\u00b9\3\2\2\2\u00d5\u00bb\3\2\2\2\u00d5"+
		"\u00c0\3\2\2\2\u00d5\u00c6\3\2\2\2\u00d5\u00ce\3\2\2\2\u00d6\u00d9\3\2"+
		"\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\t\3\2\2\2\u00d9\u00d7"+
		"\3\2\2\2\u00da\u00db\t\t\2\2\u00db\13\3\2\2\2\u00dc\u00dd\7(\2\2\u00dd"+
		"\u00de\7\35\2\2\u00de\u00df\5\30\r\2\u00df\u00e0\7\36\2\2\u00e0\u00e1"+
		"\7)\2\2\u00e1\u00e2\5\4\3\2\u00e2\u00e3\7\35\2\2\u00e3\u00e4\5 \21\2\u00e4"+
		"\u00e5\7\36\2\2\u00e5\r\3\2\2\2\u00e6\u00e7\5\22\n\2\u00e7\u00ec\5\20"+
		"\t\2\u00e8\u00e9\7\37\2\2\u00e9\u00eb\5\20\t\2\u00ea\u00e8\3\2\2\2\u00eb"+
		"\u00ee\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\17\3\2\2"+
		"\2\u00ee\u00ec\3\2\2\2\u00ef\u00f2\7=\2\2\u00f0\u00f1\7\27\2\2\u00f1\u00f3"+
		"\5\b\5\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\21\3\2\2\2\u00f4"+
		"\u00f9\5\24\13\2\u00f5\u00f6\7\33\2\2\u00f6\u00f8\7\34\2\2\u00f7\u00f5"+
		"\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa"+
		"\23\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u00fd\t\n\2\2\u00fd\25\3\2\2\2\u00fe"+
		"\u00ff\5\34\17\2\u00ff\u0100\7=\2\2\u0100\u0101\7\35\2\2\u0101\u0102\5"+
		"\30\r\2\u0102\u0103\7\36\2\2\u0103\u0104\5\4\3\2\u0104\27\3\2\2\2\u0105"+
		"\u010a\5\32\16\2\u0106\u0107\7\37\2\2\u0107\u0109\5\32\16\2\u0108\u0106"+
		"\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		"\u010f\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u010f\3\2\2\2\u010e\u0105\3\2"+
		"\2\2\u010e\u010d\3\2\2\2\u010f\31\3\2\2\2\u0110\u0111\5\22\n\2\u0111\u0112"+
		"\7=\2\2\u0112\33\3\2\2\2\u0113\u0116\5\22\n\2\u0114\u0116\7.\2\2\u0115"+
		"\u0113\3\2\2\2\u0115\u0114\3\2\2\2\u0116\35\3\2\2\2\u0117\u0118\7=\2\2"+
		"\u0118\u0119\7\35\2\2\u0119\u011a\5 \21\2\u011a\u011b\7\36\2\2\u011b\37"+
		"\3\2\2\2\u011c\u0121\5\b\5\2\u011d\u011e\7\37\2\2\u011e\u0120\5\b\5\2"+
		"\u011f\u011d\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122"+
		"\3\2\2\2\u0122\u0126\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u0126\3\2\2\2\u0125"+
		"\u011c\3\2\2\2\u0125\u0124\3\2\2\2\u0126!\3\2\2\2\u0127\u0128\79\2\2\u0128"+
		"\u0129\7=\2\2\u0129\u0131\7!\2\2\u012a\u012b\5\16\b\2\u012b\u012c\7 \2"+
		"\2\u012c\u0130\3\2\2\2\u012d\u0130\5$\23\2\u012e\u0130\5\26\f\2\u012f"+
		"\u012a\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u012e\3\2\2\2\u0130\u0133\3\2"+
		"\2\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0134\3\2\2\2\u0133"+
		"\u0131\3\2\2\2\u0134\u0135\7\"\2\2\u0135#\3\2\2\2\u0136\u0137\7=\2\2\u0137"+
		"\u0138\7\35\2\2\u0138\u0139\7\36\2\2\u0139\u013a\5\4\3\2\u013a%\3\2\2"+
		"\2\36-/8O[_ciq\u0083\u0089\u0090\u0096\u00c4\u00cb\u00d3\u00d5\u00d7\u00ec"+
		"\u00f2\u00f9\u010a\u010e\u0115\u0121\u0125\u012f\u0131";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}