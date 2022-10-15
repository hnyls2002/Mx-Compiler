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

public class Main {
    public static void main(String[] args) throws Exception {
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
    }

    void test(MxStarParser.ExpressionContext a) {
    }
}
