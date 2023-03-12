import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ASM.ASMModule;
import AST.ProgramNode;
import AST.Scopes.GlobalScope;
import Backend.ASMBuiler;
import Backend.ASMFormatter;
import Backend.RegAllocate.BfRegAllocator;
import Backend.RegAllocate.StackAllocator;
import Frontend.ASTBuilder;
import Frontend.ProgInit;
import Frontend.SemanticChecker;
import Frontend.MxStar.MxStarErrorListener;
import Frontend.MxStar.MxStarErrors.BaseError;
import Middleend.IRPrinter;
import Middleend.Middleender;

import org.antlr.v4.runtime.CharStreams;

import Parser.MxStarLexer;
import Parser.MxStarParser;
import Share.Builtin.BuiltinPrinter;

public class Compiler {
    public static void main(String[] args) throws Exception {
        try {
            boolean testManual = false;
            boolean testOnline = false;

            String filePath = testManual ? "./debug/" : "./autotestspace/";
            File testCode = new File(filePath + "test.mx");

            InputStream testCodeStream;
            if (testOnline)
                testCodeStream = System.in;
            else
                testCodeStream = new FileInputStream(testCode);

            // get lexer
            MxStarLexer lexer = new MxStarLexer(CharStreams.fromStream(testCodeStream));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxStarErrorListener());

            // get parser
            MxStarParser parser = new MxStarParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxStarErrorListener());

            // -------------------------------------------------------
            ParseTree treeRoot = parser.program();
            ASTBuilder astBuilder = new ASTBuilder(false);
            ProgramNode ast = (ProgramNode) astBuilder.visit(treeRoot);

            GlobalScope gScope = new GlobalScope();
            ProgInit progInit = new ProgInit(gScope);
            progInit.visit(ast);

            SemanticChecker semanticChecker = new SemanticChecker(gScope);
            semanticChecker.visit(ast);

            // -------------------------------------------------------

            var irModule = new Middleender().run(ast, gScope);
            if (!testOnline) {
                IRPrinter irPrinter = new IRPrinter(filePath + "test.ll");
                irPrinter.runOnIRModule(irModule);
            }

            // -------------------------------------------------------
            ASMModule asmModule = new ASMBuiler().buildAsm(irModule);
            new BfRegAllocator().runOnASMModule(asmModule);
            new StackAllocator().runOnASMModule(asmModule);

            if (testOnline) {
                new ASMFormatter().printASM("output.s", asmModule);
                new BuiltinPrinter().printBuiltin();
            } else
                new ASMFormatter().printASM(filePath + "test.s", asmModule);
        } catch (BaseError e) {
            System.err.println(e.toString());
            throw new RuntimeException();
        }
    }
}
