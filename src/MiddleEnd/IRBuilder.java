package MiddleEnd;

import java.util.ArrayList;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.ProgramNode;
import AST.Expr.AssignExprNode;
import AST.Expr.BinaryOpExprNode;
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
import AST.Expr.LiteralExprNode.literalType;
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
import Debug.MyException;
import Frontend.Util.TypeIdPair;
import Frontend.Util.TypeName;
import Frontend.Util.Scopes.GlobalScope;
import Frontend.Util.Scopes.Scope;
import Frontend.Util.Types.ArrayType;
import Frontend.Util.Types.BaseType;
import Frontend.Util.Types.ClassType;
import Frontend.Util.Types.FuncInfo;
import IR.IRModule;
import IR.IRType.IRPtType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRArgument;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.Inst.AllocaInst;
import IR.IRValue.IRUser.Inst.BinaryInst;
import IR.IRValue.IRUser.Inst.CallInst;
import IR.IRValue.IRUser.Inst.GEPInst;
import IR.IRValue.IRUser.Inst.LoadInst;
import IR.IRValue.IRUser.Inst.RetInst;
import IR.IRValue.IRUser.Inst.StoreInst;
import IR.IRValue.IRUser.Inst.BinaryInst.binaryOperator;
import IR.Util.Transfer;

public class IRBuilder implements ASTVisitor {

    private class CurStatus {
        public IRFn fn = null;
        public IRBasicBlock block = null;
        public Scope scope = null;
        public ClassType inWhichClass = null;
        public BaseType whoseMember = null;
    }

    private CurStatus cur = new CurStatus();

    public IRModule topModule;
    public ASTNode progRoot;
    public GlobalScope gScope;

    public IRBuilder(ASTNode progRoot, GlobalScope gScope) {
        this.progRoot = progRoot;
        this.gScope = gScope;
    }

    public IRModule buildIR() {
        topModule = new IRModule();
        cur.scope = gScope;
        cur.scope.DefMap.clear();
        progRoot.accept(this);
        return topModule;
    }

    @Override
    public void visit(ProgramNode it) {
        it.blocks.forEach(v -> v.accept(this));
    }

    @Override
    public void visit(ClassDeclStmtNode it) {

        // 1. build the class information
        var classTypeInfo = (ClassType) gScope.typeMap.get(it.classNameString);
        var structType = Transfer.structTypeTransfer(classTypeInfo);
        classTypeInfo.structType = structType;

        // 2. save the current status
        topModule.classList.add(structType);
        cur.scope = new Scope(cur.scope);
        cur.inWhichClass = classTypeInfo;

        // 3. visit the function declaration
        it.funcDeclList.forEach(fn -> fn.accept(this));

        cur.scope = cur.scope.parent;
        cur.inWhichClass = null;
    }

    @Override
    public void visit(VarDeclStmtNode it) {
        it.varList.forEach(vardecl -> visit(vardecl));
    }

    private void terminalCreator(IRFn fn) {
        fn.blockList.forEach(block -> {
            if (block.terminal == null) {
                if (fn.valueType instanceof IRVoidType)
                    block.terminal = RetInst.createVoidRetInst();
                else
                    throw new MyException("non-void function have a block without terminal");
            }
        });
    }

    private FuncInfo findFnInfo(String fnNameString) {
        FuncInfo ret = null;
        if (cur.inWhichClass != null)
            ret = cur.inWhichClass.funMap.get(fnNameString);
        if (ret == null)
            ret = gScope.funMap.get(fnNameString);
        if (ret == null)
            throw new MyException("I can't find the function in neither calssType nor global scope");
        return ret;
    }

    private void addParameter(TypeIdPair para) { // used in visit(FuncDeclrStmtNode it);
        // 1. put the ast var into function scope
        cur.scope.putDef(para);
        // 2. create the argument for IRFn
        var arg = new IRArgument(Transfer.typeTransfer(para.typeName));
        cur.fn.argList.add(arg);
        // 3. link the IRFn-arg to AST-para
        para.varValue = new AllocaInst(arg.valueType, cur.block);
        new StoreInst(arg, para.varValue, cur.block);
    }

