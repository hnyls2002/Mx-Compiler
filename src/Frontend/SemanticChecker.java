package Frontend;

import AST.ASTVisitor;
import AST.ProgramNode;
import AST.Expr.AssignExprNode;
import AST.Expr.BinaryOpExprNode;
import AST.Expr.CreatorExprNode;
import AST.Expr.FuncCallExprNode;
import AST.Expr.IdentiExprNode;
import AST.Expr.LambdaExprNode;
import AST.Expr.LiteralExprNode;
import AST.Expr.MemberExprNode;
import AST.Expr.SubscExprNode;
import AST.Expr.ThisExprNode;
import AST.Expr.UnaryOpExprNode;
import AST.Expr.UnaryOpExprNode.unaryOp;
import AST.Info.ClassType;
import AST.Info.TypeIdPair;
import AST.Scopes.GlobalScope;
import AST.Scopes.Scope;
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
import AST.Stmt.SuiteStmtNode;
import AST.Stmt.VarDeclStmtNode;
import AST.Stmt.WhileStmtNode;
import AST.Types.ArrayType;
import AST.Types.BaseType;
import AST.Util.Position;
import AST.Util.TypeName;
import Frontend.MxStar.MxStarErrors.BaseError;
import Frontend.MxStar.MxStarErrors.SemanticError;

public class SemanticChecker implements ASTVisitor {

    public GlobalScope gScope = null;

    public Scope curScope = null;

    public boolean isMember = false;
    public BaseType curMaster = null;
    public int inLoop = 0;

    public SemanticChecker(GlobalScope gScope) {
        this.gScope = gScope;
    }

    @Override
    public void visit(ProgramNode it) {
        curScope = gScope;

        // check the main function
        var mainFun = gScope.getFuncInfo("main", new Position(0, 0));
        if (!mainFun.retType.equals(gScope.intName))
            throw new SemanticError("main function can only be int type", mainFun.pos);
        if (mainFun.paraList.size() != 0)
            throw new SemanticError("main function cannot have parameters", mainFun.pos);

        it.blocks.forEach(v -> v.accept(this));

    }

    @Override
    public void visit(ClassDeclStmtNode it) {
        curScope = new Scope(curScope);

        // check var declares and put it into scope
        it.varDeclList.forEach(v -> v.accept(this));

        // put this into a field
        TypeName nowTypeName = new TypeName(it.classNameString, 0, false, it.pos);
        curScope.putDef(new TypeIdPair(nowTypeName, "this", it.pos));

        // put func
        var typeClass = gScope.getType(it.classNameString, it.pos);
        typeClass.funMap.forEach((s, v) -> curScope.putFunc(v));

        if (it.constructor != null) {
            it.constructor.body.accept(this);
            if (it.constructor.body.retStmtType != null && !it.constructor.body.retStmtType.equals(gScope.voidName))
                throw new SemanticError("Cannot have non-void return statement in a constructor ", it.constructor.pos);
        }

        it.funcDeclList.forEach(v -> {
            v.accept(this);
        });

        curScope = curScope.parent;
    }

    @Override
    public void visit(VarDeclStmtNode it) {
        for (var singleTypeDefNode : it.varList)
            visit(singleTypeDefNode);
    }

    @Override
    public void visit(FuncDeclrStmtNode it) {
        curScope = new Scope(curScope);

        // check return type
        gScope.getType(it.retType.typeNameString, it.retType.pos);

        it.paraList.forEach(pair -> {
            // check parameters type
            gScope.getType(pair.typeName.toString(), pair.typeName.pos);
            curScope.putDef(pair);
        });

        boolean haveReturn = false;

        // -------------------------------------------------------------
        it.body.accept(this);

        if (it.body.retStmtType != null) {
            haveReturn = true;
            if (it.retType.typeNameString.equals("void")) {
                if (!it.body.retStmtType.equals(gScope.voidName))
                    throw new SemanticError("Void function cannot return a no-void expression",
                            it.body.pos);
            } else if (!it.body.retStmtType.equals(it.retType)) {
                throw new SemanticError("Return type of the function doesn't match ",
                        it.body.pos);
            }
        }
        // -------------------------------------------------------------

        // for (var stmt : it.body.StmtList) {
        // stmt.accept(this);
        // if (stmt.retStmtType != null) {
        // haveReturn = true;
        // if (it.retType.typeNameString.equals("void")) {
        // if (!stmt.retStmtType.equals(gScope.voidName))
        // throw new SemanticError("Void function cannot return a no-void expression",
        // stmt.pos);
        // } else if (!stmt.retStmtType.equals(it.retType)) {
        // throw new SemanticError("Return type of the function doesn't match ",
        // stmt.pos);
        // }
        // }
        // }

        // -------------------------------------------------------------

        if (!it.retType.equals(gScope.voidName) && !haveReturn && !it.funcNameString.equals("main"))
            throw new SemanticError("Non-void type function must have a return statement ", it.pos);

        curScope = curScope.parent;
    }

