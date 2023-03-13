package Frontend;

import java.io.InputStream;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import AST.ASTNode;
import AST.ProgramNode;
import AST.Scopes.GlobalScope;
import Frontend.MxStar.MxStarErrorListener;

import org.antlr.v4.runtime.CharStreams;

import Parser.MxStarLexer;
import Parser.MxStarParser;

public class Frontender {
    public ASTNode run(InputStream testCodeStream, GlobalScope gScope) throws Exception {
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

        ProgInit progInit = new ProgInit(gScope);
        progInit.visit(ast);

        SemanticChecker semanticChecker = new SemanticChecker(gScope);
        semanticChecker.visit(ast);

        return ast;
    }

}
