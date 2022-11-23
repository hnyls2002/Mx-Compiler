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
import IR.IRType.IRIntType;
import IR.IRType.IRStructType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.Inst.RetInst;
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
        progRoot.accept(this);
        return topModule;
    }

    @Override
    public void visit(ProgramNode it) {
        it.blocks.forEach(v -> v.accept(this));
    }

    @Override
    public void visit(ClassDeclStmtNode it) {

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

    }

    @Override
    public void visit(VarDeclStmtNode it) {
        it.varList.forEach(vardecl -> visit(vardecl));
    }

    private IRFnType getFnType(FuncInfo funcInfo) {
        var ret = new IRFnType(funcInfo.funcName);
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
        cur.fn.AddBlock(cur.block);

        visit(it.body);

        terminalCreator(nowFn);

        cur.fn = null;
    }

    @Override
    public void visit(CreatorExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(BinaryOpExprNode it) {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ExprStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(IfStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(WhileStmtNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ForStmtNode it) {
        // TODO Auto-generated method stub

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
            case INT -> new IntConst(Integer.parseInt(it.litString), new IRIntType(32));
            // TODO Auto-generated method stub
            case STRING -> {
                var constStr = Transfer.constStrTranfer(it.litString, topModule.constStrList.size());
                topModule.constStrList.add(constStr);
                yield constStr;
            }
            case NULL -> new NullConst();
            case TRUE -> new IntConst(1, new IRIntType(8));
            case FALSE -> new IntConst(0, new IRIntType(8));
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

    }

    @Override
    public void visit(SingleVarDeclStmtNode it) {
        // TODO Auto-generated method FUCK

        if (cur.fn == null) {// global
            GlobalVariable gVar = new GlobalVariable(it.decl.Id, it.decl.typeName);
            if (it.expr != null) { // has initial value
                it.expr.accept(this);
                gVar.isInit = true;
                gVar.initData = it.expr.irValue;
            }
            topModule.globalVarList.add(gVar);
        } else {
            // TODO
        }
    }
}
