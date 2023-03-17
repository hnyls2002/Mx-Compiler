import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import AST.ASTNode;
import AST.Scopes.GlobalScope;
import Backend.Backender;
import Frontend.Frontender;
import Frontend.MxStar.MxStarErrors.BaseError;
import Middleend.Middleender;

public class Compiler {
    public static void main(String[] args) throws Exception {
        try {
            boolean testManual = true;
            boolean testOnline = false;

            String filePath = testManual ? "./debug/" : "./autotestspace/";
            File testCode = new File(filePath + "test.mx");

            InputStream testCodeStream;
            if (testOnline)
                testCodeStream = System.in;
            else
                testCodeStream = new FileInputStream(testCode);

            GlobalScope gScope = new GlobalScope();
            ASTNode ast = new Frontender().run(testCodeStream, gScope);

            var irModule = new Middleender().run(ast, gScope, filePath, testOnline);

            new Backender().run(irModule, filePath, testOnline);

        } catch (BaseError e) {
            System.err.println(e.toString());
            throw new RuntimeException();
        }
    }
}
