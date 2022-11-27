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
import IR.IRModule;
import IR.IRType.IRFnType;
import IR.IRType.IRIntType;
import IR.IRType.IRPtType;
import IR.IRType.IRType;
import IR.IRType.IRVoidType;
import IR.IRValue.IRParameter;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.GlobalVariable;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.Inst.AllocaInst;
import IR.IRValue.IRUser.Inst.BinaryInst;
import IR.IRValue.IRUser.Inst.BrInst;
import IR.IRValue.IRUser.Inst.CallInst;
import IR.IRValue.IRUser.Inst.CastInst;
import IR.IRValue.IRUser.Inst.GEPInst;
import IR.IRValue.IRUser.Inst.IcmpInst;
import IR.IRValue.IRUser.Inst.LoadInst;
import IR.IRValue.IRUser.Inst.RetInst;
import IR.IRValue.IRUser.Inst.StoreInst;
import IR.IRValue.IRUser.Inst.BinaryInst.binaryOperator;
import IR.IRValue.IRUser.Inst.CastInst.castType;
import IR.IRValue.IRUser.Inst.IcmpInst.icmpOperator;
import IR.Util.Transfer;

public class IRBuilder implements ASTVisitor {

    public IRCurrent cur = new IRCurrent();

    public IRModule topModule;
    public ASTNode progRoot;
    public GlobalScope gScope;

    public IRBuilder(ASTNode progRoot, GlobalScope gScope) {
        this.progRoot = progRoot;
        this.gScope = gScope;
    }

    public IRModule buildIR() {
        topModule = new IRModule();
        IRPreload();
        cur.scope = gScope;
        cur.scope.DefMap.clear();
        progRoot.accept(this);
        return topModule;
    }

    private void IRPreload() {
        gScope.typeMap.forEach((typeNameString, astType) -> {
            if (astType instanceof ClassType classTypeInfo) {
                var structType = Transfer.structTypeTransfer(classTypeInfo);
                classTypeInfo.structType = structType;
                topModule.classList.add(structType);
                classTypeInfo.funMap.forEach((fuNameString, fnInfo) -> {
                    fnInfo.inWhichClass = classTypeInfo;
                    fnInfo.fnType = Transfer.fnTypeTransfer(fnInfo);
                });
            } else {
                // TODO other types
            }
        });
        gScope.funMap.forEach((funNameString, funcInfo) -> {
            funcInfo.fnType = Transfer.fnTypeTransfer(funcInfo);
        });
    }

    @Override
    public void visit(ProgramNode it) {
        it.blocks.forEach(v -> v.accept(this));
    }

    @Override
    public void visit(ClassDeclStmtNode it) {
        // since we have got the structType in the preload
        cur.scope = new Scope(cur.scope);

        // 1. add the vars and functions into current scope
        var classTypeInfo = (ClassType) gScope.typeMap.get(it.classNameString);
        classTypeInfo.varMap.forEach((varNameString, memVar) -> cur.scope.DefMap.put(varNameString, memVar));
        classTypeInfo.funMap.forEach((fuNameString, fnInfo) -> cur.scope.funMap.put(fuNameString, fnInfo));

        // 2. visit the function declaration
        it.funcDeclList.forEach(fn -> fn.accept(this));

        cur.scope = cur.scope.parent;
    }

    @Override
    public void visit(VarDeclStmtNode it) {
        it.varList.forEach(vardecl -> visit(vardecl));
    }

    private void terminalToRet(IRFn fn) {
        fn.blockList.forEach(block -> {
            if (block.getTerminal() == null)
                new BrInst(fn.retBlock, block);
        });
    }

