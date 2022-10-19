package Frontend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

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
import AST.Stmt.SingleVarDeclStmtNode;
import AST.Stmt.StmtNode;
import AST.Stmt.SuiteStmtNode;
import AST.Stmt.VarDeclStmtNode;
import AST.Stmt.WhileStmtNode;
import Parser.MxStarParser;
import Parser.MxStarParserBaseVisitor;
import Parser.MxStarParser.ArrayCreatorContext;
import Parser.MxStarParser.Binary10ExprContext;
import Parser.MxStarParser.Binary1ExprContext;
import Parser.MxStarParser.Binary2ExprContext;
import Parser.MxStarParser.Binary3ExprContext;
import Parser.MxStarParser.Binary4ExprContext;
import Parser.MxStarParser.Binary5ExprContext;
import Parser.MxStarParser.Binary6ExprContext;
import Parser.MxStarParser.Binary7ExprContext;
import Parser.MxStarParser.Binary8ExprContext;
import Parser.MxStarParser.Binary9ExprContext;
import Parser.MxStarParser.ClassDeclarContext;
import Parser.MxStarParser.ExprStmtContext;
import Parser.MxStarParser.FuncDeclarContext;
import Parser.MxStarParser.Unary1ExprContext;
import Parser.MxStarParser.Unary2ExprContext;
import Parser.MxStarParser.VarDeclarContext;
import Util.Position;
import Util.TypeIdPair;
import Util.TypeName;
import Util.MxStarErrors.SyntaxError;

// This definition is used to check whether there are some methods missed

//public class ASTBuilder extends org.antlr.v4.runtime.tree.AbstractParseTreeVisitor<ASTNode>
//        implements Parser.MxStarParserVisitor<ASTNode> {

public class ASTBuilder extends MxStarParserBaseVisitor<ASTNode> {
    public boolean isDebugging;

    public ASTBuilder(boolean flag) throws FileNotFoundException {
        isDebugging = flag;
        if (flag) {
            // for debug
            File bugs = new File("bugs.txt");
            PrintStream ps = new PrintStream(bugs);
            System.setErr(ps);
        }

    }

    @Override
    public ASTNode visitProgram(MxStarParser.ProgramContext ctx) {
        if (isDebugging) {
            System.err.println("Program-Start");
        }

        ProgramNode cur = new ProgramNode(new Position(ctx));

        for (int i = 0; i < ctx.children.size(); ++i) {
            var ch = ctx.children.get(i);

            if (ch instanceof FuncDeclarContext)
                cur.blocks.add(visit(ch));
            else if (ch instanceof VarDeclarContext)
                cur.blocks.add(visit(ch));
            else if (ch instanceof ClassDeclarContext)
                cur.blocks.add(visit(ch));
        }

        if (isDebugging) {
            System.err.println("Program-End");
        }

        return cur;

    }

    @Override
    public ASTNode visitSuite(MxStarParser.SuiteContext ctx) {
        if (isDebugging) {
            System.err.println("A suite");
        }

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
        if (isDebugging) {
            System.err.println("If");
        }
        IfStmtNode cur = new IfStmtNode(new Position(ctx));
        cur.expr = (ExprNode) visit(ctx.expression());
        cur.thenStmt = (StmtNode) visit(ctx.thenStmt);
        if (ctx.elseStmt != null)
            cur.elseStmt = (StmtNode) visit(ctx.elseStmt);
        return cur;
    }

    @Override
    public ASTNode visitWhileStmt(MxStarParser.WhileStmtContext ctx) {
        if (isDebugging) {
            System.err.println("While");
        }
        WhileStmtNode cur = new WhileStmtNode(new Position(ctx));
        cur.expr = (ExprNode) visit(ctx.expression());
        cur.body = (StmtNode) visit(ctx.statement());
        return cur;
    }

    @Override
    public ASTNode visitForStmt(MxStarParser.ForStmtContext ctx) {
        if (isDebugging) {
            System.err.println("For");
        }
        ForStmtNode cur = new ForStmtNode(new Position(ctx));
        if (ctx.initVar != null)
            cur.varDecl = (VarDeclStmtNode) visit(ctx.initVar);
        if (ctx.initExpr != null)
            cur.initExpr = (ExprNode) visit(ctx.initExpr);
        if (ctx.conditionExpr != null)
            cur.condExpr = (ExprNode) visit(ctx.conditionExpr);
        if (ctx.increExpr != null)
            cur.incExpr = (ExprNode) visit(ctx.increExpr);
        cur.body = (StmtNode) visit(ctx.statement());
        return cur;
    }