    @Override
    public void visit(CreatorExprNode it) {
        it.dimenSize.forEach(v -> {
            v.accept(this);
            if (!v.typeName.equals(gScope.intName))
                throw new SemanticError("Subsript can only be int type ", v.pos);
        });
        // check type
        gScope.getType(it.typeNameString, it.pos);
        it.typeName = new TypeName(it.typeNameString, it.dimen, true);
    }

    @Override
    public void visit(BinaryOpExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        switch (it.opcode) {
            case ADD:
                if (it.lhs.typeName.equals(gScope.stringName) || it.rhs.typeName.equals(gScope.stringName)) {
                    if (!it.lhs.typeName.equals(gScope.stringName) || !it.rhs.typeName.equals(gScope.stringName))
                        throw new SemanticError("Operator " + it.opcode.toString() + " both side should be string ",
                                it.pos);
                    else
                        it.typeName = gScope.stringName;
                    break;
                }
            case MUL:
            case DIV:
            case MOD:
            case SUB:
            case SHIFT_LEFT:
            case SHIFT_RIGHT:
                if (!it.lhs.typeName.equals(gScope.intName) || !it.rhs.typeName.equals(gScope.intName)) {
                    throw new SemanticError("Operator " + it.opcode.toString() + " can only be used on int types ",
                            it.pos);
                }
                it.typeName = gScope.intName;
                break;

            case LESS:
            case LESS_EQUAL:
            case GREATER:
            case GREATER_EQUAL:

                if (it.lhs.typeName.equals(gScope.stringName) || it.rhs.typeName.equals(gScope.stringName)) {
                    if (!it.lhs.typeName.equals(gScope.stringName) || !it.rhs.typeName.equals(gScope.stringName))
                        throw new SemanticError("Operator " + it.opcode.toString() + " both side should be string ",
                                it.pos);
                    else
                        it.typeName = gScope.boolName;
                    break;
                }

                if (!it.lhs.typeName.equals(gScope.intName) || !it.rhs.typeName.equals(gScope.intName))
                    throw new SemanticError("Operator " + it.opcode.toString() + " can only be used on int types ",
                            it.pos);
                it.typeName = gScope.boolName;
                break;

            case EQUAL:
            case NOT_EQUAL:
                if (!it.lhs.typeName.equals(it.rhs.typeName))
                    throw new SemanticError("== and != , both sides should be the same type! ", it.pos);

                it.typeName = gScope.boolName;

                break;
            case BIT_AND:
            case BIT_XOR:
            case BIT_OR:
                if (!it.lhs.typeName.equals(gScope.intName) || !it.rhs.typeName.equals(gScope.intName))
                    throw new SemanticError("Operator " + it.opcode.toString() + " can only be used on int types ",
                            it.pos);
                it.typeName = gScope.intName;
                break;

            case LOGIC_AND:
            case LOGIC_OR:
                if (!it.lhs.typeName.equals(gScope.boolName) || !it.rhs.typeName.equals(gScope.boolName))
                    throw new SemanticError("Operator " + it.opcode.toString() + " can only be used on bool types ",
                            it.pos);
                it.typeName = gScope.boolName;
                break;

            default:
                break;
        }
    }

    @Override
    public void visit(UnaryOpExprNode it) {
        it.expr.accept(this);
        switch (it.opCode) {
            case PRE_INC:
            case PRE_DEC:
            case SUF_INC:
            case SUF_DEC:
                if (!it.expr.typeName.isLeftValue)
                    throw new SemanticError("operator " + it.opCode.toString() + " can only be used on left-value ",
                            it.pos);
                if (!it.expr.typeName.equals(gScope.intName)) {
                    throw new SemanticError("operator " + it.opCode.toString() + " can only be used on int types",
                            it.pos);
                }
                if (it.opCode == unaryOp.PRE_INC || it.opCode == unaryOp.PRE_DEC)
                    it.typeName = new TypeName("int", 0, true, it.pos);
                else
                    it.typeName = gScope.intName;
                break;
            case LOGIC_NOT:
                if (!it.expr.typeName.equals(gScope.boolName))
                    throw new SemanticError("Logic operator can only be use on bool type expressions ", it.pos);
                it.typeName = gScope.boolName;
                break;
            case BIT_NOT:
            case PRE_ADD:
            case PRE_SUB:
                if (!it.expr.typeName.equals(gScope.intName))
                    throw new SemanticError("operator " + it.opCode.toString() + " can only be used on int types",
                            it.pos);
                it.typeName = gScope.intName;
                break;
            default:
                break;
        }
    }

