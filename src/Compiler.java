import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ASM.ASMModule;
import AST.ProgramNode;
import AST.Scopes.GlobalScope;
import Backend.ASMBuiler;
import Backend.ASMPrinter;
import Backend.RegAllocate.MyOpt;
import Backend.RegAllocate.RegisterColoring;
import Backend.RegAllocate.StackAllocator;
import Frontend.ASTBuilder;
import Frontend.ProgInit;
import Frontend.SemanticChecker;
import Frontend.MxStar.MxStarErrorListener;
import Frontend.MxStar.MxStarErrors.BaseError;
import IR.IRModule;
import Middleend.IRBuilder;
import Middleend.IRPrinter;
import Middleend.IRRenamer;

import org.antlr.v4.runtime.CharStreams;

import Parser.MxStarLexer;
import Parser.MxStarParser;
import Share.Builtin.BuiltinPrinter;

public class Compiler {
    public static void main(String[] args) throws Exception {
        try {
            boolean testManual = false;
            boolean testOnline = true;

            // File bugs = new File("debug/debug.txt");
            // PrintStream ps = new PrintStream(bugs);
            // System.setErr(ps);

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

            IRBuilder irBuilder = new IRBuilder(ast, gScope);
            IRModule irModule = irBuilder.buildIR();
            IRRenamer irRenamer = new IRRenamer();
            irRenamer.runOnIRModule(irModule);
            if (!testOnline) {
                IRPrinter irPrinter = new IRPrinter(filePath + "test.ll");
                irPrinter.runOnIRModule(irModule);
            }

            // -------------------------------------------------------

            ASMModule asmModule = new ASMBuiler().buildAsm(irModule);
            new RegisterColoring().runOnASMModule(asmModule);
            // new BfRegAllocator().runOnASMModule(asmModule);

            new MyOpt().runOnASMModule(asmModule);

            new StackAllocator().runOnASMModule(asmModule);

            if (testOnline) {
                new ASMPrinter().printASM("output.s", asmModule);
                new BuiltinPrinter().printBuiltin();
            } else
                new ASMPrinter().printASM(filePath + "test.s", asmModule);

        } catch (BaseError e) {
            System.err.println(e.toString());
            throw new RuntimeException();
        }
    }
}