    @Override
    public ASTNode visitReturnStmt(MxStarParser.ReturnStmtContext ctx) {
        if (isDebugging) {
            System.err.println("Return");
        }
        ReturnStmtNode cur = new ReturnStmtNode(new Position(ctx));
        if (ctx.expression() != null)
            cur.expr = (ExprNode) visit(ctx.expression());
        return cur;
    }

    @Override
    public ASTNode visitBreakStmt(MxStarParser.BreakStmtContext ctx) {
        if (isDebugging) {
            System.err.println("Break");
        }
        BreakStmtNode cur = new BreakStmtNode(new Position(ctx));
        return cur;
    }

    @Override
    public ASTNode visitContinueStmt(MxStarParser.ContinueStmtContext ctx) {
        if (isDebugging) {
            System.err.println("Continue");
        }
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
        if (isDebugging) {
            System.err.println("This");
        }
        ThisExprNode cur = new ThisExprNode(new Position(ctx));
        return cur;
    }

    @Override
    public ASTNode visitSubscriptExpr(MxStarParser.SubscriptExprContext ctx) {
        if (isDebugging) {
            System.err.println("Subscript");
        }
        SubscExprNode cur = new SubscExprNode(new Position(ctx));

        if (ctx.expression(0) instanceof ArrayCreatorContext) {
            throw new SyntaxError("Creator can't be subscripted without a pair of parenthess", new Position(ctx));
        }

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
        if (isDebugging) {
            System.err.println("Member Dot");
        }
        MemberExprNode cur = new MemberExprNode(new Position(ctx));
        cur.expr = (ExprNode) visit(ctx.expression());
        if (ctx.funcCall() != null)
            cur.funcCall = (FuncCallExprNode) visit(ctx.funcCall());
        else {
            cur.idExpr = new IdentiExprNode(new Position(ctx.Identifier()));
            cur.idExpr.idString = ctx.Identifier().getText();
        }
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
        if (isDebugging) {
            System.err.println("Assign");
        }
        AssignExprNode cur = new AssignExprNode(new Position(ctx));
        cur.lvalue = (ExprNode) visit(ctx.expression(0));
        cur.rvalue = (ExprNode) visit(ctx.expression(1));
        return cur;
    }

    @Override
    public ASTNode visitMemberExpr(MxStarParser.MemberExprContext ctx) {
        if (isDebugging) {
            System.err.println("Member Dot");
        }
        MemberExprNode cur = new MemberExprNode(new Position(ctx));
        cur.expr = (ExprNode) visit(ctx.expression());
        if (ctx.funcCall() != null)
            cur.funcCall = (FuncCallExprNode) visit(ctx.funcCall());
        else {
            cur.idExpr = new IdentiExprNode(new Position(ctx.Identifier()));
            cur.idExpr.idString = ctx.Identifier().getText();
        }
        return cur;
    }

    @Override
    public ASTNode visitCommaExpr(MxStarParser.CommaExprContext ctx) {
        if (isDebugging) {
            System.err.println("Comma Expr");
        }
        CommaExprNode cur = new CommaExprNode(new Position(ctx));
        for (int i = 0; i < ctx.expression().size(); ++i)
            cur.exprList.add((ExprNode) visit(ctx.expression(i)));
        return cur;
    }

    @Override
    public ASTNode visitIdentifierExpr(MxStarParser.IdentifierExprContext ctx) {
        if (isDebugging) {
            System.err.println("id_expr : " + ctx.Identifier().getText());
        }
        IdentiExprNode cur = new IdentiExprNode(new Position(ctx));
        cur.idString = ctx.Identifier().getText();
        return cur;
    }

    @Override
    public ASTNode visitLiteralExpression(MxStarParser.LiteralExpressionContext ctx) {
        if (isDebugging) {
            System.err.println("literal : " + ctx.getText());
        }
        LiteralExprNode cur = new LiteralExprNode(new Position(ctx));
        if (ctx.IntLiteral() != null)
            cur.lit = literalType.INT;
        else if (ctx.StringLiteral() != null)
            cur.lit = literalType.STRING;
        else if (ctx.getText().equals("true"))
            cur.lit = literalType.TRUE;
        else if (ctx.getText().equals("false"))
            cur.lit = literalType.FALSE;
        else if (ctx.getText().equals("null"))
            cur.lit = literalType.NULL;
        else
            throw new SyntaxError("Errors happened in literal value parsing", new Position(ctx));
        cur.lit.litString = ctx.getText();
        return cur;
    }

