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
		SingleQuote=36, DoubleQuote=37, RightArrow=38, IntType=39, BoolType=40, 
		StringType=41, Null=42, Void=43, True=44, False=45, If=46, Else=47, For=48, 
		While=49, Break=50, Continue=51, Return=52, New=53, Class=54, This=55, 
		IntLiteral=56, StringLiteral=57, Identifier=58, LineComment=59, BlockComment=60, 
		WhiteSpace=61;
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
		"DoubleQuote", "RightArrow", "IntType", "BoolType", "StringType", "Null", 
		"Void", "True", "False", "If", "Else", "For", "While", "Break", "Continue", 
		"Return", "New", "Class", "This", "IntLiteral", "StringLiteral", "Digit", 
		"Letter", "Underline", "Printable", "Escape", "NewLine", "Identifier", 
		"LineComment", "BlockComment", "WhiteSpace"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2?\u018e\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\3\2\3\2\3\3\3\3\3\4\3\4\3\5"+
		"\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3"+
		"\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3"+
		"\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3&"+
		"\3&\3\'\3\'\3\'\3(\3(\3(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3+\3+"+
		"\3+\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3/\3/\3/\3\60"+
		"\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62"+
		"\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\67\3\67"+
		"\3\67\3\67\3\67\3\67\38\38\38\38\38\39\39\39\79\u0143\n9\f9\169\u0146"+
		"\139\59\u0148\n9\3:\3:\3:\3:\7:\u014e\n:\f:\16:\u0151\13:\3:\3:\3;\3;"+
		"\3<\3<\3=\3=\3>\3>\3?\3?\3?\5?\u0160\n?\3@\5@\u0163\n@\3@\3@\3A\3A\3A"+
		"\3A\7A\u016b\nA\fA\16A\u016e\13A\3B\3B\7B\u0172\nB\fB\16B\u0175\13B\3"+
		"B\3B\5B\u0179\nB\3B\3B\3C\3C\7C\u017f\nC\fC\16C\u0182\13C\3C\3C\3C\3C"+
		"\3D\6D\u0189\nD\rD\16D\u018a\3D\3D\5\u014f\u0173\u0180\2E\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u\2w\2y"+
		"\2{\2}\2\177\2\u0081<\u0083=\u0085>\u0087?\3\2\b\3\2\63;\3\2\62;\4\2C"+
		"\\c|\3\2\"\u0080\4\2\f\f^^\5\2\13\f\17\17\"\"\2\u0194\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2"+
		"\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2"+
		"\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q"+
		"\3\2\2\2\2s\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2"+
		"\u0087\3\2\2\2\3\u0089\3\2\2\2\5\u008b\3\2\2\2\7\u008d\3\2\2\2\t\u008f"+
		"\3\2\2\2\13\u0091\3\2\2\2\r\u0093\3\2\2\2\17\u0095\3\2\2\2\21\u0097\3"+
		"\2\2\2\23\u009a\3\2\2\2\25\u009d\3\2\2\2\27\u00a0\3\2\2\2\31\u00a3\3\2"+
		"\2\2\33\u00a6\3\2\2\2\35\u00a9\3\2\2\2\37\u00ab\3\2\2\2!\u00ae\3\2\2\2"+
		"#\u00b1\3\2\2\2%\u00b3\3\2\2\2\'\u00b5\3\2\2\2)\u00b7\3\2\2\2+\u00b9\3"+
		"\2\2\2-\u00bb\3\2\2\2/\u00be\3\2\2\2\61\u00c1\3\2\2\2\63\u00c3\3\2\2\2"+
		"\65\u00c5\3\2\2\2\67\u00c7\3\2\2\29\u00c9\3\2\2\2;\u00cb\3\2\2\2=\u00cd"+
		"\3\2\2\2?\u00cf\3\2\2\2A\u00d1\3\2\2\2C\u00d3\3\2\2\2E\u00d6\3\2\2\2G"+
		"\u00d9\3\2\2\2I\u00dc\3\2\2\2K\u00de\3\2\2\2M\u00e0\3\2\2\2O\u00e3\3\2"+
		"\2\2Q\u00e7\3\2\2\2S\u00ec\3\2\2\2U\u00f3\3\2\2\2W\u00f8\3\2\2\2Y\u00fd"+
		"\3\2\2\2[\u0102\3\2\2\2]\u0108\3\2\2\2_\u010b\3\2\2\2a\u0110\3\2\2\2c"+
		"\u0114\3\2\2\2e\u011a\3\2\2\2g\u0120\3\2\2\2i\u0129\3\2\2\2k\u0130\3\2"+
		"\2\2m\u0134\3\2\2\2o\u013a\3\2\2\2q\u0147\3\2\2\2s\u0149\3\2\2\2u\u0154"+
		"\3\2\2\2w\u0156\3\2\2\2y\u0158\3\2\2\2{\u015a\3\2\2\2}\u015f\3\2\2\2\177"+
		"\u0162\3\2\2\2\u0081\u0166\3\2\2\2\u0083\u016f\3\2\2\2\u0085\u017c\3\2"+
		"\2\2\u0087\u0188\3\2\2\2\u0089\u008a\7-\2\2\u008a\4\3\2\2\2\u008b\u008c"+
		"\7/\2\2\u008c\6\3\2\2\2\u008d\u008e\7,\2\2\u008e\b\3\2\2\2\u008f\u0090"+
		"\7\61\2\2\u0090\n\3\2\2\2\u0091\u0092\7\'\2\2\u0092\f\3\2\2\2\u0093\u0094"+
		"\7@\2\2\u0094\16\3\2\2\2\u0095\u0096\7>\2\2\u0096\20\3\2\2\2\u0097\u0098"+
		"\7?\2\2\u0098\u0099\7?\2\2\u0099\22\3\2\2\2\u009a\u009b\7@\2\2\u009b\u009c"+
		"\7?\2\2\u009c\24\3\2\2\2\u009d\u009e\7>\2\2\u009e\u009f\7?\2\2\u009f\26"+
		"\3\2\2\2\u00a0\u00a1\7#\2\2\u00a1\u00a2\7?\2\2\u00a2\30\3\2\2\2\u00a3"+
		"\u00a4\7(\2\2\u00a4\u00a5\7(\2\2\u00a5\32\3\2\2\2\u00a6\u00a7\7~\2\2\u00a7"+
		"\u00a8\7~\2\2\u00a8\34\3\2\2\2\u00a9\u00aa\7#\2\2\u00aa\36\3\2\2\2\u00ab"+
		"\u00ac\7@\2\2\u00ac\u00ad\7@\2\2\u00ad \3\2\2\2\u00ae\u00af\7>\2\2\u00af"+
		"\u00b0\7>\2\2\u00b0\"\3\2\2\2\u00b1\u00b2\7(\2\2\u00b2$\3\2\2\2\u00b3"+
		"\u00b4\7~\2\2\u00b4&\3\2\2\2\u00b5\u00b6\7\u0080\2\2\u00b6(\3\2\2\2\u00b7"+
		"\u00b8\7`\2\2\u00b8*\3\2\2\2\u00b9\u00ba\7?\2\2\u00ba,\3\2\2\2\u00bb\u00bc"+
		"\7-\2\2\u00bc\u00bd\7-\2\2\u00bd.\3\2\2\2\u00be\u00bf\7/\2\2\u00bf\u00c0"+
		"\7/\2\2\u00c0\60\3\2\2\2\u00c1\u00c2\7\60\2\2\u00c2\62\3\2\2\2\u00c3\u00c4"+
		"\7]\2\2\u00c4\64\3\2\2\2\u00c5\u00c6\7_\2\2\u00c6\66\3\2\2\2\u00c7\u00c8"+
		"\7*\2\2\u00c88\3\2\2\2\u00c9\u00ca\7+\2\2\u00ca:\3\2\2\2\u00cb\u00cc\7"+
		".\2\2\u00cc<\3\2\2\2\u00cd\u00ce\7=\2\2\u00ce>\3\2\2\2\u00cf\u00d0\7}"+
		"\2\2\u00d0@\3\2\2\2\u00d1\u00d2\7\177\2\2\u00d2B\3\2\2\2\u00d3\u00d4\7"+
		"\61\2\2\u00d4\u00d5\7\61\2\2\u00d5D\3\2\2\2\u00d6\u00d7\7\61\2\2\u00d7"+
		"\u00d8\7,\2\2\u00d8F\3\2\2\2\u00d9\u00da\7,\2\2\u00da\u00db\7\61\2\2\u00db"+
		"H\3\2\2\2\u00dc\u00dd\7)\2\2\u00ddJ\3\2\2\2\u00de\u00df\7$\2\2\u00dfL"+
		"\3\2\2\2\u00e0\u00e1\7/\2\2\u00e1\u00e2\7@\2\2\u00e2N\3\2\2\2\u00e3\u00e4"+
		"\7k\2\2\u00e4\u00e5\7p\2\2\u00e5\u00e6\7v\2\2\u00e6P\3\2\2\2\u00e7\u00e8"+
		"\7d\2\2\u00e8\u00e9\7q\2\2\u00e9\u00ea\7q\2\2\u00ea\u00eb\7n\2\2\u00eb"+
		"R\3\2\2\2\u00ec\u00ed\7u\2\2\u00ed\u00ee\7v\2\2\u00ee\u00ef\7t\2\2\u00ef"+
		"\u00f0\7k\2\2\u00f0\u00f1\7p\2\2\u00f1\u00f2\7i\2\2\u00f2T\3\2\2\2\u00f3"+
		"\u00f4\7p\2\2\u00f4\u00f5\7w\2\2\u00f5\u00f6\7n\2\2\u00f6\u00f7\7n\2\2"+
		"\u00f7V\3\2\2\2\u00f8\u00f9\7x\2\2\u00f9\u00fa\7q\2\2\u00fa\u00fb\7k\2"+
		"\2\u00fb\u00fc\7f\2\2\u00fcX\3\2\2\2\u00fd\u00fe\7v\2\2\u00fe\u00ff\7"+
		"t\2\2\u00ff\u0100\7w\2\2\u0100\u0101\7g\2\2\u0101Z\3\2\2\2\u0102\u0103"+
		"\7h\2\2\u0103\u0104\7c\2\2\u0104\u0105\7n\2\2\u0105\u0106\7u\2\2\u0106"+
		"\u0107\7g\2\2\u0107\\\3\2\2\2\u0108\u0109\7k\2\2\u0109\u010a\7h\2\2\u010a"+
		"^\3\2\2\2\u010b\u010c\7g\2\2\u010c\u010d\7n\2\2\u010d\u010e\7u\2\2\u010e"+
		"\u010f\7g\2\2\u010f`\3\2\2\2\u0110\u0111\7h\2\2\u0111\u0112\7q\2\2\u0112"+
		"\u0113\7t\2\2\u0113b\3\2\2\2\u0114\u0115\7y\2\2\u0115\u0116\7j\2\2\u0116"+
		"\u0117\7k\2\2\u0117\u0118\7n\2\2\u0118\u0119\7g\2\2\u0119d\3\2\2\2\u011a"+
		"\u011b\7d\2\2\u011b\u011c\7t\2\2\u011c\u011d\7g\2\2\u011d\u011e\7c\2\2"+
		"\u011e\u011f\7m\2\2\u011ff\3\2\2\2\u0120\u0121\7e\2\2\u0121\u0122\7q\2"+
		"\2\u0122\u0123\7p\2\2\u0123\u0124\7v\2\2\u0124\u0125\7k\2\2\u0125\u0126"+
		"\7p\2\2\u0126\u0127\7w\2\2\u0127\u0128\7g\2\2\u0128h\3\2\2\2\u0129\u012a"+
		"\7t\2\2\u012a\u012b\7g\2\2\u012b\u012c\7v\2\2\u012c\u012d\7w\2\2\u012d"+
		"\u012e\7t\2\2\u012e\u012f\7p\2\2\u012fj\3\2\2\2\u0130\u0131\7p\2\2\u0131"+
		"\u0132\7g\2\2\u0132\u0133\7y\2\2\u0133l\3\2\2\2\u0134\u0135\7e\2\2\u0135"+
		"\u0136\7n\2\2\u0136\u0137\7c\2\2\u0137\u0138\7u\2\2\u0138\u0139\7u\2\2"+
		"\u0139n\3\2\2\2\u013a\u013b\7v\2\2\u013b\u013c\7j\2\2\u013c\u013d\7k\2"+
		"\2\u013d\u013e\7u\2\2\u013ep\3\2\2\2\u013f\u0148\7\62\2\2\u0140\u0144"+
		"\t\2\2\2\u0141\u0143\t\3\2\2\u0142\u0141\3\2\2\2\u0143\u0146\3\2\2\2\u0144"+
		"\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0148\3\2\2\2\u0146\u0144\3\2"+
		"\2\2\u0147\u013f\3\2\2\2\u0147\u0140\3\2\2\2\u0148r\3\2\2\2\u0149\u014f"+
		"\5K&\2\u014a\u014b\7^\2\2\u014b\u014e\7$\2\2\u014c\u014e\13\2\2\2\u014d"+
		"\u014a\3\2\2\2\u014d\u014c\3\2\2\2\u014e\u0151\3\2\2\2\u014f\u0150\3\2"+
		"\2\2\u014f\u014d\3\2\2\2\u0150\u0152\3\2\2\2\u0151\u014f\3\2\2\2\u0152"+
		"\u0153\5K&\2\u0153t\3\2\2\2\u0154\u0155\t\3\2\2\u0155v\3\2\2\2\u0156\u0157"+
		"\t\4\2\2\u0157x\3\2\2\2\u0158\u0159\7a\2\2\u0159z\3\2\2\2\u015a\u015b"+
		"\t\5\2\2\u015b|\3\2\2\2\u015c\u0160\t\6\2\2\u015d\u015e\7^\2\2\u015e\u0160"+
		"\7$\2\2\u015f\u015c\3\2\2\2\u015f\u015d\3\2\2\2\u0160~\3\2\2\2\u0161\u0163"+
		"\7\17\2\2\u0162\u0161\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0164\3\2\2\2"+
		"\u0164\u0165\7\f\2\2\u0165\u0080\3\2\2\2\u0166\u016c\5w<\2\u0167\u016b"+
		"\5w<\2\u0168\u016b\5u;\2\u0169\u016b\5y=\2\u016a\u0167\3\2\2\2\u016a\u0168"+
		"\3\2\2\2\u016a\u0169\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2\2\2\u016c"+
		"\u016d\3\2\2\2\u016d\u0082\3\2\2\2\u016e\u016c\3\2\2\2\u016f\u0173\5C"+
		"\"\2\u0170\u0172\13\2\2\2\u0171\u0170\3\2\2\2\u0172\u0175\3\2\2\2\u0173"+
		"\u0174\3\2\2\2\u0173\u0171\3\2\2\2\u0174\u0178\3\2\2\2\u0175\u0173\3\2"+
		"\2\2\u0176\u0179\5\177@\2\u0177\u0179\7\2\2\3\u0178\u0176\3\2\2\2\u0178"+
		"\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017b\bB\2\2\u017b\u0084\3\2"+
		"\2\2\u017c\u0180\5E#\2\u017d\u017f\13\2\2\2\u017e\u017d\3\2\2\2\u017f"+
		"\u0182\3\2\2\2\u0180\u0181\3\2\2\2\u0180\u017e\3\2\2\2\u0181\u0183\3\2"+
		"\2\2\u0182\u0180\3\2\2\2\u0183\u0184\5G$\2\u0184\u0185\3\2\2\2\u0185\u0186"+
		"\bC\2\2\u0186\u0086\3\2\2\2\u0187\u0189\t\7\2\2\u0188\u0187\3\2\2\2\u0189"+
		"\u018a\3\2\2\2\u018a\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018c\3\2"+
		"\2\2\u018c\u018d\bD\2\2\u018d\u0088\3\2\2\2\17\2\u0144\u0147\u014d\u014f"+
		"\u015f\u0162\u016a\u016c\u0173\u0178\u0180\u018a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}