    private void addParameter(TypeIdPair para) { // used in visit(FuncDeclrStmtNode it);
        // 1. put the ast var into function scope
        cur.scope.putDef(para);
        // 2. create the parameter for IRFn
        var irPara = new IRParameter(Transfer.typeTransfer(para.typeName));
        cur.fn.paraList.add(irPara);
        // 3. store the format-argument into some space
        para.varValue = new AllocaInst(irPara.valueType, cur.block);
        irPara.storedAddr = para.varValue;
        new StoreInst(irPara, para.varValue, cur.block);
    }

    private void terminateRetBlock(IRFn fn) {
        if (fn.retValueAddr == null)
            RetInst.createVoidRetInst(fn.retBlock);
        else
            new RetInst(new LoadInst(fn.retValueAddr, fn.retBlock), fn.retBlock);
    }

    @Override
    public void visit(FuncDeclrStmtNode it) {
        // since we have got the fnType information

        // 1. build the function information
        var funcInfo = cur.scope.getFuncInfo(it.funcNameString, null);
        IRFn nowFn = new IRFn(funcInfo);

        // 2. add it to topModule and set the current status
        topModule.globalFnList.add(nowFn);
        cur.fn = nowFn;
        cur.block = new IRBasicBlock(cur.fn);
        cur.scope = new Scope(cur.scope);

        // 2.1 add retBlock and retAddr
        IRBasicBlock.addRetBlock(nowFn);
        IRType retType = ((IRFnType) nowFn.valueType).retType;
        if (!(retType instanceof IRVoidType))
            nowFn.retValueAddr = new AllocaInst(retType, cur.block);

        // 3. add the parameter list for IRFn
        if (funcInfo.inWhichClass != null) {
            // implicitly put this def into a function's parameter
            TypeIdPair thisPara = new TypeIdPair(new TypeName(funcInfo.inWhichClass.typeNameString, 0, true), "this");
            addParameter(thisPara);
        }
        it.paraList.forEach(para -> addParameter(para));

        // 4. visit the function's body
        visit(it.body);

        // 5. create terminal instructions for every block
        terminalToRet(nowFn);

        // 5.1 terminate the retBlock
        terminateRetBlock(nowFn);

        // 6. reset the current status
        cur.fn = null;
        cur.block = null;
        cur.scope = cur.scope.parent;
    }

    @Override
    public void visit(CreatorExprNode it) {
        // TODO Auto-generated method stub

    }

    private void intBinary(BinaryOpExprNode it) {

        binaryOperator binaryOpCode = switch (it.opcode) {
            case ADD -> binaryOperator.irADD;
            case SUB -> binaryOperator.irSUB;
            case BIT_AND -> binaryOperator.irAND;
            case BIT_OR -> binaryOperator.irOR;
            case DIV -> binaryOperator.irSDIV;
            case MOD -> binaryOperator.irSREM;
            case MUL -> binaryOperator.irMUL;
            case SHIFT_LEFT -> binaryOperator.irSHL;
            case SHIFT_RIGHT -> binaryOperator.irASHR;
            case BIT_XOR -> binaryOperator.irXOR;
            default -> null;
        };
        icmpOperator icmpOpCode = switch (it.opcode) {
            case EQUAL -> icmpOperator.irEQ;
            case NOT_EQUAL -> icmpOperator.irNE;
            case GREATER -> icmpOperator.irSGT;
            case GREATER_EQUAL -> icmpOperator.irSGE;
            case LESS -> icmpOperator.irSLT;
            case LESS_EQUAL -> icmpOperator.irSLE;
            default -> null;
        };

        IRBaseValue irValue = null;
        if (binaryOpCode != null)
            irValue = new BinaryInst(binaryOpCode, it.lhs.irValue, it.rhs.irValue, cur.block);
        else if (icmpOpCode != null)
            irValue = new IcmpInst(icmpOpCode, it.lhs.irValue, it.rhs.irValue, cur.block);
        else
            throw new MyException("Binary i32 opCode could not find");

        it.irValue = irValue;

    }

    private void stringBinary(BinaryOpExprNode it) {
        // TODO
    }

    private void shortCircuit() {
        // TODO
    }

