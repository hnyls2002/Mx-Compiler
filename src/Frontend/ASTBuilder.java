package Frontend;

import AST.ASTNode;
import AST.ProgramNode;
import AST.Expr.AssignExprNode;
import AST.Expr.BinaryOpExprNode;
import AST.Expr.CommaExprNode;
import AST.Expr.CreatorExprNode;
import AST.Expr.ExprNode;
import AST.Expr.FuncCallExprNode;
import AST.Expr.IdentiExprNode;
import AST.Expr.LambdaExprNode;
import AST.Expr.LiteralExprNode;
import AST.Expr.MemberExprNode;
import AST.Expr.SubscExprNode;
import AST.Expr.ThisExprNode;
import AST.Expr.UnaryOpExprNode;
import AST.Expr.BinaryOpExprNode.binaryOp;
import AST.Expr.LiteralExprNode.literalType;
import AST.Expr.UnaryOpExprNode.unaryOp;
import AST.Stmt.BreakStmtNode;
import AST.Stmt.ClassDeclStmtNode;
import AST.Stmt.ContinueStmtNode;
import AST.Stmt.EmptyStmtNode;
import AST.Stmt.ExprStmtNode;
import AST.Stmt.ForStmtNode;
import AST.Stmt.FuncDeclrStmtNode;
import AST.Stmt.IfStmtNode;
import AST.Stmt.ReturnStmtNode;
import AST.Stmt.SelfConstructNode;
import AST.Stmt.StmtNode;
import AST.Stmt.SuiteStmtNode;
import AST.Stmt.VarDeclStmtNode;
import AST.Stmt.WhileStmtNode;
import AST.Util.ParaNode;
import AST.Util.TypeNode;
import AST.Util.VarSingleDecNode;
import Parser.MxStarParser;
import Parser.MxStarParserBaseVisitor;
import Parser.MxStarParser.BinaryExprContext;
import Parser.MxStarParser.ExprStmtContext;
import Parser.MxStarParser.UnaryPrefixExprContext;
import Parser.MxStarParser.UnarySuffixExprContext;
import Util.Position;
import Util.MxStarErrors.SyntaxError;

// This definition is used to check whether there are some methods missed

//public class ASTBuilder extends org.antlr.v4.runtime.tree.AbstractParseTreeVisitor<ASTNode>
//        implements Parser.MxStarParserVisitor<ASTNode> {

public class ASTBuilder extends MxStarParserBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitProgram(MxStarParser.ProgramContext ctx) {
        ProgramNode cur = new ProgramNode(new Position(ctx));
        for (int i = 0; i < ctx.funcDeclar().size(); ++i)
            cur.funcList.add((FuncDeclrStmtNode) visit(ctx.funcDeclar(i)));

        for (int i = 0; i < ctx.varDeclar().size(); ++i)
            cur.globalVarList.add((VarDeclStmtNode) visit(ctx.varDeclar(i)));

        for (int i = 0; i < ctx.classDeclar().size(); ++i)
            cur.classList.add((ClassDeclStmtNode) visit(ctx.classDeclar(i)));

