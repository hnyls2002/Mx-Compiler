package MiddleEnd;

import java.util.ArrayList;

import AST.ASTNode;
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
import Frontend.Util.Scopes.GlobalScope;
import Frontend.Util.Scopes.Scope;
import Frontend.Util.Types.FuncInfo;
import IR.IRModule;
import IR.IRType.IRFnType;
import IR.IRType.IRPtType;
import IR.IRType.IRStructType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.Inst.BinaryInst;
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

        cur.scope = new Scope(cur.scope);

        var st = new IRStructType(it.classNameString);
        st.isSolid = true;
        st.fieldList = new ArrayList<>();
        for (var varDecl : it.varDeclList) {
            int cnt = 0;
            for (var varSingle : varDecl.varList) {
                st.fieldList.add(Transfer.typeTransfer(varSingle.decl.typeName));
                st.fieldIdxMap.put(varSingle.decl.Id, cnt++);
            }
        }

        cur.scope = cur.scope.parent;

    }

    @Override
    public void visit(VarDeclStmtNode it) {
        it.varList.forEach(vardecl -> visit(vardecl));
    }

    private IRFnType getFnType(FuncInfo funcInfo) {
        var ret = new IRFnType();
        ret.retType = Transfer.typeTransfer(funcInfo.retType);
        for (var arg : funcInfo.paraList)
            ret.argumentList.add(Transfer.typeTransfer(arg.typeName));
        return ret;
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

    @Override
    public void visit(FuncDeclrStmtNode it) {
        IRFn nowFn = new IRFn(it.funcNameString, getFnType(gScope.getFuncInfo(it.funcNameString, null)));
        topModule.globalFnList.add(nowFn);

        cur.fn = nowFn;
        cur.block = new IRBasicBlock();
        cur.fn.addBlock(cur.block);

        cur.scope = new Scope(cur.scope);

        visit(it.body);

        terminalCreator(nowFn);

        cur.fn = null;

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
        // TODO Auto-generated method stub

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

    @Override
    public void visit(MemberExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(LambdaExprNode it) {
        // TODO Auto-generated method stub

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
                yield new GEPInst(constStr, IRPtType.getCharRefType(), 0, 0, cur.block);
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
        var varDef = cur.scope.getDef(it.idString, null);
        it.irValue = new LoadInst(varDef.varValue, cur.block);
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
                    gVar.initFn = new IRFn(initFnNameString, IRFnType.getVarInitFnType());
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

                    System.err.println(gVar.constName);
                    System.err.println(it.expr.irValue.valueType);
                }
            }
            topModule.globalVarList.add(gVar);
            it.irValue = gVar;
            it.decl.varValue = gVar;
        } else {
            // TODO
        }

        cur.scope.putDef(it.decl);
    }
}
