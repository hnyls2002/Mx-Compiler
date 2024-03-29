package Middleend;

import java.util.ArrayList;

import AST.ASTNode;
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
import AST.Expr.BinaryOpExprNode.binaryOp;
import AST.Expr.LiteralExprNode.literalType;
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
import AST.Util.TypeName;
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
import IR.IRValue.IRUser.IRInst.AllocaInst;
import IR.IRValue.IRUser.IRInst.BinaryInst;
import IR.IRValue.IRUser.IRInst.BrInst;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.CastInst;
import IR.IRValue.IRUser.IRInst.GEPInst;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import IR.IRValue.IRUser.IRInst.IcmpInst;
import IR.IRValue.IRUser.IRInst.JumpInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.PhiInst;
import IR.IRValue.IRUser.IRInst.RetInst;
import IR.IRValue.IRUser.IRInst.StoreInst;
import IR.Util.Transfer;
import Share.MyException;
import Share.Lang.LLVMIR.BOP;
import Share.Lang.LLVMIR.CastType;
import Share.Lang.LLVMIR.ICMPOP;
import Share.Visitors.ASTVisitor;

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
        BuiltinPreload.preload(topModule, gScope);
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
                    fnInfo.irFn = new IRFn(fnInfo);
                });
                if (classTypeInfo.haveConst) {
                    var constructType = Transfer.constructorTransfer(structType);
                    structType.constructFn = new IRFn(constructType, false);
                }
            }
        });
        gScope.funMap.forEach((funNameString, funcInfo) -> {
            for (var builtInFn : topModule.builtinFnList)
                if (builtInFn.nameString.equals(funNameString))
                    funcInfo.irFn = builtInFn;
            if (funcInfo.irFn == null)
                funcInfo.irFn = new IRFn(funcInfo);
        });
    }

    @Override
    public void visit(ProgramNode it) {
        it.blocks.forEach(v -> {
            if (v instanceof VarDeclStmtNode)
                v.accept(this);
        });
        it.blocks.forEach(v -> {
            if (!(v instanceof VarDeclStmtNode))
                v.accept(this);
        });
    }

    @Override
    public void visit(ClassDeclStmtNode it) {
        // since we have got the structType in the preload
        cur.scope = new Scope(cur.scope);

        // 1. add the vars and functions into current scope
        var classTypeInfo = (ClassType) gScope.typeMap.get(it.classNameString);
        classTypeInfo.varMap.forEach((varNameString, memVar) -> cur.scope.DefMap.put(varNameString, memVar));
        classTypeInfo.funMap.forEach((fuNameString, fnInfo) -> cur.scope.funMap.put(fuNameString, fnInfo));

        // 2.0 build the constructor
        if (it.constructor != null)
            it.constructor.accept(this);

        // 2. visit the function declaration
        it.funcDeclList.forEach(fn -> fn.accept(this));

        cur.scope = cur.scope.parent;
    }

    @Override
    public void visit(VarDeclStmtNode it) {
        it.varList.forEach(this::visit);
    }

    private void terminalToRet(IRFn fn) {
        fn.blockList.forEach(block -> {
            if (block.terminal == null)
                new JumpInst(fn.retBlock, block);
        });
    }

    private void addParameter(TypeIdPair para) { // used in visit(FuncDeclrStmtNode it);
        // 1. put the ast var into function scope
        cur.scope.putDef(para);
        // 2. create the parameter for IRFn
        var irPara = new IRParameter(Transfer.typeTransfer(para.typeName));
        cur.fn.paraList.add(irPara);
        // 3. store the format-argument into some space
        para.varValue = new AllocaInst(irPara.valueType, cur.fn);
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
        IRFn nowFn = funcInfo.irFn;

        // 2. add it to topModule and set the current status
        topModule.globalFnList.add(nowFn);
        cur.fn = nowFn;
        cur.block = new IRBasicBlock(cur.fn, 0);
        cur.scope = new Scope(cur.scope);

        // *** call the builtin function
        if (it.funcNameString.equals("main"))
            topModule.varInitFnList.forEach(initFn -> new CallInst(initFn, cur.block));

        // 2.1 add retBlock and retAddr
        nowFn.retBlock = new IRBasicBlock(0);
        IRType retType = ((IRFnType) nowFn.valueType).retType;
        if (!(retType instanceof IRVoidType))
            nowFn.retValueAddr = new AllocaInst(retType, cur.fn);

        // 3. add the parameter list for IRFn
        if (funcInfo.inWhichClass != null) {
            // implicitly put this def into a function's parameter
            TypeIdPair thisPara = new TypeIdPair(new TypeName(funcInfo.inWhichClass.typeNameString, 0, true), "this");
            addParameter(thisPara);
        }
        it.paraList.forEach(this::addParameter);

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

    private IRBaseValue arrMalloc(IRPtType addrType, ArrayList<ExprNode> dimList, int k) {
        IRFn fn = getBuiltInFn("__malloc");

        dimList.get(k).accept(this);
        IRBaseValue arrLength = new BinaryInst(BOP.add, dimList.get(k).irValue, new IntConst(1, 32),
                cur.block);
        IRBaseValue arrSize = new BinaryInst(BOP.mul, arrLength,
                new IntConst(addrType.derefType().getSize(topModule), 32), cur.block);

        var i8Ptr = new CallInst(fn, cur.block, arrSize);

        var i32Ptr = new CastInst(i8Ptr, new IRPtType(new IRIntType(32), 1), CastType.bitcast, cur.block);
        new StoreInst(dimList.get(k).irValue, i32Ptr, cur.block);

        var elePtr = new CastInst(i8Ptr, addrType, CastType.bitcast, cur.block);

        if (k < dimList.size() - 1) {
            // curPtr = [startPtr, nexPtr];
            // while(iPtr != endPtr){malloc(); nexPtr = curPtr + 1;}
            IRBasicBlock beforeBlock = cur.block;
            IRBasicBlock conditionBlock = new IRBasicBlock(beforeBlock.loopDepth);
            IRBasicBlock bodyBlock = new IRBasicBlock(beforeBlock.loopDepth + 1);
            IRBasicBlock afterBlock = new IRBasicBlock(beforeBlock.loopDepth);

            var startPtr = new GEPInst(elePtr, elePtr.valueType, cur.block, new IntConst(1, 32));
            IRBaseValue endPtr = new GEPInst(startPtr, startPtr.valueType, cur.block, dimList.get(k).irValue);

            // IRBaseValue curPtr = new PhiInst(cur.block, startPtr, null, null, null);
            IRBaseValue curPtr = new PhiInst(startPtr.valueType, conditionBlock);
            ((PhiInst) curPtr).addBranch(cur.block, startPtr);

            IRBaseValue nexPtr = new GEPInst(curPtr, curPtr.valueType, null, new IntConst(1, 32));

            // cur.fn.addBlock(conditionBlock);
            // cur.block = conditionBlock;
            // conditionBlock.addInst((IRBaseInst) curPtr);
            // var conditionExpr = new IcmpInst(icmpOperator.irNE, curPtr, endPtr,
            // cur.block);

            cur.fn.blockList.add(bodyBlock);
            cur.block = bodyBlock;
            new StoreInst(arrMalloc((IRPtType) addrType.derefType(), dimList, k + 1), curPtr, cur.block);
            cur.block.addInst((IRBaseInst) nexPtr);
            ((IRBaseInst) nexPtr).parentBlock = cur.block;

            // ((PhiInst) curPtr).block2 = cur.block;
            // ((PhiInst) curPtr).res2 = nexPtr;
            ((PhiInst) curPtr).addBranch(cur.block, nexPtr);

            cur.fn.blockList.add(conditionBlock);
            cur.block = conditionBlock;
            var conditionExpr = new IcmpInst(ICMPOP.ne, curPtr, endPtr, cur.block);

            cur.fn.blockList.add(afterBlock);
            cur.block = afterBlock;

            beforeBlock.tailBlock = afterBlock;

            new JumpInst(conditionBlock, beforeBlock);
            new BrInst(conditionExpr, bodyBlock, afterBlock, conditionBlock.getTail());
            new JumpInst(conditionBlock, bodyBlock.getTail());
        }

        return elePtr;
    }

    @Override
    public void visit(CreatorExprNode it) {
        IRType pt = Transfer.typeTransfer(it.typeName);
        if (it.dimenSize.size() == 0) {
            IRFn fn = getBuiltInFn("__malloc");
            IRBaseValue size = new IntConst((((IRPtType) pt).derefType()).getSize(topModule), 32);
            it.irValue = new CallInst(fn, cur.block, size);

            var classInfo = (ClassType) gScope.typeMap.get(it.typeName.typeNameString);
            if (classInfo.haveConst) {
                IRFn constructor = classInfo.structType.constructFn;
                it.irValue = new CastInst(it.irValue, pt, CastType.bitcast, cur.block);
                it.irValue = new CallInst(constructor, cur.block, it.irValue);
            }
        } else {
            it.irValue = arrMalloc((IRPtType) pt, it.dimenSize, 0);
        }
    }

    private IRFn getBuiltInFn(String funcNameString) {
        for (var fn : topModule.builtinFnList) {
            if (fn.nameString.equals(funcNameString))
                return fn;
        }
        return null;
    }

    private void stringBinary(BinaryOpExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        var binaryOpCode = Transfer.binaryArthTransfer(it.opcode);
        var icmpOpCode = Transfer.binaryCmpTransfer(it.opcode);
        if (binaryOpCode == BOP.add)
            it.irValue = new CallInst(getBuiltInFn("__str_plus"), cur.block, it.lhs.irValue, it.rhs.irValue);
        if (icmpOpCode == ICMPOP.eq)
            it.irValue = new CallInst(getBuiltInFn("__str_eq"), cur.block, it.lhs.irValue, it.rhs.irValue);
        if (icmpOpCode == ICMPOP.ne)
            it.irValue = new CallInst(getBuiltInFn("__str_ne"), cur.block, it.lhs.irValue, it.rhs.irValue);
        if (icmpOpCode == ICMPOP.slt)
            it.irValue = new CallInst(getBuiltInFn("__str_lt"), cur.block, it.lhs.irValue, it.rhs.irValue);
        if (icmpOpCode == ICMPOP.sle)
            it.irValue = new CallInst(getBuiltInFn("__str_le"), cur.block, it.lhs.irValue, it.rhs.irValue);
        if (icmpOpCode == ICMPOP.sgt)
            it.irValue = new CallInst(getBuiltInFn("__str_gt"), cur.block, it.lhs.irValue, it.rhs.irValue);
        if (icmpOpCode == ICMPOP.sge)
            it.irValue = new CallInst(getBuiltInFn("__str_ge"), cur.block, it.lhs.irValue, it.rhs.irValue);
    }

    @Override
    public void visit(BinaryOpExprNode it) {
        if (it.lhs.typeName.equals(gScope.stringName))
            stringBinary(it);
        else if (it.opcode == binaryOp.LOGIC_AND || it.opcode == binaryOp.LOGIC_OR) {
            it.lhs.accept(this);
            IRBasicBlock beforeBlock = cur.block;
            IRBasicBlock rhsBlock = new IRBasicBlock(beforeBlock.loopDepth);
            IRBasicBlock endBlock = new IRBasicBlock(beforeBlock.loopDepth);
            boolean isOr = (it.opcode == binaryOp.LOGIC_OR);

            cur.fn.blockList.add(rhsBlock);
            cur.block = rhsBlock;
            it.rhs.accept(this);
            if (it.rhs.irValue.valueType instanceof IRIntType t && t.intLen != 1)
                it.rhs.irValue = new CastInst(it.rhs.irValue, new IRIntType(1), CastType.trunc, cur.block);

            cur.fn.blockList.add(endBlock);
            cur.block = endBlock;
            beforeBlock.tailBlock = endBlock.getTail();

            if (isOr) {
                new BrInst(it.lhs.irValue, endBlock, rhsBlock, beforeBlock);
                new JumpInst(endBlock, rhsBlock.getTail());
                // it.irValue = new PhiInst(beforeBlock, new IntConst(isOr ? 1 : 0, 1),
                // rhsBlock.getTail(), it.rhs.irValue, endBlock);
                it.irValue = new PhiInst(new IRIntType(1), endBlock);
                ((PhiInst) it.irValue).addBranch(beforeBlock, new IntConst(isOr ? 1 : 0, 1));
                ((PhiInst) it.irValue).addBranch(rhsBlock.getTail(), it.rhs.irValue);

            } else {
                new BrInst(it.lhs.irValue, rhsBlock, endBlock, beforeBlock);
                new JumpInst(endBlock, rhsBlock.getTail());
                // it.irValue = new PhiInst(beforeBlock, new IntConst(isOr ? 1 : 0, 1),
                // rhsBlock.getTail(), it.rhs.irValue, endBlock);
                it.irValue = new PhiInst(new IRIntType(1), endBlock);
                ((PhiInst) it.irValue).addBranch(beforeBlock, new IntConst(isOr ? 1 : 0, 1));
                ((PhiInst) it.irValue).addBranch(rhsBlock.getTail(), it.rhs.irValue);
            }
        } else {
            it.lhs.accept(this);
            it.rhs.accept(this);
            if (it.lhs.typeName.equals(gScope.boolName)) {
                it.lhs.irValue = CastInst.tryBoolCast(it.lhs.irValue, new IRIntType(1), CastType.trunc, cur.block);
                it.rhs.irValue = CastInst.tryBoolCast(it.rhs.irValue, new IRIntType(1), CastType.trunc, cur.block);
            }
            IRBaseValue irValue = null;
            var binaryOpCode = Transfer.binaryArthTransfer(it.opcode);
            var icmpOpCode = Transfer.binaryCmpTransfer(it.opcode);
            if (binaryOpCode != null)
                irValue = new BinaryInst(binaryOpCode, it.lhs.irValue, it.rhs.irValue, cur.block);
            else if (icmpOpCode != null)
                irValue = new IcmpInst(icmpOpCode, it.lhs.irValue, it.rhs.irValue, cur.block);
            else
                throw new MyException("opCode could not find");
            it.irValue = irValue;
        }
    }

    @Override
    public void visit(UnaryOpExprNode it) {
        it.expr.accept(this);
        switch (it.opCode) {
            case BIT_NOT -> {
                it.irValue = new BinaryInst(BOP.xor, it.expr.irValue, new IntConst(-1, 32), cur.block);
            }
            case LOGIC_NOT -> { // bool type : transfer to i1 first
                IRBaseValue i1Value = it.expr.irValue;
                i1Value = CastInst.tryBoolCast(i1Value, new IRIntType(1), CastType.trunc, cur.block);
                it.irValue = new BinaryInst(BOP.xor, i1Value, new IntConst(1, 1), cur.block);
            }
            case PRE_ADD -> {
                it.irValue = it.expr.irValue;
            }
            case PRE_SUB -> {
                it.irValue = new BinaryInst(BOP.sub, new IntConst(0, 32), it.expr.irValue, cur.block);
            }
            case PRE_INC -> {
                it.irValue = new BinaryInst(BOP.add, it.expr.irValue, new IntConst(1, 32), cur.block);
                new StoreInst(it.irValue, it.expr.irAddr, cur.block);
                it.irAddr = it.expr.irAddr;
            }
            case PRE_DEC -> {
                it.irValue = new BinaryInst(BOP.sub, it.expr.irValue, new IntConst(1, 32), cur.block);
                new StoreInst(it.irValue, it.expr.irAddr, cur.block);
                it.irAddr = it.expr.irAddr;
            }
            case SUF_INC -> {
                it.irValue = it.expr.irValue;
                var updatedValue = new BinaryInst(BOP.add, it.expr.irValue, new IntConst(1, 32),
                        cur.block);
                new StoreInst(updatedValue, it.expr.irAddr, cur.block);
            }
            case SUF_DEC -> {
                it.irValue = it.expr.irValue;
                var updatedValue = new BinaryInst(BOP.sub, it.expr.irValue, new IntConst(1, 32),
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
        it.arr.accept(this);
        it.sub.accept(this);
        IRBaseValue arrAddr = it.arr.irValue;
        if (arrAddr == null)
            arrAddr = new LoadInst(it.arr.irAddr, cur.block);
        IRBaseValue subExpr = it.sub.irValue;

        // var tmp = new CastInst(arrAddr, new IRPtType(new IRIntType(32), 1),
        // castType.BIT, cur.block);
        // var tmp1 = new LoadInst(tmp, cur.block);
        // new CallInst(getFnType("printlnInt"), cur.block, tmp1);
        subExpr = new BinaryInst(BOP.add, subExpr, new IntConst(1, 32), cur.block);

        it.irAddr = new GEPInst(arrAddr, arrAddr.valueType, cur.block, subExpr);
        it.irValue = new LoadInst(it.irAddr, cur.block);
    }

    @Override
    public void visit(FuncCallExprNode it) {
        IRFn calledFn = null;
        ArrayList<IRBaseValue> argList = new ArrayList<>();

        calledFn = cur.whoseMember == null ? cur.scope.getFuncInfo(it.FuncNameString, null).irFn
                : cur.whoseMember.getFunc(it.FuncNameString, null).irFn;

        // you can still get the member-function when using cur.scope
        // as innner function call each other
        // so still need add this argument when call

        // if calledFn is a member function
        // && you don't get it from a whoseMember scope(no using "this")
        if (((IRFnType) calledFn.valueType).methodFrom != null && cur.whoseMember == null) {
            var thisPara = cur.fn.paraList.get(0);
            argList.add(new LoadInst(thisPara.storedAddr, cur.block));
        }

        cur.whoseMember = null;

        it.argList.forEach(argExpr -> {
            argExpr.accept(this);
            argList.add(argExpr.irValue);
        });

        it.irValue = new CallInst(calledFn, argList, cur.block);
    }

    @Override
    public void visit(SelfConstructNode it) {
        // since we have got the fnType information

        // 1. build the function information
        IRFn constructor = ((ClassType) gScope.typeMap.get(it.consNameString)).structType.constructFn;
        var constructType = (IRFnType) constructor.valueType;

        // 2. add it to topModule and set the current status
        topModule.globalFnList.add(constructor);
        cur.fn = constructor;
        cur.block = new IRBasicBlock(cur.fn, 0);
        cur.scope = new Scope(cur.scope);

        // 2.1 add retBlock and retAddr
        constructor.retBlock = new IRBasicBlock(0);
        IRType retType = constructType.retType;
        constructor.retValueAddr = new AllocaInst(retType, cur.fn);

        // 3. add the parameter list for IRFn
        TypeIdPair thisPara = new TypeIdPair(new TypeName(it.consNameString, 0, true), "this");
        addParameter(thisPara);

        // 4. visit the function's body
        visit(it.body);

        new StoreInst(cur.fn.paraList.get(0), cur.fn.retValueAddr, cur.block);

        // 5. create terminal instructions for every block
        terminalToRet(constructor);

        // 5.1 terminate the retBlock
        terminateRetBlock(constructor);

        // 6. reset the current status
        cur.fn = null;
        cur.block = null;
        cur.scope = cur.scope.parent;
    }

    @Override
    public void visit(ExprStmtNode it) {
        it.expr.accept(this);
    }

    @Override
    public void visit(IfStmtNode it) {
        it.expr.accept(this);
        var beforeIfBlock = cur.block;

        IRBasicBlock thenBlock = new IRBasicBlock(cur.fn, beforeIfBlock.loopDepth);
        cur.scope = new Scope(cur.scope);
        cur.block = thenBlock;
        it.thenStmt.accept(this);
        cur.scope = cur.scope.parent;

        IRBasicBlock elseBlock = null;
        if (it.elseStmt != null) {
            elseBlock = new IRBasicBlock(cur.fn, beforeIfBlock.loopDepth);
            cur.scope = new Scope(cur.scope);
            cur.block = elseBlock;
            it.elseStmt.accept(this);
            cur.scope = cur.scope.parent;
        }

        IRBasicBlock afterIfBlock = new IRBasicBlock(cur.fn, beforeIfBlock.loopDepth);
        cur.block = afterIfBlock;

        beforeIfBlock.tailBlock = cur.block;

        if (elseBlock != null) {
            new BrInst(it.expr.irValue, thenBlock, elseBlock, beforeIfBlock);
            new JumpInst(afterIfBlock, thenBlock.getTail());
            new JumpInst(afterIfBlock, elseBlock.getTail());
        } else {
            new BrInst(it.expr.irValue, thenBlock, afterIfBlock, beforeIfBlock);
            new JumpInst(afterIfBlock, thenBlock.getTail());
        }
    }

    @Override
    public void visit(WhileStmtNode it) {
        IRBasicBlock beforeBlock = cur.block;
        cur.scope = new Scope(cur.scope);

        IRBasicBlock conditionBlock = new IRBasicBlock(cur.fn, beforeBlock.loopDepth);
        cur.block = conditionBlock;
        it.expr.accept(this);

        IRBasicBlock whileBodyBlock = new IRBasicBlock(cur.fn, beforeBlock.loopDepth + 1);
        IRBasicBlock afterWhileBlock = new IRBasicBlock(beforeBlock.loopDepth);
        cur.block = whileBodyBlock;
        cur.pushInfo(afterWhileBlock, conditionBlock);
        it.body.accept(this);
        cur.popInfo();

        cur.fn.blockList.add(afterWhileBlock);
        cur.block = afterWhileBlock;
        beforeBlock.tailBlock = afterWhileBlock.getTail();

        new JumpInst(conditionBlock, beforeBlock);
        new BrInst(it.expr.irValue, whileBodyBlock, afterWhileBlock, conditionBlock.getTail());
        new JumpInst(conditionBlock, whileBodyBlock.getTail());

        cur.scope = cur.scope.parent;
    }

    @Override
    public void visit(ForStmtNode it) {
        IRBasicBlock beforeBlock = cur.block;
        cur.scope = new Scope(cur.scope);

        if (it.varDecl != null)
            it.varDecl.accept(this);
        else if (it.initExpr != null)
            it.initExpr.accept(this);

        IRBasicBlock conditionBlock = new IRBasicBlock(cur.fn, beforeBlock.loopDepth);
        cur.block = conditionBlock;
        if (it.condExpr != null)
            it.condExpr.accept(this);

        IRBasicBlock forBodyBlock = new IRBasicBlock(cur.fn, beforeBlock.loopDepth + 1);
        IRBasicBlock stepBlock = new IRBasicBlock(beforeBlock.loopDepth + 1); // continue
        IRBasicBlock afterForBlock = new IRBasicBlock(beforeBlock.loopDepth); // break
        cur.block = forBodyBlock;
        cur.pushInfo(afterForBlock, stepBlock);
        it.body.accept(this);
        cur.popInfo();

        cur.fn.blockList.add(stepBlock);
        cur.block = stepBlock;
        if (it.incExpr != null)
            it.incExpr.accept(this);

        cur.fn.blockList.add(afterForBlock);
        cur.block = afterForBlock;
        beforeBlock.tailBlock = afterForBlock.getTail();

        // before -> condition
        new JumpInst(conditionBlock, beforeBlock);
        // condition -> forbody or afeterFor
        if (it.condExpr != null)
            new BrInst(it.condExpr.irValue, forBodyBlock, afterForBlock, conditionBlock.getTail());
        else // condition -> forbody
            new JumpInst(forBodyBlock, conditionBlock.getTail());
        // forbody -> step
        new JumpInst(stepBlock, forBodyBlock.getTail());
        // step -> condition
        new JumpInst(conditionBlock, stepBlock.getTail());

        cur.scope = cur.scope.parent;
    }

    @Override
    public void visit(ReturnStmtNode it) {
        if (it.expr == null)// return void
            new JumpInst(cur.fn.retBlock, cur.block);
        else {
            it.expr.accept(this);
            if (it.expr.irValue.valueType instanceof IRIntType b && b.intLen == 1)
                it.expr.irValue = new CastInst(it.expr.irValue, new IRIntType(8), CastType.zext, cur.block);
            new StoreInst(it.expr.irValue, cur.fn.retValueAddr, cur.block);
            new JumpInst(cur.fn.retBlock, cur.block);
        }
    }

    @Override
    public void visit(ContinueStmtNode it) {
        new JumpInst(cur.getCurContinue(), cur.block);
    }

    @Override
    public void visit(BreakStmtNode it) {
        new JumpInst(cur.getCurBreak(), cur.block);
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
            var gep = new GEPInst(it.expr.irValue, gepInstType, cur.block, new IntConst(0, 32),
                    ((IntConst) it.idExpr.irValue));
            it.irAddr = gep;
            if (!onlyAddr)
                it.irValue = new LoadInst(gep, cur.block);
        } else if (it.funcCall != null) {
            if (it.funcCall.FuncNameString.equals("size") && it.expr.typeName.dimen > 0) {
                var sizeAddr = new CastInst(it.expr.irValue, new IRPtType(new IRIntType(32), 1), CastType.bitcast,
                        cur.block);
                it.irValue = new LoadInst(sizeAddr, cur.block);
            } else {
                Boolean isMemberFunction = cur.whoseMember != null;
                it.funcCall.accept(this);
                if (isMemberFunction)
                    ((CallInst) it.funcCall.irValue).insertOprand(0, it.expr.irValue);
                it.irValue = it.funcCall.irValue;
            }
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
                yield new GEPInst(constStr, IRPtType.getCharRefType(), cur.block, new IntConst(0, 32),
                        new IntConst(0, 32));
            }
            case NULL -> new NullConst();
            case TRUE -> new IntConst(1, 1);
            case FALSE -> new IntConst(0, 1);
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
            var gep = new GEPInst(startPtr, gepInstType, cur.block, new IntConst(0, 32), idx);

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
            var initFnNameString = "__mx_global_var_init." + gVar.getName();
            gVar.initFn = IRFn.getInitFn(initFnNameString);
            topModule.varInitFnList.add(gVar.initFn);

            cur.fn = gVar.initFn;
            var block = new IRBasicBlock(cur.fn, 0);
            cur.block = block;
            gVar.initFn.retBlock = new IRBasicBlock(0);

            gVar.isInit = true;
            gVar.initValue = gVar.derefType.defaultValue();
            it.expr.accept(this);
            new StoreInst(it.expr.irValue, gVar, cur.block);

            terminalToRet(cur.fn);
            terminateRetBlock(cur.fn);

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
            it.irValue = it.decl.varValue = new AllocaInst(Transfer.typeTransfer(it.decl.typeName), cur.fn);
            if (it.expr != null) {
                it.expr.accept(this);
                new StoreInst(it.expr.irValue, it.irValue, cur.block);
            }
        }

        cur.scope.putDef(it.decl);
    }
}