        return cur;
    }

    @Override
    public ASTNode visitSuite(MxStarParser.SuiteContext ctx) {
        SuiteStmtNode cur = new SuiteStmtNode(new Position(ctx));
        for (int i = 0; i < ctx.statement().size(); ++i)
            cur.StmtList.add((StmtNode) visit(ctx.statement(i)));
        return cur;
    }

    @Override
    public ASTNode visitSuitStmt(MxStarParser.SuitStmtContext ctx) {
        return visit(ctx.suite());
    }

    @Override
    public ASTNode visitVarDeclStmt(MxStarParser.VarDeclStmtContext ctx) {
        return visit(ctx.varDeclar());
    }

    @Override
    public ASTNode visitFuncDeclStmt(MxStarParser.FuncDeclStmtContext ctx) {
        return visit(ctx.funcDeclar());
    }

    @Override
    public ASTNode visitClassDeclStmt(MxStarParser.ClassDeclStmtContext ctx) {
        return visit(ctx.classDeclar());
    }

    @Override
    public ASTNode visitIfStmt(MxStarParser.IfStmtContext ctx) {
        IfStmtNode cur = new IfStmtNode(new Position(ctx));
        cur.expr = (ExprNode) visit(ctx.expression());
        cur.thenStmt = (StmtNode) visit(ctx.thenStmt);
        if (!ctx.elseStmt.isEmpty())
            cur.elseStmt = (StmtNode) visit(ctx.elseStmt);
        return cur;
    }

    @Override
    public ASTNode visitWhileStmt(MxStarParser.WhileStmtContext ctx) {
        WhileStmtNode cur = new WhileStmtNode(new Position(ctx));
        cur.expr = (ExprNode) visit(ctx.expression());
        cur.body = (StmtNode) visit(ctx.statement());
        return cur;
    }

    @Override
    public ASTNode visitForStmt(MxStarParser.ForStmtContext ctx) {
        ForStmtNode cur = new ForStmtNode(new Position(ctx));
        if (!ctx.initVar.isEmpty())
            cur.varDecl = (VarDeclStmtNode) visit(ctx.initVar);
        if (!ctx.initExpr.isEmpty())
            cur.initExpr = (ExprNode) visit(ctx.initExpr);
        if (!ctx.conditionExpr.isEmpty())
            cur.condExpr = (ExprNode) visit(ctx.conditionExpr);
        if (!ctx.increExpr.isEmpty())
            cur.incExpr = (ExprNode) visit(ctx.increExpr);
        cur.body = (StmtNode) visit(ctx.statement());
        return cur;
    }

    @Override
    public ASTNode visitReturnStmt(MxStarParser.ReturnStmtContext ctx) {
        ReturnStmtNode cur = new ReturnStmtNode(new Position(ctx));
        if (!ctx.expression().isEmpty())
            cur.expr = (ExprNode) visit(ctx.expression());
        return cur;
    }

    @Override
    public ASTNode visitBreakStmt(MxStarParser.BreakStmtContext ctx) {
        BreakStmtNode cur = new BreakStmtNode(new Position(ctx));
        return cur;
    }

    @Override
    public ASTNode visitContinueStmt(MxStarParser.ContinueStmtContext ctx) {
        ContinueStmtNode cur = new ContinueStmtNode(new Position(ctx));
        return cur;
    }

    @Override
    public ASTNode visitEmptyStmt(MxStarParser.EmptyStmtContext ctx) {
        EmptyStmtNode cur = new EmptyStmtNode(new Position(ctx));
        return cur;
    }

    @Override
    public ASTNode visitThisExpr(MxStarParser.ThisExprContext ctx) {
        ThisExprNode cur = new ThisExprNode(new Position(ctx));
        return cur;
    }

    @Override
    public ASTNode visitSubscriptExpr(MxStarParser.SubscriptExprContext ctx) {
        SubscExprNode cur = new SubscExprNode(new Position(ctx));
        cur.arr = (ExprNode) visit(ctx.expression(0));
        cur.sub = (ExprNode) visit(ctx.expression(1));
        return cur;
    }

    @Override
    public ASTNode visitFuncCallExpr(MxStarParser.FuncCallExprContext ctx) {
        return visit(ctx.funcCall());
    }

    @Override
    public ASTNode visitParenExpr(MxStarParser.ParenExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public ASTNode visitMemberParenExpr(MxStarParser.MemberParenExprContext ctx) {
        MemberExprNode cur = new MemberExprNode(new Position(ctx));
        cur.expr = (ExprNode) visit(ctx.expression());
        if (!ctx.funcCall().isEmpty())
            cur.funcCall = (FuncCallExprNode) visit(ctx.funcCall());
        else
            cur.idString = ctx.Identifier().toString();
        return cur;
    }

    @Override
    public ASTNode visitLambdaExpr(MxStarParser.LambdaExprContext ctx) {
        return visit(ctx.lambdaExpression());
    }

    @Override
    public ASTNode visitLiteralExpr(MxStarParser.LiteralExprContext ctx) {
        return visit(ctx.literalExpression());
    }

    @Override
    public ASTNode visitAssignExpr(MxStarParser.AssignExprContext ctx) {
        AssignExprNode cur = new AssignExprNode(new Position(ctx));
        cur.lvalue = (ExprNode) visit(ctx.expression(0));
        cur.rvalue = (ExprNode) visit(ctx.expression(1));
        return cur;
    }

    @Override
    public ASTNode visitMemberExpr(MxStarParser.MemberExprContext ctx) {
        MemberExprNode cur = new MemberExprNode(new Position(ctx));
        cur.expr = (ExprNode) visit(ctx.expression());
        if (!ctx.funcCall().isEmpty())
            cur.funcCall = (FuncCallExprNode) visit(ctx.funcCall());
        else
            cur.idString = ctx.Identifier().toString();
        return cur;
    }

    @Override
    public ASTNode visitCommaExpr(MxStarParser.CommaExprContext ctx) {
        CommaExprNode cur = new CommaExprNode(new Position(ctx));
        for (int i = 0; i < ctx.expression().size(); ++i)
            cur.exprList.add((ExprNode) visit(ctx.expression(i)));
        return cur;
    }

    @Override
    public ASTNode visitIdentifierExpr(MxStarParser.IdentifierExprContext ctx) {
        IdentiExprNode cur = new IdentiExprNode(new Position(ctx));
        cur.idString = ctx.Identifier().toString();
        return cur;
    }

    @Override
    public ASTNode visitLiteralExpression(MxStarParser.LiteralExpressionContext ctx) {
        LiteralExprNode cur = new LiteralExprNode(new Position(ctx));
        if (ctx.IntLiteral().toString() != null)
            cur.lit = literalType.INT;
        else if (ctx.StringLiteral().toString() != null)
            cur.lit = literalType.STRING;
        else if (ctx.toString().equals("true"))
            cur.lit = literalType.TRUE;
        else if (ctx.toString().equals("false"))
            cur.lit = literalType.FALSE;
        else if (ctx.toString().equals("null"))
            cur.lit = literalType.NULL;
        else
            throw new SyntaxError("Errors happened in literal value parsing", new Position(ctx));
        cur.lit.litString = ctx.getText();
        return cur;
    }

    @Override
    public ASTNode visitArrayCreator(MxStarParser.ArrayCreatorContext ctx) {
        CreatorExprNode cur = new CreatorExprNode(new Position(ctx));
        cur.typeNameString = ctx.typeNameUnit().toString();
        cur.dimen = ctx.LBracket().size() + 1;
        cur.dimenSet = ctx.expression().size() + 1;
        for (int i = 0; i < ctx.expression().size(); ++i)
            cur.dimenSize.add((ExprNode) visit(ctx.expression(i)));
        return cur;
    }

    @Override
    public ASTNode visitSingleCreator(MxStarParser.SingleCreatorContext ctx) {
        CreatorExprNode cur = new CreatorExprNode(new Position(ctx));
        cur.typeNameString = ctx.typeNameUnit().toString();
        return cur;
    }

    @Override
    public ASTNode visitLambdaExpression(MxStarParser.LambdaExpressionContext ctx) {
        LambdaExprNode cur = new LambdaExprNode(new Position(ctx));
        var paraList = ctx.parameterList();
        for (int i = 0; i < paraList.parameter().size(); ++i)
            cur.paraList.add((ParaNode) visit(paraList.parameter(i)));

        var argList = ctx.argumentList();
        for (int i = 0; i < argList.expression().size(); ++i)
            cur.argList.add((ExprNode) visit(argList.expression(i)));

        cur.body = (SuiteStmtNode) visit(ctx.suite());

        return cur;
    }

    @Override
    public ASTNode visitVarDeclar(MxStarParser.VarDeclarContext ctx) {
        VarDeclStmtNode cur = new VarDeclStmtNode(new Position(ctx));
        cur.typeName = (TypeNode) visit(ctx.typeName());
        for (int i = 0; i < ctx.varSingleDeclar().size(); ++i)
            cur.varList.add((VarSingleDecNode) visit(ctx.varSingleDeclar(i)));
        return cur;
    }

    @Override
    public ASTNode visitVarSingleDeclar(MxStarParser.VarSingleDeclarContext ctx) {
        VarSingleDecNode cur = new VarSingleDecNode(new Position(ctx));
        cur.id = ctx.Identifier().toString();
        if (!ctx.expression().isEmpty())
            cur.expr = (ExprNode) visit(ctx.expression());
        return cur;
    }

    @Override
    public ASTNode visitTypeName(MxStarParser.TypeNameContext ctx) {
        TypeNode cur = new TypeNode(new Position(ctx), ctx);
        return cur;
    }

    @Override
    public ASTNode visitTypeNameUnit(MxStarParser.TypeNameUnitContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitFuncDeclar(MxStarParser.FuncDeclarContext ctx) {
        FuncDeclrStmtNode cur = new FuncDeclrStmtNode(new Position(ctx), ctx.Identifier().toString());

        cur.retType = (TypeNode) visit(ctx.returnType());

        var parametersContext = ctx.parameterList();
        for (int i = 0; i < parametersContext.parameter().size(); ++i)
            cur.paraList.add((ParaNode) visit(parametersContext.parameter(i)));

        cur.body = (SuiteStmtNode) visit(ctx.suite());

        return cur;
    }

    @Override
    public ASTNode visitParameter(MxStarParser.ParameterContext ctx) {
        ParaNode cur = new ParaNode(new Position(ctx), (TypeNode) visit(ctx.typeName()), ctx.Identifier().toString());
        return cur;
    }

    @Override
    public ASTNode visitReturnType(MxStarParser.ReturnTypeContext ctx) {
        TypeNode cur = new TypeNode(new Position(ctx), ctx);
        return cur;
    }

    @Override
    public ASTNode visitFuncCall(MxStarParser.FuncCallContext ctx) {
        FuncCallExprNode cur = new FuncCallExprNode(new Position(ctx));
        cur.FuncNameString = ctx.Identifier().toString();
        var argList = ctx.argumentList();
        for (int i = 0; i < argList.expression().size(); ++i)
            cur.argList.add((ExprNode) visit(argList.expression(i)));
        return cur;
    }

    @Override
    public ASTNode visitClassDeclar(MxStarParser.ClassDeclarContext ctx) {
        ClassDeclStmtNode cur = new ClassDeclStmtNode(new Position(ctx));
        cur.classNameString = ctx.Identifier().toString();
        for (int i = 0; i < ctx.varDeclar().size(); ++i)
            cur.varDeclList.add((VarDeclStmtNode) visit(ctx.varDeclar(i)));

        for (int i = 0; i < ctx.funcDeclar().size(); ++i)
            cur.funcDeclList.add((FuncDeclrStmtNode) visit(ctx.funcDeclar(i)));

        if (ctx.selfConstructor().size() > 1)
            throw new SyntaxError("Multiple Constructors For One Class", new Position(ctx));

        if (!ctx.selfConstructor().isEmpty())
            cur.constructor = (SelfConstructNode) visit(ctx.selfConstructor(0));
        return cur;
    }

    @Override
    public ASTNode visitSelfConstructor(MxStarParser.SelfConstructorContext ctx) {
        SelfConstructNode cur = new SelfConstructNode(new Position(ctx));
        cur.ClassNameString = ctx.Identifier().toString();
        cur.body = (SuiteStmtNode) visit(ctx.suite());
        return cur;
    }

    @Override
    public ASTNode visitExprStmt(ExprStmtContext ctx) {
        ExprStmtNode cur = new ExprStmtNode(new Position(ctx));
        cur.expr = (ExprNode) visit(ctx.expression());
        return cur;
    }

    @Override
    public ASTNode visitUnaryPrefixExpr(UnaryPrefixExprContext ctx) {
        UnaryOpExprNode cur = new UnaryOpExprNode(new Position(ctx));
        cur.expr = (ExprNode) visit(ctx.expression());
        cur.opCode = switch (ctx.op.getType()) {
            case MxStarParser.Inc -> unaryOp.PRE_INC;
            case MxStarParser.Dec -> unaryOp.PRE_DEC;
            case MxStarParser.Add -> unaryOp.PRE_ADD;
            case MxStarParser.Sub -> unaryOp.PRE_SUB;
            case MxStarParser.LogicNot -> unaryOp.LOGIC_NOT;
            case MxStarParser.BitNot -> unaryOp.BIT_NOT;
            default ->
                throw new SyntaxError("Errors happened in unary prefx operator", new Position(ctx));
        };
        return cur;
    }

    @Override
    public ASTNode visitUnarySuffixExpr(UnarySuffixExprContext ctx) {
        UnaryOpExprNode cur = new UnaryOpExprNode(new Position(ctx));
        cur.expr = (ExprNode) visit(ctx.expression());
        cur.opCode = switch (ctx.op.getType()) {
            case MxStarParser.Inc -> unaryOp.SUF_INC;
            case MxStarParser.Dec -> unaryOp.SUF_DEC;
            default ->
                throw new SyntaxError("Errors happened in unary suffix operator", new Position(ctx));
        };
        return cur;
    }

    @Override
    public ASTNode visitBinaryExpr(BinaryExprContext ctx) {
        BinaryOpExprNode cur = new BinaryOpExprNode(new Position(ctx));
        cur.lhs = (ExprNode) visit(ctx.expression(0));
        cur.rhs = (ExprNode) visit(ctx.expression(1));
        cur.opcode = switch (ctx.op.getType()) {
            case MxStarParser.Mul -> binaryOp.MUL;
            case MxStarParser.Div -> binaryOp.DIV;
            case MxStarParser.Mod -> binaryOp.MOD;
            case MxStarParser.Add -> binaryOp.ADD;
            case MxStarParser.Sub -> binaryOp.SUB;
            case MxStarParser.ShiftLeft -> binaryOp.SHIFT_LEFT;
            case MxStarParser.ShiftRight -> binaryOp.SHIFT_RIGHT;
            case MxStarParser.Less -> binaryOp.LESS;
            case MxStarParser.LessEqual -> binaryOp.LESS_EQUAL;
            case MxStarParser.Greater -> binaryOp.GREATER;
            case MxStarParser.GreaterEqual -> binaryOp.GREATER_EQUAL;
            case MxStarParser.Equal -> binaryOp.EQUAL;
            case MxStarParser.NotEqual -> binaryOp.NOT_EQUAL;
            case MxStarParser.BitAnd -> binaryOp.BIT_AND;
            case MxStarParser.BitXor -> binaryOp.BIT_XOR;
            case MxStarParser.BitOr -> binaryOp.BIT_OR;
            case MxStarParser.LogicAnd -> binaryOp.LOGIC_AND;
            case MxStarParser.LogicOr -> binaryOp.LOGIC_OR;
            default -> throw new SyntaxError("Errors happened in binary operator", new Position(ctx));
        };
        return cur;
    }
}