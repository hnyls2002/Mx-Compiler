import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;

import Parser.MxStarLexer;
import Parser.MxStarParser;
import TestPackage.Test;
import TestPackage.TestPackage1.Test1;

public class Main {
    public static void main(String[] args) throws Exception {
        Test.showShowWay();
        Test1.showShowWay();
        System.out.println(" --------------------------------- ");
        File testCode = new File("testcases/sema/test.mx");
        InputStream testCodeStream = new FileInputStream(testCode);
        MxStarLexer lexer = new MxStarLexer(CharStreams.fromStream(testCodeStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
    }
}
