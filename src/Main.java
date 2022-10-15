import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStreams;

import Parser.MxStarLexer;
import Parser.MxStarParser;
import TestPackage.Test;
import TestPackage.TestPackage1.Test1;
import Util.MxStarErrorListener;
import Util.MxStarErrors.BaseError;
import Util.MxStarErrors.SemanticError;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Test.showShowWay();
            Test1.showShowWay();
            System.out.println(" --------------------------------- ");
            File testCode = new File("testcases/sema/test.mx");
            InputStream testCodeStream = new FileInputStream(testCode);

            // get lexer
            MxStarLexer lexer = new MxStarLexer(CharStreams.fromStream(testCodeStream));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxStarErrorListener());

            // get parser
            MxStarParser parser = new MxStarParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxStarErrorListener());

            ParseTree treeRoot = parser.program();
        } catch (BaseError e) {
            System.err.println(e.toString());
            throw new RuntimeException();
        }
    }

    void test(MxStarParser.ExpressionContext a) {
        SemanticError v1 = new SemanticError(null, null);
        SemanticError v2 = v1;
    }
}
