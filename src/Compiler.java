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
import Backend.RegAllocate.BfRegAllocator;
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

public class Compiler {
    public static void main(String[] args) throws Exception {
        try {
            boolean testIR = true;
            String filePath = testIR ? "./irtestspace/" : "./debug/";
            File testCode = new File(filePath + "test.mx");

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

            // -------------------------------------------------------

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
            IRModule irModule = irBuilder.buildIR();
            IRRenamer irRenamer = new IRRenamer();
            irRenamer.runOnIRModule(irModule);
            IRPrinter irPrinter = new IRPrinter(filePath + "test.ll");
            irPrinter.runOnIRModule(irModule);

            // -------------------------------------------------------

            ASMModule asmModule = new ASMBuiler().buildAsm(irModule);
            //new ASMPrinter().printASM(filePath + "test_debug.s", asmModule);
            new BfRegAllocator().runOnASMModule(asmModule);
            new StackAllocator().runOnASMModule(asmModule);
            new ASMPrinter().printASM(filePath + "test.s", asmModule);

        } catch (BaseError e) {
            System.err.println(e.toString());
            throw new RuntimeException();
        }
    }
}
