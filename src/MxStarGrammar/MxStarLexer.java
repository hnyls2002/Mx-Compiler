// Generated from MxStarLexer.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxStarLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"Add", "Sub", "Mul", "Div", "Mod", "Greater", "Less", "Equal", "GreaterEqual", 
		"LessEqual", "NotEqual", "LogicAnd", "LogicOr", "LogicNot", "ShiftRight", 
		"ShiftLeft", "BitAnd", "BitOr", "BitNot", "BitXor", "Assign", "Inc", "Dec", 
		"Dot", "LBracket", "RBracket", "LParen", "RParen", "Comma", "Semicolon", 
		"LBrace", "RBrace", "DoubleSlash", "SlashStar", "StarSlash", "SingleQuote", 
		"DoubleQuote", "LambdaStart", "RightArrow", "IntType", "BoolType", "StringType", 
		"Null", "Void", "True", "False", "If", "Else", "For", "While", "Break", 
		"Continue", "Return", "New", "Class", "This", "IntLiteral", "StringLiteral", 
		"Digit", "Letter", "Underline", "Printable", "Escape", "NewLine", "Identifier", 
		"LineComment", "BlockComment", "WhiteSpace"
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


	public MxStarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MxStarLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2@\u0195\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\3\2\3\2\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3"+
		"\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3"+
		"\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3"+
		"%\3&\3&\3\'\3\'\3\'\3\'\3(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+\3"+
		"+\3+\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\3/\3"+
		"/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\63"+
		"\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65"+
		"\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\67"+
		"\3\67\3\67\3\67\38\38\38\38\38\38\39\39\39\39\39\3:\3:\3:\7:\u0149\n:"+
		"\f:\16:\u014c\13:\5:\u014e\n:\3;\3;\3;\7;\u0153\n;\f;\16;\u0156\13;\3"+
		";\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3@\3@\3@\5@\u0167\n@\3A\5A\u016a\n"+
		"A\3A\3A\3B\3B\3B\3B\7B\u0172\nB\fB\16B\u0175\13B\3C\3C\7C\u0179\nC\fC"+
		"\16C\u017c\13C\3C\3C\5C\u0180\nC\3C\3C\3D\3D\7D\u0186\nD\fD\16D\u0189"+
		"\13D\3D\3D\3D\3D\3E\6E\u0190\nE\rE\16E\u0191\3E\3E\5\u0154\u017a\u0187"+
		"\2F\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67"+
		"m8o9q:s;u<w\2y\2{\2}\2\177\2\u0081\2\u0083=\u0085>\u0087?\u0089@\3\2\7"+
		"\3\2\63;\3\2\62;\4\2C\\c|\3\2\"\u0080\5\2\13\f\17\17\"\"\2\u019c\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I"+
		"\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2"+
		"\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2"+
		"\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o"+
		"\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2"+
		"\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\3\u008b\3\2\2\2\5\u008d\3\2\2\2\7"+
		"\u008f\3\2\2\2\t\u0091\3\2\2\2\13\u0093\3\2\2\2\r\u0095\3\2\2\2\17\u0097"+
		"\3\2\2\2\21\u0099\3\2\2\2\23\u009c\3\2\2\2\25\u009f\3\2\2\2\27\u00a2\3"+
		"\2\2\2\31\u00a5\3\2\2\2\33\u00a8\3\2\2\2\35\u00ab\3\2\2\2\37\u00ad\3\2"+
		"\2\2!\u00b0\3\2\2\2#\u00b3\3\2\2\2%\u00b5\3\2\2\2\'\u00b7\3\2\2\2)\u00b9"+
		"\3\2\2\2+\u00bb\3\2\2\2-\u00bd\3\2\2\2/\u00c0\3\2\2\2\61\u00c3\3\2\2\2"+
		"\63\u00c5\3\2\2\2\65\u00c7\3\2\2\2\67\u00c9\3\2\2\29\u00cb\3\2\2\2;\u00cd"+
		"\3\2\2\2=\u00cf\3\2\2\2?\u00d1\3\2\2\2A\u00d3\3\2\2\2C\u00d5\3\2\2\2E"+
		"\u00d8\3\2\2\2G\u00db\3\2\2\2I\u00de\3\2\2\2K\u00e0\3\2\2\2M\u00e2\3\2"+
		"\2\2O\u00e6\3\2\2\2Q\u00e9\3\2\2\2S\u00ed\3\2\2\2U\u00f2\3\2\2\2W\u00f9"+
		"\3\2\2\2Y\u00fe\3\2\2\2[\u0103\3\2\2\2]\u0108\3\2\2\2_\u010e\3\2\2\2a"+
		"\u0111\3\2\2\2c\u0116\3\2\2\2e\u011a\3\2\2\2g\u0120\3\2\2\2i\u0126\3\2"+
		"\2\2k\u012f\3\2\2\2m\u0136\3\2\2\2o\u013a\3\2\2\2q\u0140\3\2\2\2s\u014d"+
		"\3\2\2\2u\u014f\3\2\2\2w\u0159\3\2\2\2y\u015b\3\2\2\2{\u015d\3\2\2\2}"+
		"\u015f\3\2\2\2\177\u0166\3\2\2\2\u0081\u0169\3\2\2\2\u0083\u016d\3\2\2"+
		"\2\u0085\u0176\3\2\2\2\u0087\u0183\3\2\2\2\u0089\u018f\3\2\2\2\u008b\u008c"+
		"\7-\2\2\u008c\4\3\2\2\2\u008d\u008e\7/\2\2\u008e\6\3\2\2\2\u008f\u0090"+
		"\7,\2\2\u0090\b\3\2\2\2\u0091\u0092\7\61\2\2\u0092\n\3\2\2\2\u0093\u0094"+
		"\7\'\2\2\u0094\f\3\2\2\2\u0095\u0096\7@\2\2\u0096\16\3\2\2\2\u0097\u0098"+
		"\7>\2\2\u0098\20\3\2\2\2\u0099\u009a\7?\2\2\u009a\u009b\7?\2\2\u009b\22"+
		"\3\2\2\2\u009c\u009d\7@\2\2\u009d\u009e\7?\2\2\u009e\24\3\2\2\2\u009f"+
		"\u00a0\7>\2\2\u00a0\u00a1\7?\2\2\u00a1\26\3\2\2\2\u00a2\u00a3\7#\2\2\u00a3"+
		"\u00a4\7?\2\2\u00a4\30\3\2\2\2\u00a5\u00a6\7(\2\2\u00a6\u00a7\7(\2\2\u00a7"+
		"\32\3\2\2\2\u00a8\u00a9\7~\2\2\u00a9\u00aa\7~\2\2\u00aa\34\3\2\2\2\u00ab"+
		"\u00ac\7#\2\2\u00ac\36\3\2\2\2\u00ad\u00ae\7@\2\2\u00ae\u00af\7@\2\2\u00af"+
		" \3\2\2\2\u00b0\u00b1\7>\2\2\u00b1\u00b2\7>\2\2\u00b2\"\3\2\2\2\u00b3"+
		"\u00b4\7(\2\2\u00b4$\3\2\2\2\u00b5\u00b6\7~\2\2\u00b6&\3\2\2\2\u00b7\u00b8"+
		"\7\u0080\2\2\u00b8(\3\2\2\2\u00b9\u00ba\7`\2\2\u00ba*\3\2\2\2\u00bb\u00bc"+
		"\7?\2\2\u00bc,\3\2\2\2\u00bd\u00be\7-\2\2\u00be\u00bf\7-\2\2\u00bf.\3"+
		"\2\2\2\u00c0\u00c1\7/\2\2\u00c1\u00c2\7/\2\2\u00c2\60\3\2\2\2\u00c3\u00c4"+
		"\7\60\2\2\u00c4\62\3\2\2\2\u00c5\u00c6\7]\2\2\u00c6\64\3\2\2\2\u00c7\u00c8"+
		"\7_\2\2\u00c8\66\3\2\2\2\u00c9\u00ca\7*\2\2\u00ca8\3\2\2\2\u00cb\u00cc"+
		"\7+\2\2\u00cc:\3\2\2\2\u00cd\u00ce\7.\2\2\u00ce<\3\2\2\2\u00cf\u00d0\7"+
		"=\2\2\u00d0>\3\2\2\2\u00d1\u00d2\7}\2\2\u00d2@\3\2\2\2\u00d3\u00d4\7\177"+
		"\2\2\u00d4B\3\2\2\2\u00d5\u00d6\7\61\2\2\u00d6\u00d7\7\61\2\2\u00d7D\3"+
		"\2\2\2\u00d8\u00d9\7\61\2\2\u00d9\u00da\7,\2\2\u00daF\3\2\2\2\u00db\u00dc"+
		"\7,\2\2\u00dc\u00dd\7\61\2\2\u00ddH\3\2\2\2\u00de\u00df\7)\2\2\u00dfJ"+
		"\3\2\2\2\u00e0\u00e1\7$\2\2\u00e1L\3\2\2\2\u00e2\u00e3\7]\2\2\u00e3\u00e4"+
		"\7(\2\2\u00e4\u00e5\7_\2\2\u00e5N\3\2\2\2\u00e6\u00e7\7/\2\2\u00e7\u00e8"+
		"\7@\2\2\u00e8P\3\2\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7p\2\2\u00eb\u00ec"+
		"\7v\2\2\u00ecR\3\2\2\2\u00ed\u00ee\7d\2\2\u00ee\u00ef\7q\2\2\u00ef\u00f0"+
		"\7q\2\2\u00f0\u00f1\7n\2\2\u00f1T\3\2\2\2\u00f2\u00f3\7u\2\2\u00f3\u00f4"+
		"\7v\2\2\u00f4\u00f5\7t\2\2\u00f5\u00f6\7k\2\2\u00f6\u00f7\7p\2\2\u00f7"+
		"\u00f8\7i\2\2\u00f8V\3\2\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7w\2\2\u00fb"+
		"\u00fc\7n\2\2\u00fc\u00fd\7n\2\2\u00fdX\3\2\2\2\u00fe\u00ff\7x\2\2\u00ff"+
		"\u0100\7q\2\2\u0100\u0101\7k\2\2\u0101\u0102\7f\2\2\u0102Z\3\2\2\2\u0103"+
		"\u0104\7v\2\2\u0104\u0105\7t\2\2\u0105\u0106\7w\2\2\u0106\u0107\7g\2\2"+
		"\u0107\\\3\2\2\2\u0108\u0109\7h\2\2\u0109\u010a\7c\2\2\u010a\u010b\7n"+
		"\2\2\u010b\u010c\7u\2\2\u010c\u010d\7g\2\2\u010d^\3\2\2\2\u010e\u010f"+
		"\7k\2\2\u010f\u0110\7h\2\2\u0110`\3\2\2\2\u0111\u0112\7g\2\2\u0112\u0113"+
		"\7n\2\2\u0113\u0114\7u\2\2\u0114\u0115\7g\2\2\u0115b\3\2\2\2\u0116\u0117"+
		"\7h\2\2\u0117\u0118\7q\2\2\u0118\u0119\7t\2\2\u0119d\3\2\2\2\u011a\u011b"+
		"\7y\2\2\u011b\u011c\7j\2\2\u011c\u011d\7k\2\2\u011d\u011e\7n\2\2\u011e"+
		"\u011f\7g\2\2\u011ff\3\2\2\2\u0120\u0121\7d\2\2\u0121\u0122\7t\2\2\u0122"+
		"\u0123\7g\2\2\u0123\u0124\7c\2\2\u0124\u0125\7m\2\2\u0125h\3\2\2\2\u0126"+
		"\u0127\7e\2\2\u0127\u0128\7q\2\2\u0128\u0129\7p\2\2\u0129\u012a\7v\2\2"+
		"\u012a\u012b\7k\2\2\u012b\u012c\7p\2\2\u012c\u012d\7w\2\2\u012d\u012e"+
		"\7g\2\2\u012ej\3\2\2\2\u012f\u0130\7t\2\2\u0130\u0131\7g\2\2\u0131\u0132"+
		"\7v\2\2\u0132\u0133\7w\2\2\u0133\u0134\7t\2\2\u0134\u0135\7p\2\2\u0135"+
		"l\3\2\2\2\u0136\u0137\7p\2\2\u0137\u0138\7g\2\2\u0138\u0139\7y\2\2\u0139"+
		"n\3\2\2\2\u013a\u013b\7e\2\2\u013b\u013c\7n\2\2\u013c\u013d\7c\2\2\u013d"+
		"\u013e\7u\2\2\u013e\u013f\7u\2\2\u013fp\3\2\2\2\u0140\u0141\7v\2\2\u0141"+
		"\u0142\7j\2\2\u0142\u0143\7k\2\2\u0143\u0144\7u\2\2\u0144r\3\2\2\2\u0145"+
		"\u014e\7\62\2\2\u0146\u014a\t\2\2\2\u0147\u0149\t\3\2\2\u0148\u0147\3"+
		"\2\2\2\u0149\u014c\3\2\2\2\u014a\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b"+
		"\u014e\3\2\2\2\u014c\u014a\3\2\2\2\u014d\u0145\3\2\2\2\u014d\u0146\3\2"+
		"\2\2\u014et\3\2\2\2\u014f\u0154\5K&\2\u0150\u0153\5\177@\2\u0151\u0153"+
		"\13\2\2\2\u0152\u0150\3\2\2\2\u0152\u0151\3\2\2\2\u0153\u0156\3\2\2\2"+
		"\u0154\u0155\3\2\2\2\u0154\u0152\3\2\2\2\u0155\u0157\3\2\2\2\u0156\u0154"+
		"\3\2\2\2\u0157\u0158\5K&\2\u0158v\3\2\2\2\u0159\u015a\t\3\2\2\u015ax\3"+
		"\2\2\2\u015b\u015c\t\4\2\2\u015cz\3\2\2\2\u015d\u015e\7a\2\2\u015e|\3"+
		"\2\2\2\u015f\u0160\t\5\2\2\u0160~\3\2\2\2\u0161\u0167\7\f\2\2\u0162\u0163"+
		"\7^\2\2\u0163\u0167\7^\2\2\u0164\u0165\7^\2\2\u0165\u0167\7$\2\2\u0166"+
		"\u0161\3\2\2\2\u0166\u0162\3\2\2\2\u0166\u0164\3\2\2\2\u0167\u0080\3\2"+
		"\2\2\u0168\u016a\7\17\2\2\u0169\u0168\3\2\2\2\u0169\u016a\3\2\2\2\u016a"+
		"\u016b\3\2\2\2\u016b\u016c\7\f\2\2\u016c\u0082\3\2\2\2\u016d\u0173\5y"+
		"=\2\u016e\u0172\5y=\2\u016f\u0172\5w<\2\u0170\u0172\5{>\2\u0171\u016e"+
		"\3\2\2\2\u0171\u016f\3\2\2\2\u0171\u0170\3\2\2\2\u0172\u0175\3\2\2\2\u0173"+
		"\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0084\3\2\2\2\u0175\u0173\3\2"+
		"\2\2\u0176\u017a\5C\"\2\u0177\u0179\13\2\2\2\u0178\u0177\3\2\2\2\u0179"+
		"\u017c\3\2\2\2\u017a\u017b\3\2\2\2\u017a\u0178\3\2\2\2\u017b\u017f\3\2"+
		"\2\2\u017c\u017a\3\2\2\2\u017d\u0180\5\u0081A\2\u017e\u0180\7\2\2\3\u017f"+
		"\u017d\3\2\2\2\u017f\u017e\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0182\bC"+
		"\2\2\u0182\u0086\3\2\2\2\u0183\u0187\5E#\2\u0184\u0186\13\2\2\2\u0185"+
		"\u0184\3\2\2\2\u0186\u0189\3\2\2\2\u0187\u0188\3\2\2\2\u0187\u0185\3\2"+
		"\2\2\u0188\u018a\3\2\2\2\u0189\u0187\3\2\2\2\u018a\u018b\5G$\2\u018b\u018c"+
		"\3\2\2\2\u018c\u018d\bD\2\2\u018d\u0088\3\2\2\2\u018e\u0190\t\6\2\2\u018f"+
		"\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u018f\3\2\2\2\u0191\u0192\3\2"+
		"\2\2\u0192\u0193\3\2\2\2\u0193\u0194\bE\2\2\u0194\u008a\3\2\2\2\17\2\u014a"+
		"\u014d\u0152\u0154\u0166\u0169\u0171\u0173\u017a\u017f\u0187\u0191\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}