    @Override
    public void visit(AssignExprNode it) {
        it.rvalue.accept(this);
        it.lvalue.accept(this);
        if (!it.lvalue.typeName.isLeftValue)
            throw new SemanticError("Assignment's left side must be a left value ", it.pos);
        if (!it.lvalue.typeName.equals(it.rvalue.typeName))
            throw new SemanticError("Assignment both sides' should have the same type ", it.pos);
        it.typeName = it.lvalue.typeName;
    }

    @Override
    public void visit(SubscExprNode it) {
        it.arr.accept(this);
        it.sub.accept(this);
        int dim = it.arr.typeName.dimen;
        if (dim == 0)
            throw new SemanticError("Expression is not a array type ", it.arr.pos);
        if (!it.sub.typeName.equals(gScope.intName))
            throw new SemanticError("Subscript can only be int type", it.sub.pos);

        it.typeName = new TypeName(it.arr.typeName.typeNameString, dim - 1, it.arr.typeName.isLeftValue, it.pos);
    }

    @Override
    public void visit(FuncCallExprNode it) {

        var func = isMember ? curMaster.getFunc(it.FuncNameString, it.pos)
                : curScope.getFuncInfo(it.FuncNameString, it.pos);

        // since got the function, you don't need the specific field anymore
        // you will find the arguments : in basic-55
        isMember = false;

        if (func.paraList.size() != it.argList.size())
            throw new SemanticError("Arguments number is not match function " +
                    it.FuncNameString,
                    it.pos);

        int siz = func.paraList.size();
        for (int i = 0; i < siz; ++i) {
            it.argList.get(i).accept(this);
            if (!func.paraList.get(i).typeName.equals(it.argList.get(i).typeName))
                throw new SemanticError("Argument's type doesn't match ", it.pos);
        }

        it.typeName = func.retType;

    }

    @Override
    public void visit(SelfConstructNode it) {
        curScope = new Scope(curScope);
        it.body.accept(this);
        curScope = curScope.parent;
    }

    @Override
    public void visit(ExprStmtNode it) {
        it.expr.accept(this);
    }

    @Override
    public void visit(IfStmtNode it) {
        it.expr.accept(this);
        if (!it.expr.typeName.equals(gScope.boolName)) {
            throw new SemanticError("Condition expression must be bool type ", it.expr.pos);

        }
        curScope = new Scope(curScope);
        it.thenStmt.accept(this);
        curScope = curScope.parent;

        it.retStmtType = it.thenStmt.retStmtType;

        if (it.elseStmt != null) {
            curScope = new Scope(curScope);
            it.elseStmt.accept(this);
            if (it.retStmtType != null && it.elseStmt.retStmtType != null) {
                if (!it.retStmtType.equals(it.elseStmt.retStmtType))
                    throw new SemanticError("return types different in if and else blocks ", it.pos);
            }
            if (it.elseStmt.retStmtType != null)
                it.retStmtType = it.elseStmt.retStmtType;

            curScope = curScope.parent;
        }
    }

    @Override
    public void visit(WhileStmtNode it) {
        it.expr.accept(this);
        if (!it.expr.typeName.equals(gScope.boolName))
            throw new SemanticError("Condition expression must be bool type ", it.expr.pos);

        curScope = new Scope(curScope);
        ++inLoop;

        it.body.accept(this);
        it.retStmtType = it.body.retStmtType;

        curScope = curScope.parent;
        --inLoop;
    }

    @Override
    public void visit(ForStmtNode it) {
        curScope = new Scope(curScope);
        ++inLoop;

        if (it.varDecl != null)
            it.varDecl.accept(this);
        else if (it.initExpr != null)
            it.initExpr.accept(this);

        if (it.condExpr != null) {
            it.condExpr.accept(this);
            if (!it.condExpr.typeName.equals(gScope.boolName))
                throw new SemanticError("Condition expression must be bool type ", it.condExpr.pos);
        }

        if (it.incExpr != null)
            it.incExpr.accept(this);

        it.body.accept(this);
        it.retStmtType = it.body.retStmtType;

        curScope = curScope.parent;
        --inLoop;
    }

