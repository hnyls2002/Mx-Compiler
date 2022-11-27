import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import AST.ProgramNode;
import Frontend.ASTBuilder;
import Frontend.ProgInit;
import Frontend.SemanticChecker;
import Frontend.Util.MxStarErrorListener;
import Frontend.Util.MxStarErrors.BaseError;
import Frontend.Util.Scopes.GlobalScope;
import IR.IRModule;
import MiddleEnd.IRBuilder;
import MiddleEnd.IRPrinter;
import MiddleEnd.IRRenamer;

import org.antlr.v4.runtime.CharStreams;

import Parser.MxStarLexer;
import Parser.MxStarParser;
import TestPackage.Test;
import TestPackage.TestPackage1.Test1;

public class Compiler {
    public static void main(String[] args) throws Exception {
        try {
            Test.showShowWay();
            Test1.showShowWay();
            System.out.println(" --------------------------------- ");
            File testCode = new File("testcases/test_short_circuit.mx");

            InputStream testCodeStream = new FileInputStream(testCode);

            // InputStream testCodeStream = System.in;

            // get lexer
            MxStarLexer lexer = new MxStarLexer(CharStreams.fromStream(testCodeStream));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxStarErrorListener());

            // get parser
            MxStarParser parser = new MxStarParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxStarErrorListener());

            ParseTree treeRoot = parser.program();
            ASTBuilder astBuilder = new ASTBuilder(false);
            ProgramNode ast = (ProgramNode) astBuilder.visit(treeRoot);

            GlobalScope gScope = new GlobalScope();
            ProgInit progInit = new ProgInit(gScope);
            progInit.visit(ast);

            // gScope.showShowWay();

            SemanticChecker semanticChecker = new SemanticChecker(gScope);
            semanticChecker.visit(ast);

            // -------------------------------------------------------

            IRBuilder irBuilder = new IRBuilder(ast, gScope);
            IRModule topModule = irBuilder.buildIR();
            IRRenamer irRenamer = new IRRenamer(topModule);
            irRenamer.renameIR();
            IRPrinter irPrinter = new IRPrinter(topModule, "testcases/test.ll");
            irPrinter.printIR();

        } catch (BaseError e) {
            System.err.println(e.toString());
            throw new RuntimeException();
        }
    }
}