    @Override
    public void visit(FuncDeclrStmtNode it) {

        // 1. build the function information
        var funcInfo = findFnInfo(it.funcNameString);
        IRFn nowFn = new IRFn(funcInfo, cur.inWhichClass);

        // 2. add it to topModule and set the current status
        topModule.globalFnList.add(nowFn);
        cur.fn = nowFn;
        cur.block = new IRBasicBlock();
        cur.fn.addBlock(cur.block);
        cur.scope = new Scope(cur.scope);

        // 3. add the parameter list for IRFn
        if (cur.inWhichClass != null) {
            // implicitly put this def into a function's parameter
            TypeIdPair thisArg = new TypeIdPair(new TypeName(cur.inWhichClass.typeNameString, 0, true), "this");
            addParameter(thisArg);
        }
        it.paraList.forEach(para -> addParameter(para));

        // 4. visit the function's body
        visit(it.body);

        // 5. create terminal instructions for every block
        terminalCreator(nowFn);

        // 6. reset the current status
        cur.inWhichClass = null;
        cur.fn = null;
        cur.block = null;
        cur.scope = cur.scope.parent;
    }

    @Override
    public void visit(CreatorExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(BinaryOpExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        binaryOperator opCode = switch (it.opcode) {
            case ADD -> binaryOperator.irADD;
            case SUB -> binaryOperator.irSUB;
            case BIT_AND, LOGIC_AND -> binaryOperator.irAND;
            case BIT_OR, LOGIC_OR -> binaryOperator.irOR;
            case DIV -> binaryOperator.irSDIV;
            case MOD -> binaryOperator.irSREM;
            case MUL -> binaryOperator.irMUL;
            case SHIFT_LEFT -> binaryOperator.irSHL;
            case SHIFT_RIGHT -> binaryOperator.irASHR;
            case BIT_XOR -> binaryOperator.irXOR;
            default -> throw new IllegalArgumentException("Wait !!! wo hai mei chuli hao bijiao fuhao");
        };
        var irvalue = new BinaryInst(opCode, it.lhs.irValue, it.rhs.irValue, cur.block);
        it.irValue = irvalue;
    }

    @Override
    public void visit(UnaryOpExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(AssignExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(SubscExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(FuncCallExprNode it) {
        ArrayList<IRBaseValue> argList = new ArrayList<>();
        it.argList.forEach(argExpr -> {
            argExpr.accept(this);
            argList.add(argExpr.irValue);
        });
        var calledFn = cur.scope.getFuncInfo(it.FuncNameString, null).fnValue;
        it.irValue = new CallInst(calledFn, argList, cur.block);
    }

    @Override
    public void visit(SelfConstructNode it) {
        cur.scope = new Scope(cur.scope);
        // TODO Auto-generated method stub
        cur.scope = cur.scope.parent;
    }

    @Override
    public void visit(ExprStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(IfStmtNode it) {
        cur.scope = new Scope(cur.scope);
        // TODO Auto-generated method stub
        cur.scope = cur.scope.parent;

    }

    @Override
    public void visit(WhileStmtNode it) {
        cur.scope = new Scope(cur.scope);
        // TODO Auto-generated method stub
        cur.scope = cur.scope.parent;
    }

    @Override
    public void visit(ForStmtNode it) {
        cur.scope = new Scope(cur.scope);
        // TODO Auto-generated method stub
        cur.scope = cur.scope.parent;

    }

    @Override
    public void visit(ReturnStmtNode it) {
        if (it.expr == null)// return void
            cur.block.terminal = RetInst.createVoidRetInst();
        else {
            it.expr.accept(this);
            cur.block.terminal = new RetInst(it.expr.irValue);
        }
    }

    @Override
    public void visit(ContinueStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(BreakStmtNode it) {
        // TODO Auto-generated method stub

    }

    private BaseType getWhoseMember(ExprNode it) {
        var typeNameL = it.typeName;
        var uniType = gScope.getType(typeNameL.typeNameString, typeNameL.pos);
        BaseType ctype = null;
        if (typeNameL.dimen == 0)
            ctype = uniType;
        else
            ctype = new ArrayType(gScope, uniType, typeNameL.dimen, it.pos);
        return ctype;
    }

    @Override
    public void visit(MemberExprNode it) {
        // TODO Auto-generated method stub

        // 1. get the base pointer
        it.expr.accept(this);

        // 2. set the isMember status and get the index of a member
        cur.whoseMember = getWhoseMember(it.expr);

        // 3. get the id value or fn value
        if (it.idExpr != null) {
            it.idExpr.accept(this);
            var getInstType = new IRPtType(Transfer.typeTransfer(it.typeName), 1);
            var gep = new GEPInst(it.expr.irValue, getInstType, cur.block, ((IntConst) it.idExpr.irValue));
            it.irValue = new LoadInst(gep, cur.block);
        } else if (it.funcCall != null) {
            // TODO
        }

        cur.whoseMember = null;
    }

    @Override
    public void visit(LambdaExprNode it) {
    }

    @Override
    public void visit(ThisExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(LiteralExprNode it) {
        it.irValue = switch (it.lit) {
            case INT -> new IntConst(Integer.parseInt(it.litString), 32);
            // TODO Auto-generated method stub
            case STRING -> {
                var constStr = Transfer.constStrTranfer(it.litString, topModule.constStrList.size());
                topModule.constStrList.add(constStr);
                yield new GEPInst(constStr, IRPtType.getCharRefType(), cur.block, new IntConst(0, 64),
                        new IntConst(0, 64));
            }
            case NULL -> new NullConst();
            case TRUE -> new IntConst(1, 8);
            case FALSE -> new IntConst(0, 8);
        };
    }

    @Override
    public void visit(SuiteStmtNode it) {
        cur.scope = new Scope(cur.scope);
        it.StmtList.forEach(stmtNode -> stmtNode.accept(this));
        cur.scope = cur.scope.parent;
    }

    @Override
    public void visit(EmptyStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(IdentiExprNode it) {
        // TODO Auto-generated method stub
        if (cur.whoseMember == null) {
            var varDef = cur.scope.getDef(it.idString, null);
            it.irValue = new LoadInst(varDef.varValue, cur.block);
        } else {
            var varDef = ((ClassType) cur.whoseMember).varMap.get(it.idString);
            it.irValue = varDef.varValue;
        }
    }

    @Override
    public void visit(SingleVarDeclStmtNode it) {
        // TODO Auto-generated method FUCK

        if (cur.fn == null) {// global
            GlobalVariable gVar = new GlobalVariable(it.decl.Id, it.decl.typeName);
            if (it.expr != null) { // has initial value
                if (it.expr instanceof LiteralExprNode exprNode && exprNode.lit == literalType.INT) {
                    // int type(i32) can be handled specially
                    it.expr.accept(this);
                    gVar.isInit = true;
                    gVar.initValue = it.expr.irValue;
                } else {
                    var initFnNameString = "__mx_global_var_init." + gVar.constName;
                    gVar.initFn = IRFn.getInitFn(initFnNameString);
                    var block = IRBasicBlock.getVarInitBB();
                    gVar.initFn.addBlock(block);
                    topModule.varInitFnList.add(gVar.initFn);
                    cur.fn = gVar.initFn;
                    cur.block = block;

                    gVar.isInit = true;
                    gVar.initValue = gVar.derefType.defaultValue();
                    it.expr.accept(this);
                    new StoreInst(it.expr.irValue, gVar, cur.block);

                    cur.fn = null;
                    cur.block = null;
                }
            }
            topModule.globalVarList.add(gVar);
            it.irValue = it.decl.varValue = gVar;
        } else {
            // TODO
            it.irValue = it.decl.varValue = new AllocaInst(Transfer.typeTransfer(it.decl.typeName), cur.block);
            if (it.expr != null) {
                it.expr.accept(this);
                new StoreInst(it.expr.irValue, it.irValue, cur.block);
            }
        }

        cur.scope.putDef(it.decl);
    }
}