    @Override
    public void visit(ReturnStmtNode it) {
        if (it.expr != null) {
            it.expr.accept(this);
            it.retStmtType = it.expr.typeName;
        } else
            it.retStmtType = gScope.voidName;
    }

    @Override
    public void visit(ContinueStmtNode it) {
        if (inLoop == 0)
            throw new SemanticError("Continue can only be in loop suite ", it.pos);

    }

    @Override
    public void visit(BreakStmtNode it) {
        if (inLoop == 0)
            throw new SemanticError("Break can only be in loop suite ", it.pos);

    }

    @Override
    public void visit(MemberExprNode it) {
        it.expr.accept(this);

        var typeNameL = it.expr.typeName;
        var uniType = gScope.getType(typeNameL.typeNameString, typeNameL.pos);
        BaseType ctype = null;
        if (typeNameL.dimen == 0)
            ctype = uniType;
        else
            ctype = new ArrayType(gScope, uniType, typeNameL.dimen, it.expr.pos);

        isMember = true;
        curMaster = ctype;

        if (it.funcCall != null) { // function call
            it.funcCall.accept(this);
            it.typeName = it.funcCall.typeName;
        } else if (it.idExpr != null) { // fields
            it.idExpr.accept(this);
            it.typeName = it.idExpr.typeName;
        }

        isMember = false;
        curMaster = null;

    }

    @Override
    public void visit(LambdaExprNode it) {
        if (it.argList.size() != it.paraList.size())
            throw new SemanticError("Lambda's arguments number don't match its parameters ", it.pos);

        it.argList.forEach(v -> v.accept(this));

        int siz = it.argList.size();
        for (int i = 0; i < siz; ++i) {
            if (!it.argList.get(i).typeName.equals(it.paraList.get(i).typeName))
                throw new SemanticError("Lambda's arguments type don't match its parameters ", it.pos);
        }

        curScope = new Scope(curScope);
        it.paraList.forEach(v -> {
            curScope.putDef(v);
        });

        it.body.accept(this);
        if (it.body.retStmtType == null)
            throw new SemanticError("Lambda should have a return statement", it.pos);

        it.typeName = it.body.retStmtType;

        curScope = curScope.parent;

    }

    @Override
    public void visit(ThisExprNode it) {

        try {
            it.typeName = curScope.getDef("this", it.pos).typeName;
        } catch (BaseError e) {
            throw new SemanticError("This can only be used in function in class ", it.pos);
        }

    }

    @Override
    public void visit(LiteralExprNode it) {
        it.typeName = switch (it.lit) {
            case INT -> gScope.intName;
            case STRING -> gScope.stringName;
            case NULL -> gScope.nullName;
            case FALSE -> gScope.boolName;
            case TRUE -> gScope.boolName;
            default -> throw new SemanticError("Unknown Error in literal expression ", it.pos);
        };

    }

    @Override
    public void visit(SuiteStmtNode it) {
        curScope = new Scope(curScope);
        it.StmtList.forEach(v -> {
            v.accept(this);
            if (v.retStmtType != null) {
                if (it.retStmtType != null && !it.retStmtType.equals(v.retStmtType))
                    throw new SemanticError("Different return types ", it.pos);
                it.retStmtType = v.retStmtType;
            }
        });
        curScope = curScope.parent;

    }

    @Override
    public void visit(EmptyStmtNode it) {
        // fuck

    }

    @Override
    public void visit(IdentiExprNode it) {
        if (isMember) {
            if (curMaster instanceof ClassType typeClass) {
                var fd = typeClass.getDef(it.idString, it.pos);
                it.typeName = fd.typeName;
            } else
                throw new SemanticError("The dot can't be used at a non-class type ", it.pos);
        } else {
            var idenDef = curScope.getDef(it.idString, it.pos);
            it.typeName = idenDef.typeName;
        }

    }

    @Override
    public void visit(SingleVarDeclStmtNode it) {
        var pair = it.decl;

        // check type
        gScope.getType(pair.typeName.typeNameString, pair.pos);

        if (it.expr != null) {
            it.expr.accept(this);
            if (!pair.typeName.equals(it.expr.typeName))
                throw new SemanticError("lhs and rhs don't have the same type ", it.pos);
        }

        curScope.putDef(pair);
    }

}