    @Override
    public ASTNode visitArrayCreator(MxStarParser.ArrayCreatorContext ctx) {
        if (isDebugging) {
            System.err.println("Array Create : ");
        }
        CreatorExprNode cur = new CreatorExprNode(new Position(ctx));
        cur.typeNameString = ctx.typeNameUnit().getText();
        cur.dimen = ctx.LBracket().size();
        for (int i = 0; i < ctx.expression().size(); ++i)
            cur.dimenSize.add((ExprNode) visit(ctx.expression(i)));
        return cur;
    }

    @Override
    public ASTNode visitSingleCreator(MxStarParser.SingleCreatorContext ctx) {
        if (isDebugging) {
            System.err.println("Single Create");
        }
        CreatorExprNode cur = new CreatorExprNode(new Position(ctx));
        cur.typeNameString = ctx.typeNameUnit().getText();
        return cur;
    }

    @Override
    public ASTNode visitLambdaExpression(MxStarParser.LambdaExpressionContext ctx) {
        if (isDebugging) {
            System.err.println("Lambda");
        }
        LambdaExprNode cur = new LambdaExprNode(new Position(ctx));
        var paraList = ctx.parameterList();
        for (int i = 0; i < paraList.parameter().size(); ++i)
            cur.paraList.add(new TypeIdPair(paraList.parameter(i)));

        var argList = ctx.argumentList();
        for (int i = 0; i < argList.expression().size(); ++i)
            cur.argList.add((ExprNode) visit(argList.expression(i)));

        cur.body = (SuiteStmtNode) visit(ctx.suite());

        return cur;
    }

    @Override
    public ASTNode visitVarDeclar(MxStarParser.VarDeclarContext ctx) {
        if (isDebugging) {
            System.err.println("Var Declar");
        }
        VarDeclStmtNode cur = new VarDeclStmtNode(new Position(ctx));
        cur.typeName = new TypeName(ctx.typeName(), true);
        for (int i = 0; i < ctx.varSingleDeclar().size(); ++i) {
            var sub_ctx = ctx.varSingleDeclar(i);
            var pair = new TypeIdPair(cur.typeName, sub_ctx.Identifier().getText(), new Position(sub_ctx));
            if (isDebugging) {
                System.err.println("typename : " + pair.typeName.toString() + " ,var Name : " + pair.Id + " , dime : "
                        + pair.typeName.getDimen());
            }
            var tmp = (SingleVarDeclStmtNode) visit(sub_ctx);
            tmp.decl = pair;
            cur.varList.add(tmp);
        }
        return cur;
    }

    @Override
    public ASTNode visitVarSingleDeclar(MxStarParser.VarSingleDeclarContext ctx) {
        SingleVarDeclStmtNode cur = new SingleVarDeclStmtNode(new Position(ctx));
        cur.expr = ctx.expression() == null ? null : (ExprNode) visit(ctx.expression());
        return cur;
    }

