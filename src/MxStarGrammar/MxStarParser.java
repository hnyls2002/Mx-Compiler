// Generated from MxStarParser.g4 by ANTLR 4.7.1
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
		SingleQuote=36, DoubleQuote=37, RightArrow=38, IntType=39, BoolType=40, 
		StringType=41, Null=42, Void=43, True=44, False=45, If=46, Else=47, For=48, 
		While=49, Break=50, Continue=51, Return=52, New=53, Class=54, This=55, 
		IntLiteral=56, StringLiteral=57, Identifier=58, LineComment=59, BlockComment=60, 
		WhiteSpace=61;
	public static final int
		RULE_program = 0, RULE_suite = 1, RULE_statement = 2, RULE_ifStatement = 3, 
		RULE_whileStatement = 4, RULE_forStatement = 5, RULE_returnStatement = 6, 
		RULE_breakStatement = 7, RULE_continueStatement = 8, RULE_emptyStatement = 9, 
		RULE_expression = 10, RULE_literalExpression = 11, RULE_creatorExpression = 12, 
		RULE_lambdaExpression = 13, RULE_varDeclarStatement = 14, RULE_varDeclar = 15, 
		RULE_varSingleDeclar = 16, RULE_typeName = 17, RULE_typeNameUnit = 18, 
		RULE_funcDeclarStatement = 19, RULE_parameterList = 20, RULE_parameter = 21, 
		RULE_returnType = 22, RULE_funcCall = 23, RULE_argumentList = 24, RULE_classDeclarStatement = 25, 
		RULE_classDeclar = 26, RULE_selfConstructor = 27;
	public static final String[] ruleNames = {
		"program", "suite", "statement", "ifStatement", "whileStatement", "forStatement", 
		"returnStatement", "breakStatement", "continueStatement", "emptyStatement", 
		"expression", "literalExpression", "creatorExpression", "lambdaExpression", 
		"varDeclarStatement", "varDeclar", "varSingleDeclar", "typeName", "typeNameUnit", 
		"funcDeclarStatement", "parameterList", "parameter", "returnType", "funcCall", 
		"argumentList", "classDeclarStatement", "classDeclar", "selfConstructor"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'<'", "'=='", "'>='", 
		"'<='", "'!='", "'&&'", "'||'", "'!'", "'>>'", "'<<'", "'&'", "'|'", "'~'", 
		"'^'", "'='", "'++'", "'--'", "'.'", "'['", "']'", "'('", "')'", "','", 
		"';'", "'{'", "'}'", "'//'", "'/*'", "'*/'", "'''", "'\"'", "'->'", "'int'", 
		"'bool'", "'string'", "'null'", "'void'", "'true'", "'false'", "'if'", 
		"'else'", "'for'", "'while'", "'break'", "'continue'", "'return'", "'new'", 
		"'class'", "'this'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "Add", "Sub", "Mul", "Div", "Mod", "Greater", "Less", "Equal", "GreaterEqual", 
		"LessEqual", "NotEqual", "LogicAnd", "LogicOr", "LogicNot", "ShiftRight", 
		"ShiftLeft", "BitAnd", "BitOr", "BitNot", "BitXor", "Assign", "Inc", "Dec", 
		"Dot", "LBracket", "RBracket", "LParen", "RParen", "Comma", "Semicolon", 
		"LBrace", "RBrace", "DoubleSlash", "SlashStar", "StarSlash", "SingleQuote", 
		"DoubleQuote", "RightArrow", "IntType", "BoolType", "StringType", "Null", 
		"Void", "True", "False", "If", "Else", "For", "While", "Break", "Continue", 
		"Return", "New", "Class", "This", "IntLiteral", "StringLiteral", "Identifier", 
		"LineComment", "BlockComment", "WhiteSpace"
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntType) | (1L << BoolType) | (1L << StringType) | (1L << Void) | (1L << Class) | (1L << Identifier))) != 0)) {
				{
				setState(59);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(56);
					funcDeclarStatement();
					}
					break;
				case 2:
					{
					setState(57);
					classDeclarStatement();
					}
					break;
				case 3:
					{
					setState(58);
					varDeclarStatement();
					}
					break;
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
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
		public List<SuiteContext> suite() {
			return getRuleContexts(SuiteContext.class);
		}
		public SuiteContext suite(int i) {
			return getRuleContext(SuiteContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitSuite(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_suite);
		int _la;
		try {
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBrace:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				match(LBrace);
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LogicNot) | (1L << BitNot) | (1L << Inc) | (1L << Dec) | (1L << LBracket) | (1L << LParen) | (1L << Semicolon) | (1L << LBrace) | (1L << IntType) | (1L << BoolType) | (1L << StringType) | (1L << Null) | (1L << Void) | (1L << True) | (1L << False) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << Class) | (1L << This) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
					{
					setState(69);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						setState(67);
						statement();
						}
						break;
					case 2:
						{
						setState(68);
						suite();
						}
						break;
					}
					}
					setState(73);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(74);
				match(RBrace);
				}
				break;
			case Add:
			case Sub:
			case LogicNot:
			case BitNot:
			case Inc:
			case Dec:
			case LBracket:
			case LParen:
			case Semicolon:
			case IntType:
			case BoolType:
			case StringType:
			case Null:
			case Void:
			case True:
			case False:
			case If:
			case For:
			case While:
			case Break:
			case Continue:
			case Return:
			case New:
			case Class:
			case This:
			case IntLiteral:
			case StringLiteral:
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				statement();
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

	public static class StatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
		public VarDeclarStatementContext varDeclarStatement() {
			return getRuleContext(VarDeclarStatementContext.class,0);
		}
		public FuncDeclarStatementContext funcDeclarStatement() {
			return getRuleContext(FuncDeclarStatementContext.class,0);
		}
		public ClassDeclarStatementContext classDeclarStatement() {
			return getRuleContext(ClassDeclarStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public EmptyStatementContext emptyStatement() {
			return getRuleContext(EmptyStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				expression(0);
				setState(79);
				match(Semicolon);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				varDeclarStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(82);
				funcDeclarStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(83);
				classDeclarStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(84);
				ifStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(85);
				whileStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(86);
				forStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(87);
				returnStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(88);
				breakStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(89);
				continueStatement();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(90);
				emptyStatement();
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

	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(MxStarParser.If, 0); }
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public List<SuiteContext> suite() {
			return getRuleContexts(SuiteContext.class);
		}
		public SuiteContext suite(int i) {
			return getRuleContext(SuiteContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxStarParser.Else, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(If);
			setState(94);
			match(LParen);
			setState(95);
			expression(0);
			setState(96);
			match(RParen);
			setState(97);
			suite();
			setState(100);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(98);
				match(Else);
				setState(99);
				suite();
				}
				break;
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

	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode While() { return getToken(MxStarParser.While, 0); }
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitWhileStatement(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(While);
			setState(103);
			match(LParen);
			setState(104);
			expression(0);
			setState(105);
			match(RParen);
			setState(106);
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

	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode For() { return getToken(MxStarParser.For, 0); }
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public List<TerminalNode> Semicolon() { return getTokens(MxStarParser.Semicolon); }
		public TerminalNode Semicolon(int i) {
			return getToken(MxStarParser.Semicolon, i);
		}
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
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
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitForStatement(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_forStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(For);
			setState(109);
			match(LParen);
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(110);
				expression(0);
				}
				break;
			case 2:
				{
				setState(111);
				varDeclar();
				}
				break;
			}
			setState(114);
			match(Semicolon);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LogicNot) | (1L << BitNot) | (1L << Inc) | (1L << Dec) | (1L << LBracket) | (1L << LParen) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
				{
				setState(115);
				expression(0);
				}
			}

			setState(118);
			match(Semicolon);
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LogicNot) | (1L << BitNot) | (1L << Inc) | (1L << Dec) | (1L << LBracket) | (1L << LParen) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
				{
				setState(119);
				expression(0);
				}
			}

			setState(122);
			match(RParen);
			setState(123);
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

	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(MxStarParser.Return, 0); }
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitReturnStatement(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(Return);
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << LogicNot) | (1L << BitNot) | (1L << Inc) | (1L << Dec) | (1L << LBracket) | (1L << LParen) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
				{
				setState(126);
				expression(0);
				}
			}

			setState(129);
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

	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(MxStarParser.Break, 0); }
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitBreakStatement(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(Break);
			setState(132);
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

	public static class ContinueStatementContext extends ParserRuleContext {
		public TerminalNode Continue() { return getToken(MxStarParser.Continue, 0); }
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitContinueStatement(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(Continue);
			setState(135);
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

	public static class EmptyStatementContext extends ParserRuleContext {
		public TerminalNode Semicolon() { return getToken(MxStarParser.Semicolon, 0); }
		public EmptyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitEmptyStatement(this);
		}
	}

	public final EmptyStatementContext emptyStatement() throws RecognitionException {
		EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_emptyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
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

	public static class ExpressionContext extends ParserRuleContext {
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Inc() { return getToken(MxStarParser.Inc, 0); }
		public TerminalNode Dec() { return getToken(MxStarParser.Dec, 0); }
		public TerminalNode Add() { return getToken(MxStarParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxStarParser.Sub, 0); }
		public TerminalNode LogicNot() { return getToken(MxStarParser.LogicNot, 0); }
		public TerminalNode BitNot() { return getToken(MxStarParser.BitNot, 0); }
		public CreatorExpressionContext creatorExpression() {
			return getRuleContext(CreatorExpressionContext.class,0);
		}
		public LambdaExpressionContext lambdaExpression() {
			return getRuleContext(LambdaExpressionContext.class,0);
		}
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public TerminalNode This() { return getToken(MxStarParser.This, 0); }
		public LiteralExpressionContext literalExpression() {
			return getRuleContext(LiteralExpressionContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxStarParser.Identifier, 0); }
		public TerminalNode Mul() { return getToken(MxStarParser.Mul, 0); }
		public TerminalNode Div() { return getToken(MxStarParser.Div, 0); }
		public TerminalNode Mod() { return getToken(MxStarParser.Mod, 0); }
		public TerminalNode ShiftLeft() { return getToken(MxStarParser.ShiftLeft, 0); }
		public TerminalNode ShiftRight() { return getToken(MxStarParser.ShiftRight, 0); }
		public TerminalNode Less() { return getToken(MxStarParser.Less, 0); }
		public TerminalNode LessEqual() { return getToken(MxStarParser.LessEqual, 0); }
		public TerminalNode Greater() { return getToken(MxStarParser.Greater, 0); }
		public TerminalNode GreaterEqual() { return getToken(MxStarParser.GreaterEqual, 0); }
		public TerminalNode Equal() { return getToken(MxStarParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(MxStarParser.NotEqual, 0); }
		public TerminalNode BitAnd() { return getToken(MxStarParser.BitAnd, 0); }
		public TerminalNode BitXor() { return getToken(MxStarParser.BitXor, 0); }
		public TerminalNode BitOr() { return getToken(MxStarParser.BitOr, 0); }
		public TerminalNode LogicAnd() { return getToken(MxStarParser.LogicAnd, 0); }
		public TerminalNode LogicOr() { return getToken(MxStarParser.LogicOr, 0); }
		public TerminalNode Assign() { return getToken(MxStarParser.Assign, 0); }
		public TerminalNode LBracket() { return getToken(MxStarParser.LBracket, 0); }
		public TerminalNode RBracket() { return getToken(MxStarParser.RBracket, 0); }
		public TerminalNode Dot() { return getToken(MxStarParser.Dot, 0); }
		public List<TerminalNode> Comma() { return getTokens(MxStarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxStarParser.Comma, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitExpression(this);
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
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(140);
				funcCall();
				}
				break;
			case 2:
				{
				setState(141);
				_la = _input.LA(1);
				if ( !(_la==Inc || _la==Dec) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(142);
				expression(22);
				}
				break;
			case 3:
				{
				setState(143);
				_la = _input.LA(1);
				if ( !(_la==Add || _la==Sub) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(144);
				expression(21);
				}
				break;
			case 4:
				{
				setState(145);
				match(LogicNot);
				setState(146);
				expression(20);
				}
				break;
			case 5:
				{
				setState(147);
				match(BitNot);
				setState(148);
				expression(19);
				}
				break;
			case 6:
				{
				setState(149);
				creatorExpression();
				}
				break;
			case 7:
				{
				setState(150);
				lambdaExpression();
				}
				break;
			case 8:
				{
				setState(151);
				match(LParen);
				setState(152);
				expression(0);
				setState(153);
				match(RParen);
				}
				break;
			case 9:
				{
				setState(155);
				match(This);
				}
				break;
			case 10:
				{
				setState(156);
				literalExpression();
				}
				break;
			case 11:
				{
				setState(157);
				match(Identifier);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(223);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(221);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(160);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(161);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(162);
						expression(19);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(163);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(164);
						_la = _input.LA(1);
						if ( !(_la==Add || _la==Sub) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(165);
						expression(18);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(166);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(167);
						_la = _input.LA(1);
						if ( !(_la==ShiftRight || _la==ShiftLeft) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(168);
						expression(17);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(169);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(170);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Greater) | (1L << Less) | (1L << GreaterEqual) | (1L << LessEqual))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(171);
						expression(16);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(172);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(173);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==NotEqual) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(174);
						expression(15);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(175);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(176);
						match(BitAnd);
						setState(177);
						expression(14);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(178);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(179);
						match(BitXor);
						setState(180);
						expression(13);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(182);
						match(BitOr);
						setState(183);
						expression(12);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(184);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(185);
						match(LogicAnd);
						setState(186);
						expression(11);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(187);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(188);
						match(LogicOr);
						setState(189);
						expression(10);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(190);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(191);
						match(Assign);
						setState(192);
						expression(8);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(193);
						if (!(precpred(_ctx, 27))) throw new FailedPredicateException(this, "precpred(_ctx, 27)");
						setState(194);
						_la = _input.LA(1);
						if ( !(_la==Inc || _la==Dec) ) {
						_errHandler.recoverInline(this);
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
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(195);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						{
						setState(196);
						match(LBracket);
						setState(197);
						expression(0);
						setState(198);
						match(RBracket);
						}
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(200);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(201);
						match(Dot);
						setState(204);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
						case 1:
							{
							setState(202);
							match(Identifier);
							}
							break;
						case 2:
							{
							setState(203);
							funcCall();
							}
							break;
						}
						}
						break;
					case 15:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(206);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(207);
						match(Dot);
						setState(208);
						match(LParen);
						setState(211);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
						case 1:
							{
							setState(209);
							match(Identifier);
							}
							break;
						case 2:
							{
							setState(210);
							funcCall();
							}
							break;
						}
						setState(213);
						match(RParen);
						}
						break;
					case 16:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(214);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(217); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(215);
								match(Comma);
								setState(216);
								expression(0);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(219); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					}
					} 
				}
				setState(225);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitLiteralExpression(this);
		}
	}

	public final LiteralExpressionContext literalExpression() throws RecognitionException {
		LiteralExpressionContext _localctx = new LiteralExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_literalExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
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
		public TerminalNode LParen() { return getToken(MxStarParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxStarParser.RParen, 0); }
		public CreatorExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creatorExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterCreatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitCreatorExpression(this);
		}
	}

	public final CreatorExpressionContext creatorExpression() throws RecognitionException {
		CreatorExpressionContext _localctx = new CreatorExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_creatorExpression);
		try {
			int _alt;
			setState(265);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				match(New);
				setState(229);
				typeNameUnit();
				setState(234); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(230);
						match(LBracket);
						setState(231);
						expression(0);
						setState(232);
						match(RBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(236); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(240);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(238);
					match(LParen);
					setState(239);
					match(RParen);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(242);
				match(New);
				setState(243);
				typeNameUnit();
				setState(248); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(244);
						match(LBracket);
						setState(245);
						expression(0);
						setState(246);
						match(RBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(250); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(256);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(252);
						match(LBracket);
						setState(253);
						match(RBracket);
						}
						} 
					}
					setState(258);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(259);
				match(New);
				setState(260);
				typeNameUnit();
				setState(263);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(261);
					match(LParen);
					setState(262);
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
		public TerminalNode LBracket() { return getToken(MxStarParser.LBracket, 0); }
		public TerminalNode BitAnd() { return getToken(MxStarParser.BitAnd, 0); }
		public TerminalNode RBracket() { return getToken(MxStarParser.RBracket, 0); }
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterLambdaExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitLambdaExpression(this);
		}
	}

	public final LambdaExpressionContext lambdaExpression() throws RecognitionException {
		LambdaExpressionContext _localctx = new LambdaExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_lambdaExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			match(LBracket);
			setState(268);
			match(BitAnd);
			setState(269);
			match(RBracket);
			setState(270);
			match(LParen);
			setState(271);
			parameterList();
			setState(272);
			match(RParen);
			setState(273);
			match(RightArrow);
			setState(274);
			suite();
			setState(275);
			match(LParen);
			setState(276);
			argumentList();
			setState(277);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterVarDeclarStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitVarDeclarStatement(this);
		}
	}

	public final VarDeclarStatementContext varDeclarStatement() throws RecognitionException {
		VarDeclarStatementContext _localctx = new VarDeclarStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_varDeclarStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			varDeclar();
			setState(280);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterVarDeclar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitVarDeclar(this);
		}
	}

	public final VarDeclarContext varDeclar() throws RecognitionException {
		VarDeclarContext _localctx = new VarDeclarContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_varDeclar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			typeName();
			setState(283);
			varSingleDeclar();
			setState(288);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(284);
				match(Comma);
				setState(285);
				varSingleDeclar();
				}
				}
				setState(290);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterVarSingleDeclar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitVarSingleDeclar(this);
		}
	}

	public final VarSingleDeclarContext varSingleDeclar() throws RecognitionException {
		VarSingleDeclarContext _localctx = new VarSingleDeclarContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_varSingleDeclar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(Identifier);
			setState(294);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(292);
				match(Assign);
				setState(293);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitTypeName(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			typeNameUnit();
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBracket) {
				{
				{
				setState(297);
				match(LBracket);
				setState(298);
				match(RBracket);
				}
				}
				setState(303);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterTypeNameUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitTypeNameUnit(this);
		}
	}

	public final TypeNameUnitContext typeNameUnit() throws RecognitionException {
		TypeNameUnitContext _localctx = new TypeNameUnitContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_typeNameUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
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
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public FuncDeclarStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDeclarStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterFuncDeclarStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitFuncDeclarStatement(this);
		}
	}

	public final FuncDeclarStatementContext funcDeclarStatement() throws RecognitionException {
		FuncDeclarStatementContext _localctx = new FuncDeclarStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_funcDeclarStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			returnType();
			setState(307);
			match(Identifier);
			setState(308);
			match(LParen);
			setState(309);
			parameterList();
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitParameterList(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_parameterList);
		int _la;
		try {
			setState(322);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntType:
			case BoolType:
			case StringType:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(313);
				parameter();
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(314);
					match(Comma);
					setState(315);
					parameter();
					}
					}
					setState(320);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitParameter(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			typeName();
			setState(325);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitReturnType(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_returnType);
		try {
			setState(329);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntType:
			case BoolType:
			case StringType:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(327);
				typeName();
				}
				break;
			case Void:
				enterOuterAlt(_localctx, 2);
				{
				setState(328);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitFuncCall(this);
		}
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_funcCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			match(Identifier);
			setState(332);
			match(LParen);
			setState(333);
			argumentList();
			setState(334);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitArgumentList(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_argumentList);
		int _la;
		try {
			setState(345);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Add:
			case Sub:
			case LogicNot:
			case BitNot:
			case Inc:
			case Dec:
			case LBracket:
			case LParen:
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
				setState(336);
				expression(0);
				setState(341);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(337);
					match(Comma);
					setState(338);
					expression(0);
					}
					}
					setState(343);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterClassDeclarStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitClassDeclarStatement(this);
		}
	}

	public final ClassDeclarStatementContext classDeclarStatement() throws RecognitionException {
		ClassDeclarStatementContext _localctx = new ClassDeclarStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_classDeclarStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347);
			classDeclar();
			setState(348);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterClassDeclar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitClassDeclar(this);
		}
	}

	public final ClassDeclarContext classDeclar() throws RecognitionException {
		ClassDeclarContext _localctx = new ClassDeclarContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_classDeclar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(Class);
			setState(351);
			match(Identifier);
			setState(352);
			match(LBrace);
			setState(358);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntType) | (1L << BoolType) | (1L << StringType) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(356);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(353);
					varDeclarStatement();
					}
					break;
				case 2:
					{
					setState(354);
					selfConstructor();
					}
					break;
				case 3:
					{
					setState(355);
					funcDeclarStatement();
					}
					break;
				}
				}
				setState(360);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(361);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).enterSelfConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxStarParserListener ) ((MxStarParserListener)listener).exitSelfConstructor(this);
		}
	}

	public final SelfConstructorContext selfConstructor() throws RecognitionException {
		SelfConstructorContext _localctx = new SelfConstructorContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_selfConstructor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
			match(Identifier);
			setState(364);
			match(LParen);
			setState(365);
			match(RParen);
			setState(366);
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
		case 10:
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
			return precpred(_ctx, 27);
		case 12:
			return precpred(_ctx, 25);
		case 13:
			return precpred(_ctx, 24);
		case 14:
			return precpred(_ctx, 23);
		case 15:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3?\u0173\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\2\7\2>\n\2\f\2\16\2"+
		"A\13\2\3\2\3\2\3\3\3\3\3\3\7\3H\n\3\f\3\16\3K\13\3\3\3\3\3\5\3O\n\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4^\n\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\5\5g\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5"+
		"\7s\n\7\3\7\3\7\5\7w\n\7\3\7\3\7\5\7{\n\7\3\7\3\7\3\7\3\b\3\b\5\b\u0082"+
		"\n\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00a1\n\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00cf\n\f\3\f\3\f\3\f\3\f\3\f\5\f"+
		"\u00d6\n\f\3\f\3\f\3\f\3\f\6\f\u00dc\n\f\r\f\16\f\u00dd\7\f\u00e0\n\f"+
		"\f\f\16\f\u00e3\13\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\6\16\u00ed"+
		"\n\16\r\16\16\16\u00ee\3\16\3\16\5\16\u00f3\n\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\6\16\u00fb\n\16\r\16\16\16\u00fc\3\16\3\16\7\16\u0101\n\16\f"+
		"\16\16\16\u0104\13\16\3\16\3\16\3\16\3\16\5\16\u010a\n\16\5\16\u010c\n"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\7\21\u0121\n\21\f\21\16\21\u0124\13\21\3"+
		"\22\3\22\3\22\5\22\u0129\n\22\3\23\3\23\3\23\7\23\u012e\n\23\f\23\16\23"+
		"\u0131\13\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3"+
		"\26\7\26\u013f\n\26\f\26\16\26\u0142\13\26\3\26\5\26\u0145\n\26\3\27\3"+
		"\27\3\27\3\30\3\30\5\30\u014c\n\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\7\32\u0156\n\32\f\32\16\32\u0159\13\32\3\32\5\32\u015c\n\32\3\33"+
		"\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u0167\n\34\f\34\16\34\u016a"+
		"\13\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\2\3\26\36\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668\2\n\3\2\30\31\3\2\3\4\3"+
		"\2\5\7\3\2\21\22\4\2\b\t\13\f\4\2\n\n\r\r\5\2,,./:;\4\2)+<<\2\u019b\2"+
		"?\3\2\2\2\4N\3\2\2\2\6]\3\2\2\2\b_\3\2\2\2\nh\3\2\2\2\fn\3\2\2\2\16\177"+
		"\3\2\2\2\20\u0085\3\2\2\2\22\u0088\3\2\2\2\24\u008b\3\2\2\2\26\u00a0\3"+
		"\2\2\2\30\u00e4\3\2\2\2\32\u010b\3\2\2\2\34\u010d\3\2\2\2\36\u0119\3\2"+
		"\2\2 \u011c\3\2\2\2\"\u0125\3\2\2\2$\u012a\3\2\2\2&\u0132\3\2\2\2(\u0134"+
		"\3\2\2\2*\u0144\3\2\2\2,\u0146\3\2\2\2.\u014b\3\2\2\2\60\u014d\3\2\2\2"+
		"\62\u015b\3\2\2\2\64\u015d\3\2\2\2\66\u0160\3\2\2\28\u016d\3\2\2\2:>\5"+
		"(\25\2;>\5\64\33\2<>\5\36\20\2=:\3\2\2\2=;\3\2\2\2=<\3\2\2\2>A\3\2\2\2"+
		"?=\3\2\2\2?@\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\7\2\2\3C\3\3\2\2\2DI\7!\2\2"+
		"EH\5\6\4\2FH\5\4\3\2GE\3\2\2\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2"+
		"JL\3\2\2\2KI\3\2\2\2LO\7\"\2\2MO\5\6\4\2ND\3\2\2\2NM\3\2\2\2O\5\3\2\2"+
		"\2PQ\5\26\f\2QR\7 \2\2R^\3\2\2\2S^\5\36\20\2T^\5(\25\2U^\5\64\33\2V^\5"+
		"\b\5\2W^\5\n\6\2X^\5\f\7\2Y^\5\16\b\2Z^\5\20\t\2[^\5\22\n\2\\^\5\24\13"+
		"\2]P\3\2\2\2]S\3\2\2\2]T\3\2\2\2]U\3\2\2\2]V\3\2\2\2]W\3\2\2\2]X\3\2\2"+
		"\2]Y\3\2\2\2]Z\3\2\2\2][\3\2\2\2]\\\3\2\2\2^\7\3\2\2\2_`\7\60\2\2`a\7"+
		"\35\2\2ab\5\26\f\2bc\7\36\2\2cf\5\4\3\2de\7\61\2\2eg\5\4\3\2fd\3\2\2\2"+
		"fg\3\2\2\2g\t\3\2\2\2hi\7\63\2\2ij\7\35\2\2jk\5\26\f\2kl\7\36\2\2lm\5"+
		"\4\3\2m\13\3\2\2\2no\7\62\2\2or\7\35\2\2ps\5\26\f\2qs\5 \21\2rp\3\2\2"+
		"\2rq\3\2\2\2rs\3\2\2\2st\3\2\2\2tv\7 \2\2uw\5\26\f\2vu\3\2\2\2vw\3\2\2"+
		"\2wx\3\2\2\2xz\7 \2\2y{\5\26\f\2zy\3\2\2\2z{\3\2\2\2{|\3\2\2\2|}\7\36"+
		"\2\2}~\5\4\3\2~\r\3\2\2\2\177\u0081\7\66\2\2\u0080\u0082\5\26\f\2\u0081"+
		"\u0080\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\7 "+
		"\2\2\u0084\17\3\2\2\2\u0085\u0086\7\64\2\2\u0086\u0087\7 \2\2\u0087\21"+
		"\3\2\2\2\u0088\u0089\7\65\2\2\u0089\u008a\7 \2\2\u008a\23\3\2\2\2\u008b"+
		"\u008c\7 \2\2\u008c\25\3\2\2\2\u008d\u008e\b\f\1\2\u008e\u00a1\5\60\31"+
		"\2\u008f\u0090\t\2\2\2\u0090\u00a1\5\26\f\30\u0091\u0092\t\3\2\2\u0092"+
		"\u00a1\5\26\f\27\u0093\u0094\7\20\2\2\u0094\u00a1\5\26\f\26\u0095\u0096"+
		"\7\25\2\2\u0096\u00a1\5\26\f\25\u0097\u00a1\5\32\16\2\u0098\u00a1\5\34"+
		"\17\2\u0099\u009a\7\35\2\2\u009a\u009b\5\26\f\2\u009b\u009c\7\36\2\2\u009c"+
		"\u00a1\3\2\2\2\u009d\u00a1\79\2\2\u009e\u00a1\5\30\r\2\u009f\u00a1\7<"+
		"\2\2\u00a0\u008d\3\2\2\2\u00a0\u008f\3\2\2\2\u00a0\u0091\3\2\2\2\u00a0"+
		"\u0093\3\2\2\2\u00a0\u0095\3\2\2\2\u00a0\u0097\3\2\2\2\u00a0\u0098\3\2"+
		"\2\2\u00a0\u0099\3\2\2\2\u00a0\u009d\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0"+
		"\u009f\3\2\2\2\u00a1\u00e1\3\2\2\2\u00a2\u00a3\f\24\2\2\u00a3\u00a4\t"+
		"\4\2\2\u00a4\u00e0\5\26\f\25\u00a5\u00a6\f\23\2\2\u00a6\u00a7\t\3\2\2"+
		"\u00a7\u00e0\5\26\f\24\u00a8\u00a9\f\22\2\2\u00a9\u00aa\t\5\2\2\u00aa"+
		"\u00e0\5\26\f\23\u00ab\u00ac\f\21\2\2\u00ac\u00ad\t\6\2\2\u00ad\u00e0"+
		"\5\26\f\22\u00ae\u00af\f\20\2\2\u00af\u00b0\t\7\2\2\u00b0\u00e0\5\26\f"+
		"\21\u00b1\u00b2\f\17\2\2\u00b2\u00b3\7\23\2\2\u00b3\u00e0\5\26\f\20\u00b4"+
		"\u00b5\f\16\2\2\u00b5\u00b6\7\26\2\2\u00b6\u00e0\5\26\f\17\u00b7\u00b8"+
		"\f\r\2\2\u00b8\u00b9\7\24\2\2\u00b9\u00e0\5\26\f\16\u00ba\u00bb\f\f\2"+
		"\2\u00bb\u00bc\7\16\2\2\u00bc\u00e0\5\26\f\r\u00bd\u00be\f\13\2\2\u00be"+
		"\u00bf\7\17\2\2\u00bf\u00e0\5\26\f\f\u00c0\u00c1\f\n\2\2\u00c1\u00c2\7"+
		"\27\2\2\u00c2\u00e0\5\26\f\n\u00c3\u00c4\f\35\2\2\u00c4\u00e0\t\2\2\2"+
		"\u00c5\u00c6\f\33\2\2\u00c6\u00c7\7\33\2\2\u00c7\u00c8\5\26\f\2\u00c8"+
		"\u00c9\7\34\2\2\u00c9\u00e0\3\2\2\2\u00ca\u00cb\f\32\2\2\u00cb\u00ce\7"+
		"\32\2\2\u00cc\u00cf\7<\2\2\u00cd\u00cf\5\60\31\2\u00ce\u00cc\3\2\2\2\u00ce"+
		"\u00cd\3\2\2\2\u00cf\u00e0\3\2\2\2\u00d0\u00d1\f\31\2\2\u00d1\u00d2\7"+
		"\32\2\2\u00d2\u00d5\7\35\2\2\u00d3\u00d6\7<\2\2\u00d4\u00d6\5\60\31\2"+
		"\u00d5\u00d3\3\2\2\2\u00d5\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00e0"+
		"\7\36\2\2\u00d8\u00db\f\t\2\2\u00d9\u00da\7\37\2\2\u00da\u00dc\5\26\f"+
		"\2\u00db\u00d9\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de"+
		"\3\2\2\2\u00de\u00e0\3\2\2\2\u00df\u00a2\3\2\2\2\u00df\u00a5\3\2\2\2\u00df"+
		"\u00a8\3\2\2\2\u00df\u00ab\3\2\2\2\u00df\u00ae\3\2\2\2\u00df\u00b1\3\2"+
		"\2\2\u00df\u00b4\3\2\2\2\u00df\u00b7\3\2\2\2\u00df\u00ba\3\2\2\2\u00df"+
		"\u00bd\3\2\2\2\u00df\u00c0\3\2\2\2\u00df\u00c3\3\2\2\2\u00df\u00c5\3\2"+
		"\2\2\u00df\u00ca\3\2\2\2\u00df\u00d0\3\2\2\2\u00df\u00d8\3\2\2\2\u00e0"+
		"\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\27\3\2\2"+
		"\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\t\b\2\2\u00e5\31\3\2\2\2\u00e6\u00e7"+
		"\7\67\2\2\u00e7\u00ec\5&\24\2\u00e8\u00e9\7\33\2\2\u00e9\u00ea\5\26\f"+
		"\2\u00ea\u00eb\7\34\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00e8\3\2\2\2\u00ed"+
		"\u00ee\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f2\3\2"+
		"\2\2\u00f0\u00f1\7\35\2\2\u00f1\u00f3\7\36\2\2\u00f2\u00f0\3\2\2\2\u00f2"+
		"\u00f3\3\2\2\2\u00f3\u010c\3\2\2\2\u00f4\u00f5\7\67\2\2\u00f5\u00fa\5"+
		"&\24\2\u00f6\u00f7\7\33\2\2\u00f7\u00f8\5\26\f\2\u00f8\u00f9\7\34\2\2"+
		"\u00f9\u00fb\3\2\2\2\u00fa\u00f6\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fa"+
		"\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u0102\3\2\2\2\u00fe\u00ff\7\33\2\2"+
		"\u00ff\u0101\7\34\2\2\u0100\u00fe\3\2\2\2\u0101\u0104\3\2\2\2\u0102\u0100"+
		"\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u010c\3\2\2\2\u0104\u0102\3\2\2\2\u0105"+
		"\u0106\7\67\2\2\u0106\u0109\5&\24\2\u0107\u0108\7\35\2\2\u0108\u010a\7"+
		"\36\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010c\3\2\2\2\u010b"+
		"\u00e6\3\2\2\2\u010b\u00f4\3\2\2\2\u010b\u0105\3\2\2\2\u010c\33\3\2\2"+
		"\2\u010d\u010e\7\33\2\2\u010e\u010f\7\23\2\2\u010f\u0110\7\34\2\2\u0110"+
		"\u0111\7\35\2\2\u0111\u0112\5*\26\2\u0112\u0113\7\36\2\2\u0113\u0114\7"+
		"(\2\2\u0114\u0115\5\4\3\2\u0115\u0116\7\35\2\2\u0116\u0117\5\62\32\2\u0117"+
		"\u0118\7\36\2\2\u0118\35\3\2\2\2\u0119\u011a\5 \21\2\u011a\u011b\7 \2"+
		"\2\u011b\37\3\2\2\2\u011c\u011d\5$\23\2\u011d\u0122\5\"\22\2\u011e\u011f"+
		"\7\37\2\2\u011f\u0121\5\"\22\2\u0120\u011e\3\2\2\2\u0121\u0124\3\2\2\2"+
		"\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123!\3\2\2\2\u0124\u0122\3"+
		"\2\2\2\u0125\u0128\7<\2\2\u0126\u0127\7\27\2\2\u0127\u0129\5\26\f\2\u0128"+
		"\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129#\3\2\2\2\u012a\u012f\5&\24\2"+
		"\u012b\u012c\7\33\2\2\u012c\u012e\7\34\2\2\u012d\u012b\3\2\2\2\u012e\u0131"+
		"\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130%\3\2\2\2\u0131"+
		"\u012f\3\2\2\2\u0132\u0133\t\t\2\2\u0133\'\3\2\2\2\u0134\u0135\5.\30\2"+
		"\u0135\u0136\7<\2\2\u0136\u0137\7\35\2\2\u0137\u0138\5*\26\2\u0138\u0139"+
		"\7\36\2\2\u0139\u013a\5\4\3\2\u013a)\3\2\2\2\u013b\u0140\5,\27\2\u013c"+
		"\u013d\7\37\2\2\u013d\u013f\5,\27\2\u013e\u013c\3\2\2\2\u013f\u0142\3"+
		"\2\2\2\u0140\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0145\3\2\2\2\u0142"+
		"\u0140\3\2\2\2\u0143\u0145\3\2\2\2\u0144\u013b\3\2\2\2\u0144\u0143\3\2"+
		"\2\2\u0145+\3\2\2\2\u0146\u0147\5$\23\2\u0147\u0148\7<\2\2\u0148-\3\2"+
		"\2\2\u0149\u014c\5$\23\2\u014a\u014c\7-\2\2\u014b\u0149\3\2\2\2\u014b"+
		"\u014a\3\2\2\2\u014c/\3\2\2\2\u014d\u014e\7<\2\2\u014e\u014f\7\35\2\2"+
		"\u014f\u0150\5\62\32\2\u0150\u0151\7\36\2\2\u0151\61\3\2\2\2\u0152\u0157"+
		"\5\26\f\2\u0153\u0154\7\37\2\2\u0154\u0156\5\26\f\2\u0155\u0153\3\2\2"+
		"\2\u0156\u0159\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u015c"+
		"\3\2\2\2\u0159\u0157\3\2\2\2\u015a\u015c\3\2\2\2\u015b\u0152\3\2\2\2\u015b"+
		"\u015a\3\2\2\2\u015c\63\3\2\2\2\u015d\u015e\5\66\34\2\u015e\u015f\7 \2"+
		"\2\u015f\65\3\2\2\2\u0160\u0161\78\2\2\u0161\u0162\7<\2\2\u0162\u0168"+
		"\7!\2\2\u0163\u0167\5\36\20\2\u0164\u0167\58\35\2\u0165\u0167\5(\25\2"+
		"\u0166\u0163\3\2\2\2\u0166\u0164\3\2\2\2\u0166\u0165\3\2\2\2\u0167\u016a"+
		"\3\2\2\2\u0168\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016b\3\2\2\2\u016a"+
		"\u0168\3\2\2\2\u016b\u016c\7\"\2\2\u016c\67\3\2\2\2\u016d\u016e\7<\2\2"+
		"\u016e\u016f\7\35\2\2\u016f\u0170\7\36\2\2\u0170\u0171\5\4\3\2\u01719"+
		"\3\2\2\2#=?GIN]frvz\u0081\u00a0\u00ce\u00d5\u00dd\u00df\u00e1\u00ee\u00f2"+
		"\u00fc\u0102\u0109\u010b\u0122\u0128\u012f\u0140\u0144\u014b\u0157\u015b"+
		"\u0166\u0168";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}