    @Override
    public void visit(BinaryOpExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.irValue.valueType instanceof IRPtType ptr) {
            if (ptr.atomicType instanceof IRIntType i8 && i8.intLen == 8)
                stringBinary(it);
            else {
                var irOpCode = switch (it.opcode) { // array ptr or class ptr
                    case EQUAL -> icmpOperator.irEQ;
                    case NOT_EQUAL -> icmpOperator.irNE;
                    default -> throw new MyException("ptr type can only '==' '!=' ");
                };
                it.irValue = new IcmpInst(irOpCode, it.lhs.irValue, it.rhs.irValue, cur.block);
            }
        } else {
            var len1 = ((IRIntType) it.lhs.irValue.valueType).intLen;
            var len2 = ((IRIntType) it.rhs.irValue.valueType).intLen;
            if (len1 == 32 && len2 == 32) // int
                intBinary(it);
            else if (len1 == 8 || len1 == 1 || len2 == 8 || len2 == 1) { // bool
                IRBaseValue lhs = it.lhs.irValue, rhs = it.rhs.irValue;
                if (len1 == 8)
                    lhs = new CastInst(lhs, new IRIntType(1), castType.TRUNC, cur.block);
                if (len2 == 8)
                    rhs = new CastInst(rhs, new IRIntType(1), castType.TRUNC, cur.block);
                var irOpCode = switch (it.opcode) {
                    case EQUAL -> icmpOperator.irEQ;
                    case NOT_EQUAL -> icmpOperator.irNE;
                    default -> null;
                };
                if (irOpCode != null)
                    it.irValue = new IcmpInst(irOpCode, lhs, rhs, cur.block);
                else {
                    // TODO
                    shortCircuit();
                }
            } else
                throw new MyException("int WHAT type ? in binary operator");
        }
    }

    @Override
    public void visit(UnaryOpExprNode it) {
        it.expr.accept(this);
        switch (it.opCode) {
            case BIT_NOT -> {
                it.irValue = new BinaryInst(binaryOperator.irXOR, it.expr.irValue, new IntConst(-1, 32), cur.block);
            }
            case LOGIC_NOT -> { // bool type : transfer to i1 first
                IRBaseValue i1Value = it.expr.irValue;
                if (((IRIntType) i1Value.valueType).intLen != 1)
                    i1Value = new CastInst(i1Value, new IRIntType(1), castType.TRUNC, cur.block);
                it.irValue = new BinaryInst(binaryOperator.irXOR, i1Value, new IntConst(1, 1), cur.block);
            }
            case PRE_ADD -> {
                it.irValue = it.expr.irValue;
            }
            case PRE_SUB -> {
                it.irValue = new BinaryInst(binaryOperator.irSUB, new IntConst(0, 32), it.expr.irValue, cur.block);
            }
            case PRE_INC -> {
                it.irValue = new BinaryInst(binaryOperator.irADD, it.expr.irValue, new IntConst(1, 32), cur.block);
                new StoreInst(it.irValue, it.expr.irAddr, cur.block);
                it.irAddr = it.expr.irAddr;
            }
            case PRE_DEC -> {
                it.irValue = new BinaryInst(binaryOperator.irSUB, it.expr.irValue, new IntConst(1, 32), cur.block);
                new StoreInst(it.irValue, it.expr.irAddr, cur.block);
                it.irAddr = it.expr.irAddr;
            }
            case SUF_INC -> {
                it.irValue = it.expr.irValue;
                var updatedValue = new BinaryInst(binaryOperator.irADD, it.expr.irValue, new IntConst(1, 32),
                        cur.block);
                new StoreInst(updatedValue, it.expr.irAddr, cur.block);
            }
            case SUF_DEC -> {
                it.irValue = it.expr.irValue;
                var updatedValue = new BinaryInst(binaryOperator.irSUB, it.expr.irValue, new IntConst(1, 32),
                        cur.block);
                new StoreInst(updatedValue, it.expr.irAddr, cur.block);
            }
        }
    }

    @Override
    public void visit(AssignExprNode it) {
        cur.justNeedAddr = true;
        it.lvalue.accept(this);
        cur.justNeedAddr = false;
        it.rvalue.accept(this);
        it.irValue = new StoreInst(it.rvalue.irValue, it.lvalue.irAddr, cur.block);
    }

    @Override
    public void visit(SubscExprNode it) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(FuncCallExprNode it) {
        IRFnType calledFnType = null;
        ArrayList<IRBaseValue> argList = new ArrayList<>();

        calledFnType = cur.whoseMember == null ? cur.scope.getFuncInfo(it.FuncNameString, null).fnType
                : cur.whoseMember.getFunc(it.FuncNameString, null).fnType;

        // you can still get the member-function when using cur.scope
        // as innner function call each other
        // so still need add this argument when call

        // if calledFn is a member function
        // && you don't get it from a whoseMember scope(no using "this")
        if (calledFnType.methodFrom != null && cur.whoseMember == null) {
            var thisPara = cur.fn.paraList.get(0);
            argList.add(new LoadInst(thisPara.storedAddr, cur.block));
        }

        cur.whoseMember = null;

        it.argList.forEach(argExpr -> {
            argExpr.accept(this);
            argList.add(argExpr.irValue);
        });

        it.irValue = new CallInst(calledFnType, argList, cur.block);
    }

    @Override
    public void visit(SelfConstructNode it) {
        cur.scope = new Scope(cur.scope);
        // TODO Auto-generated method stub
        cur.scope = cur.scope.parent;
    }

    @Override
    public void visit(ExprStmtNode it) {
        it.expr.accept(this);
    }

    @Override
    public void visit(IfStmtNode it) {
        var beforeIfBlock = cur.block;
        it.expr.accept(this);

        IRBasicBlock thenBlock = new IRBasicBlock(cur.fn);
        cur.scope = new Scope(cur.scope);
        cur.block = thenBlock;
        it.thenStmt.accept(this);
        cur.scope = cur.scope.parent;

        IRBasicBlock elseBlock = null;
        if (it.elseStmt != null) {
            elseBlock = new IRBasicBlock(cur.fn);
            cur.scope = new Scope(cur.scope);
            cur.block = elseBlock;
            it.elseStmt.accept(this);
            cur.scope = cur.scope.parent;
        }

        IRBasicBlock afterIfBlock = new IRBasicBlock(cur.fn);
        cur.block = afterIfBlock;

        beforeIfBlock.tailBlock = cur.block;

        if (elseBlock != null) {
            new BrInst(it.expr.irValue, thenBlock, elseBlock, beforeIfBlock);
            new BrInst(afterIfBlock, thenBlock.tailBlock);
            new BrInst(afterIfBlock, elseBlock.tailBlock);
        } else {
            new BrInst(it.expr.irValue, thenBlock, afterIfBlock, beforeIfBlock);
            new BrInst(afterIfBlock, thenBlock.tailBlock);
        }
    }

    @Override
    public void visit(WhileStmtNode it) {
        IRBasicBlock beforeBlock = cur.block;
        cur.scope = new Scope(cur.scope);

        IRBasicBlock conditionBlock = new IRBasicBlock(cur.fn);
        cur.block = conditionBlock;
        it.expr.accept(this);

        IRBasicBlock whileBodyBlock = new IRBasicBlock(cur.fn);
        IRBasicBlock afterWhileBlock = new IRBasicBlock();
        cur.block = whileBodyBlock;
        cur.pushInfo(afterWhileBlock, conditionBlock);
        it.body.accept(this);
        cur.popInfo();

        cur.fn.addBlock(afterWhileBlock);
        cur.block = afterWhileBlock;
        beforeBlock.tailBlock = afterWhileBlock;

        new BrInst(conditionBlock, beforeBlock);
        new BrInst(it.expr.irValue, whileBodyBlock, afterWhileBlock, conditionBlock.tailBlock);
        new BrInst(conditionBlock, whileBodyBlock.tailBlock);

        cur.scope = cur.scope.parent;
    }

    @Override
    public void visit(ForStmtNode it) {
        IRBasicBlock beforeBlock = cur.block;
        cur.scope = new Scope(cur.scope);

        if (it.varDecl != null)
            it.varDecl.accept(this);
        else if (it.incExpr != null)
            it.initExpr.accept(this);

        IRBasicBlock conditionBlock = new IRBasicBlock(cur.fn);
        cur.block = conditionBlock;
        if (it.condExpr != null)
            it.condExpr.accept(this);

        IRBasicBlock forBodyBlock = new IRBasicBlock(cur.fn);
        IRBasicBlock stepBlock = new IRBasicBlock(); // continue
        IRBasicBlock afterForBlock = new IRBasicBlock(); // break
        cur.block = forBodyBlock;
        cur.pushInfo(afterForBlock, stepBlock);
        it.body.accept(this);
        cur.popInfo();

        cur.fn.addBlock(stepBlock);
        cur.block = stepBlock;
        if (it.incExpr != null)
            it.incExpr.accept(this);

        cur.fn.addBlock(afterForBlock);
        cur.block = afterForBlock;
        beforeBlock.tailBlock = afterForBlock;

        // before -> condition
        new BrInst(conditionBlock, beforeBlock);
        // condition -> forbody or afeterFor
        if (it.condExpr != null)
            new BrInst(it.condExpr.irValue, forBodyBlock, afterForBlock, conditionBlock.tailBlock);
        else // condition -> forbody
            new BrInst(forBodyBlock, conditionBlock.tailBlock);
        // forbody -> step
        new BrInst(stepBlock, forBodyBlock.tailBlock);
        // step -> condition
        new BrInst(conditionBlock, stepBlock.tailBlock);

        cur.scope = cur.scope.parent;
    }

    @Override
    public void visit(ReturnStmtNode it) {
        if (it.expr == null)// return void
            new BrInst(cur.fn.retBlock, cur.block);
        else {
            it.expr.accept(this);
            if (it.expr.irValue.valueType instanceof IRIntType b && b.intLen == 1)
                it.expr.irValue = new CastInst(it.expr.irValue, new IRIntType(8), castType.ZEXT, cur.block);
            new StoreInst(it.expr.irValue, cur.fn.retValueAddr, cur.block);
            new BrInst(cur.fn.retBlock, cur.block);
        }
    }

    @Override
    public void visit(ContinueStmtNode it) {
        new BrInst(cur.getCurContinue(), cur.block);
    }

    @Override
    public void visit(BreakStmtNode it) {
        new BrInst(cur.getCurBreak(), cur.block);
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
        // 1. get the base pointer

        // handld the leftValue address : a.b.c.d.e , you need e's address
        // member's address handled in member node
        var onlyAddr = cur.justNeedAddr;
        cur.justNeedAddr = false;
        it.expr.accept(this);

        // 2. set the isMember status and get the index of a member
        cur.whoseMember = getWhoseMember(it.expr);

        // 3. get the id value or fn value
        if (it.idExpr != null) {
            it.idExpr.accept(this);
            var gepInstType = new IRPtType(Transfer.typeTransfer(it.typeName), 1);
            var gep = new GEPInst(it.expr.irValue, gepInstType, cur.block, ((IntConst) it.idExpr.irValue));
            it.irAddr = gep;
            if (!onlyAddr)
                it.irValue = new LoadInst(gep, cur.block);
        } else if (it.funcCall != null) {
            Boolean isMemberFunction = cur.whoseMember != null;
            it.funcCall.accept(this);
            if (isMemberFunction)
                ((CallInst) it.funcCall.irValue).argList.add(0, it.expr.irValue);
            it.irValue = it.funcCall.irValue;
        }

        cur.whoseMember = null;
        // 1. enter the id, then null
        // 2. enter the call, then null
    }

    @Override
    public void visit(LambdaExprNode it) {
    }

    @Override
    public void visit(ThisExprNode it) {
        var thisPara = cur.fn.paraList.get(0);
        // a single this can't be leftValue, but this.a can be
        it.irValue = new LoadInst(thisPara.storedAddr, cur.block);
    }

    @Override
    public void visit(LiteralExprNode it) {
        it.irValue = switch (it.lit) {
            case INT -> new IntConst(Integer.parseInt(it.litString), 32);
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
    }

    @Override
    public void visit(IdentiExprNode it) {

        // 1. member access is prior
        if (cur.whoseMember != null) {
            var varDef = ((ClassType) cur.whoseMember).varMap.get(it.idString);
            it.irValue = varDef.varValue;
            cur.whoseMember = null;
            return;
        }

        // 2. try to get it in the current scope, whether in class or in global
        // Q : how to find a varDef is a member ?
        // A : varDef.varValue instanceof IntConst

        var varDef = cur.scope.getDef(it.idString, null);
        if (varDef.varValue instanceof IntConst idx) {
            var thisPara = cur.fn.paraList.get(0);
            var gepInstType = new IRPtType(Transfer.typeTransfer(varDef.typeName), 1);
            var startPtr = new LoadInst(thisPara.storedAddr, cur.block);
            var gep = new GEPInst(startPtr, gepInstType, cur.block, new IntConst(0, 64), idx);

            it.irAddr = gep;
            if (!cur.justNeedAddr)
                it.irValue = new LoadInst(gep, cur.block);
            cur.justNeedAddr = false;
        } else {
            it.irAddr = varDef.varValue;
            if (!cur.justNeedAddr)
                it.irValue = new LoadInst(varDef.varValue, cur.block);
            cur.justNeedAddr = false;
        }
    }

    private void varInit(GlobalVariable gVar, SingleVarDeclStmtNode it) {
        if (it.expr instanceof LiteralExprNode exprNode && exprNode.lit == literalType.INT) {
            // int type(i32) can be handled specially
            it.expr.accept(this);
            gVar.isInit = true;
            gVar.initValue = it.expr.irValue;
        } else {
            var initFnNameString = "__mx_global_var_init." + gVar.constName;
            gVar.initFn = IRFn.getInitFn(initFnNameString);
            IRBasicBlock.addRetBlock(gVar.initFn);
            var block = gVar.initFn.retBlock;
            topModule.varInitFnList.add(gVar.initFn);
            cur.fn = gVar.initFn;
            cur.block = block;

            gVar.isInit = true;
            gVar.initValue = gVar.derefType.defaultValue();
            it.expr.accept(this);
            new StoreInst(it.expr.irValue, gVar, cur.block);

            RetInst.createVoidRetInst(cur.block);

            cur.fn = null;
            cur.block = null;
        }
    }

    @Override
    public void visit(SingleVarDeclStmtNode it) {

        if (cur.fn == null) {// global
            GlobalVariable gVar = new GlobalVariable(it.decl.Id, it.decl.typeName);
            gVar.initValue = gVar.derefType.defaultValue();
            if (it.expr != null) // has initial value
                varInit(gVar, it);

            topModule.globalVarList.add(gVar);
            it.irValue = it.decl.varValue = gVar;
        } else {// local
            it.irValue = it.decl.varValue = new AllocaInst(Transfer.typeTransfer(it.decl.typeName), cur.block);
            if (it.expr != null) {
                it.expr.accept(this);
                new StoreInst(it.expr.irValue, it.irValue, cur.block);
            }
        }

        cur.scope.putDef(it.decl);
    }
}