    @Override
    public ASTNode visitTypeNameUnit(MxStarParser.TypeNameUnitContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitFuncDeclar(MxStarParser.FuncDeclarContext ctx) {
        if (isDebugging) {
            System.err.println("Func-Declar ");
        }
        FuncDeclrStmtNode cur = new FuncDeclrStmtNode(new Position(ctx), ctx.Identifier().getText());

        cur.retType = new TypeName(ctx.returnType(), false);

        var parametersContext = ctx.parameterList();
        for (int i = 0; i < parametersContext.parameter().size(); ++i)
            cur.paraList.add(new TypeIdPair(parametersContext.parameter(i)));

        cur.body = (SuiteStmtNode) visit(ctx.suite());

        return cur;
    }

    @Override
    public ASTNode visitFuncCall(MxStarParser.FuncCallContext ctx) {
        if (isDebugging) {
            System.err.println("Func Call");
        }
        FuncCallExprNode cur = new FuncCallExprNode(new Position(ctx));
        cur.FuncNameString = ctx.Identifier().getText();
        var argList = ctx.argumentList();
        for (int i = 0; i < argList.expression().size(); ++i)
            cur.argList.add((ExprNode) visit(argList.expression(i)));
        return cur;
    }

    @Override
    public ASTNode visitClassDeclar(MxStarParser.ClassDeclarContext ctx) {
        if (isDebugging) {
            System.err.println("Class Declar");
        }
        ClassDeclStmtNode cur = new ClassDeclStmtNode(new Position(ctx));
        cur.classNameString = ctx.Identifier().getText();
        for (int i = 0; i < ctx.varDeclar().size(); ++i)
            cur.varDeclList.add((VarDeclStmtNode) visit(ctx.varDeclar(i)));

        for (int i = 0; i < ctx.funcDeclar().size(); ++i)
            cur.funcDeclList.add((FuncDeclrStmtNode) visit(ctx.funcDeclar(i)));

        if (ctx.selfConstructor().size() > 1)
            throw new SyntaxError("Multiple Constructors For One Class", new Position(ctx));

        if (!ctx.selfConstructor().isEmpty()) {
            cur.constructor = (SelfConstructNode) visit(ctx.selfConstructor(0));
        }

        return cur;
    }

    @Override
    public ASTNode visitSelfConstructor(MxStarParser.SelfConstructorContext ctx) {
        if (isDebugging) {
            System.err.println("Self Constructor");
        }
        SelfConstructNode cur = new SelfConstructNode(new Position(ctx));
        cur.consNameString = ctx.Identifier().getText();
        cur.body = (SuiteStmtNode) visit(ctx.suite());
        return cur;
    }

    @Override
    public ASTNode visitExprStmt(ExprStmtContext ctx) {
        if (isDebugging) {
            System.err.println("an expression-statement:");
        }
        ExprStmtNode cur = new ExprStmtNode(new Position(ctx));
        cur.expr = (ExprNode) visit(ctx.expression());
        return cur;
    }

    @Override
    public ASTNode visitBinary9Expr(Binary9ExprContext ctx) {
        if (isDebugging) {
            System.err.println("Binary Op && ");
        }
        BinaryOpExprNode cur = new BinaryOpExprNode(new Position(ctx));
        cur.lhs = (ExprNode) visit(ctx.expression(0));
        cur.rhs = (ExprNode) visit(ctx.expression(1));
        cur.opcode = switch (ctx.op.getType()) {
            case MxStarParser.LogicAnd -> binaryOp.LOGIC_AND;
            default -> throw new SyntaxError("Errors happened in binary operator", new Position(ctx));
        };
        return cur;
    }

    @Override
    public ASTNode visitBinary4Expr(Binary4ExprContext ctx) {
        if (isDebugging) {
            System.err.println("Binary Op < <= > >=");
        }
        BinaryOpExprNode cur = new BinaryOpExprNode(new Position(ctx));
        cur.lhs = (ExprNode) visit(ctx.expression(0));
        cur.rhs = (ExprNode) visit(ctx.expression(1));
        cur.opcode = switch (ctx.op.getType()) {
            case MxStarParser.Less -> binaryOp.LESS;
            case MxStarParser.LessEqual -> binaryOp.LESS_EQUAL;
            case MxStarParser.Greater -> binaryOp.GREATER;
            case MxStarParser.GreaterEqual -> binaryOp.GREATER_EQUAL;
            default -> throw new SyntaxError("Errors happened in binary operator", new Position(ctx));
        };
        return cur;
    }

    @Override
    public ASTNode visitUnary2Expr(Unary2ExprContext ctx) {
        if (isDebugging) {
            System.err.println("Prefix Op");
        }
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
    public ASTNode visitBinary5Expr(Binary5ExprContext ctx) {
        if (isDebugging) {
            System.err.println("Binary Op == != ");
        }
        BinaryOpExprNode cur = new BinaryOpExprNode(new Position(ctx));
        cur.lhs = (ExprNode) visit(ctx.expression(0));
        cur.rhs = (ExprNode) visit(ctx.expression(1));
        cur.opcode = switch (ctx.op.getType()) {
            case MxStarParser.Equal -> binaryOp.EQUAL;
            case MxStarParser.NotEqual -> binaryOp.NOT_EQUAL;
            default -> throw new SyntaxError("Errors happened in binary operator", new Position(ctx));
        };
        return cur;
    }

    @Override
    public ASTNode visitBinary1Expr(Binary1ExprContext ctx) {
        if (isDebugging) {
            System.err.println("Binary Op * / %");
        }
        BinaryOpExprNode cur = new BinaryOpExprNode(new Position(ctx));
        cur.lhs = (ExprNode) visit(ctx.expression(0));
        cur.rhs = (ExprNode) visit(ctx.expression(1));
        cur.opcode = switch (ctx.op.getType()) {
            case MxStarParser.Mul -> binaryOp.MUL;
            case MxStarParser.Div -> binaryOp.DIV;
            case MxStarParser.Mod -> binaryOp.MOD;
            default -> throw new SyntaxError("Errors happened in binary operator", new Position(ctx));
        };
        return cur;
    }

    @Override
    public ASTNode visitBinary7Expr(Binary7ExprContext ctx) {
        if (isDebugging) {
            System.err.println("Binary Op ^");
        }
        BinaryOpExprNode cur = new BinaryOpExprNode(new Position(ctx));
        cur.lhs = (ExprNode) visit(ctx.expression(0));
        cur.rhs = (ExprNode) visit(ctx.expression(1));
        cur.opcode = switch (ctx.op.getType()) {
            case MxStarParser.BitXor -> binaryOp.BIT_XOR;
            default -> throw new SyntaxError("Errors happened in binary operator", new Position(ctx));
        };
        return cur;
    }

    @Override
    public ASTNode visitUnary1Expr(Unary1ExprContext ctx) {
        if (isDebugging) {
            System.err.println("suffix Op");
        }
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
    public ASTNode visitBinary6Expr(Binary6ExprContext ctx) {
        if (isDebugging) {
            System.err.println("Binary Op &");
        }
        BinaryOpExprNode cur = new BinaryOpExprNode(new Position(ctx));
        cur.lhs = (ExprNode) visit(ctx.expression(0));
        cur.rhs = (ExprNode) visit(ctx.expression(1));
        cur.opcode = switch (ctx.op.getType()) {
            case MxStarParser.BitAnd -> binaryOp.BIT_AND;
            default -> throw new SyntaxError("Errors happened in binary operator", new Position(ctx));
        };
        return cur;
    }

    @Override
    public ASTNode visitBinary2Expr(Binary2ExprContext ctx) {
        if (isDebugging) {
            System.err.println("Binary Op");
        }
        BinaryOpExprNode cur = new BinaryOpExprNode(new Position(ctx));
        cur.lhs = (ExprNode) visit(ctx.expression(0));
        cur.rhs = (ExprNode) visit(ctx.expression(1));
        cur.opcode = switch (ctx.op.getType()) {
            case MxStarParser.Add -> binaryOp.ADD;
            case MxStarParser.Sub -> binaryOp.SUB;
            default -> throw new SyntaxError("Errors happened in binary operator", new Position(ctx));
        };
        return cur;
    }

    @Override
    public ASTNode visitBinary8Expr(Binary8ExprContext ctx) {
        if (isDebugging) {
            System.err.println("Binary Op | ");
        }
        BinaryOpExprNode cur = new BinaryOpExprNode(new Position(ctx));
        cur.lhs = (ExprNode) visit(ctx.expression(0));
        cur.rhs = (ExprNode) visit(ctx.expression(1));
        cur.opcode = switch (ctx.op.getType()) {
            case MxStarParser.BitOr -> binaryOp.BIT_OR;
            default -> throw new SyntaxError("Errors happened in binary operator", new Position(ctx));
        };
        return cur;
    }

    @Override
    public ASTNode visitBinary3Expr(Binary3ExprContext ctx) {
        if (isDebugging) {
            System.err.println("Binary Op << >>");
        }
        BinaryOpExprNode cur = new BinaryOpExprNode(new Position(ctx));
        cur.lhs = (ExprNode) visit(ctx.expression(0));
        cur.rhs = (ExprNode) visit(ctx.expression(1));
        cur.opcode = switch (ctx.op.getType()) {
            case MxStarParser.ShiftLeft -> binaryOp.SHIFT_LEFT;
            case MxStarParser.ShiftRight -> binaryOp.SHIFT_RIGHT;
            default -> throw new SyntaxError("Errors happened in binary operator", new Position(ctx));
        };
        return cur;
    }

    @Override
    public ASTNode visitBinary10Expr(Binary10ExprContext ctx) {
        if (isDebugging) {
            System.err.println("Binary Op || ");
        }
        BinaryOpExprNode cur = new BinaryOpExprNode(new Position(ctx));
        cur.lhs = (ExprNode) visit(ctx.expression(0));
        cur.rhs = (ExprNode) visit(ctx.expression(1));
        cur.opcode = switch (ctx.op.getType()) {
            case MxStarParser.LogicOr -> binaryOp.LOGIC_OR;
            default -> throw new SyntaxError("Errors happened in binary operator", new Position(ctx));
        };
        return cur;
    }

}