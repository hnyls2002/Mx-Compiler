package Middleend.IROptimize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import IR.IRModule;
import IR.IRType.IRIntType;
import IR.IRType.IRPtType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.ConsData.IntConst;
import IR.IRValue.IRUser.ConsValue.ConsData.NullConst;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;
import IR.IRValue.IRUser.IRInst.BinaryInst;
import IR.IRValue.IRUser.IRInst.BrInst;
import IR.IRValue.IRUser.IRInst.CallInst;
import IR.IRValue.IRUser.IRInst.CastInst;
import IR.IRValue.IRUser.IRInst.GEPInst;
import IR.IRValue.IRUser.IRInst.IRBaseInst;
import IR.IRValue.IRUser.IRInst.IcmpInst;
import IR.IRValue.IRUser.IRInst.JumpInst;
import IR.IRValue.IRUser.IRInst.LoadInst;
import IR.IRValue.IRUser.IRInst.MoveInst;
import IR.IRValue.IRUser.IRInst.PhiInst;
import IR.IRValue.IRUser.IRInst.RetInst;
import IR.IRValue.IRUser.IRInst.StoreInst;
import Middleend.IROptimize.Tools.CriticalSpliter;
import Middleend.IROptimize.Tools.InfosRebuilder;
import Share.MyException;
import Share.Lang.LLVMIR.ICMPOP;
import Share.Pass.IRPass.IRFnPass;
import Share.Pass.IRPass.IRModulePass;
import Share.Visitors.IRInstVisitor;

public class SCCP implements IRModulePass, IRFnPass, IRInstVisitor {
    /*
     * After mem2reg, no value will be used before it is defined.
     * 
     * Implementation according to Tiger book, page 317.
     */

    @Override
    public void runOnIRModule(IRModule irModule) {
        // using edge splitting to make sure that
        // a phi's predecessor edges can be uniquely identified by a basic block
        new CriticalSpliter().splitCriticalEdge(irModule);
        new InfosRebuilder().rebuildDefUse(irModule);
        irModule.globalFnList.forEach(this::runOnIRFn);
        irModule.varInitFnList.forEach(this::runOnIRFn);
        System.err.println("SCCP: " + constCount + " constants, " + invalidBrCount + " invalid branches");
    }

    // 0 -> undefined, 1 -> constant, 2 -> unknown
    Queue<IRBaseValue> workList = new LinkedList<>();
    HashMap<IRBaseValue, Integer> latticeMap = new HashMap<>();
    HashMap<IRBaseValue, Integer> constantMap = new HashMap<>();
    HashSet<IRBasicBlock> availableBBSet = new HashSet<>();
    HashMap<BrInst, IRBasicBlock> br2jumpMap = new HashMap<>();
    public int constCount = 0, invalidBrCount = 0;

    public void init(IRFn fn) {
        workList.clear();
        latticeMap.clear();
        constantMap.clear();
        availableBBSet.clear();
        br2jumpMap.clear();

        // offer the first block
        updateBlock(fn.blockList.get(0));

        // offer the parameters
        fn.paraList.forEach(para -> updateLattice(para, 2, 0));

        // offer the unknown value
        var tempList = new LinkedList<>(fn.blockList);
        tempList.add(fn.retBlock);
        tempList.forEach(block -> {
            block.instList.forEach(inst -> {
                if (inst instanceof LoadInst
                        || inst instanceof GEPInst
                        || inst instanceof CallInst)
                    updateLattice(inst, 2, 0);
            });
        });
    }

    @Override
    public void runOnIRFn(IRFn fn) {
        // System.err.println("--------------------");
        // System.err.println("SCCP on " + fn.getName());
        init(fn);

        // constant propagation
        while (!workList.isEmpty()) {
            var val = workList.poll();
            for (var user : val.userList) {
                // System.err.println("user " + ((IRBaseInst) user).formatDef());
                ((IRBaseInst) user).accept(this);
            }
            if (val instanceof IRBasicBlock block) {
                for (var inst : block.instList)
                    inst.accept(this);
                for (var inst : block.phiList)
                    inst.accept(this);
            }
        }

        // replace with constant
        latticeMap.forEach((val, lat) -> {
            if (lat == 1) {
                var constValue = constantMap.get(val);
                if (val.valueType instanceof IRIntType intType)
                    val.replaceAllUseWith(new IntConst(constValue, intType.intLen));
                else if (constValue == 0 && val.valueType instanceof IRPtType)
                    val.replaceAllUseWith(new NullConst());
                else
                    throw new MyException("SCCP: unknown const type to replace");
            }
        });

        // reorganize the code : br -> jump , phi -> move or less pre,
        ArrayList<IRBasicBlock> blockList = new ArrayList<>(fn.blockList);
        blockList.add(fn.retBlock);
        blockList.forEach(block -> {
            if (availableBBSet.contains(block)) {

                if (block.terminal instanceof BrInst br && br2jumpMap.containsKey(br)) {
                    var toBlock = br2jumpMap.get(br);
                    block.removeTerminal();
                    new JumpInst(toBlock, block);
                }

                var it = block.phiList.iterator();
                while (it.hasNext()) {
                    var inst = it.next();
                    while (true) {
                        boolean flag = false;
                        for (int i = 0; i < inst.getOprandNum(); i += 2) {
                            var pred = inst.getOprand(i);
                            if (!availableBBSet.contains(pred)) {
                                flag = true;
                                inst.removeOp(i);
                                inst.removeOp(i);
                                break;
                            }
                        }
                        if (!flag)
                            break;
                    }
                }
            }
        });

        // for visualizing
        for (var val : latticeMap.values())
            constCount += val == 1 ? 1 : 0;
        invalidBrCount += br2jumpMap.size();
    }

    @Override
    public void visit(BinaryInst inst) {
        var val1 = inst.getOprand(0);
        var val2 = inst.getOprand(1);
        var lat1 = getLatticeStatus(val1);
        var lat2 = getLatticeStatus(val2);
        if (lat1 == 0 || lat2 == 0)
            return;
        if (lat1 == 2 || lat2 == 2) {
            updateLattice(inst, 2, 0);
            return;
        }
        var c1 = getConstInt(val1);
        var c2 = getConstInt(val2);
        int res = switch (inst.opCode) {
            case add -> c1 + c2;
            case and -> c1 & c2;
            case ashr -> c1 >> c2;
            case mul -> c1 * c2;
            case or -> c1 | c2;
            case sdiv -> c1 / c2;
            case shl -> c1 << c2;
            case srem -> c1 % c2;
            case sub -> c1 - c2;
            case xor -> c1 ^ c2;
        };
        updateLattice(inst, 1, res);
    }

    @Override
    public void visit(BrInst inst) {
        var condition = inst.getOprand(0);
        int latCond = getLatticeStatus(condition);
        if (latCond == 0)
            return;
        else if (latCond == 1) {
            int c = getConstInt(condition);
            var gotoBlock = c == 0 ? inst.getOprand(2) : inst.getOprand(1);
            updateBlock((IRBasicBlock) gotoBlock);
            br2jumpMap.put(inst, (IRBasicBlock) gotoBlock);
        } else {
            br2jumpMap.remove(inst);
            for (int i = 1; i <= 2; ++i) {
                var gotoBlock = inst.getOprand(i);
                updateBlock((IRBasicBlock) gotoBlock);
            }
        }
    }

    @Override
    public void visit(IcmpInst inst) {
        var val1 = inst.getOprand(0);
        var val2 = inst.getOprand(1);
        var lat1 = getLatticeStatus(val1);
        var lat2 = getLatticeStatus(val2);
        if (lat1 == 0 || lat2 == 0)
            return;
        if (lat1 == 2 || lat2 == 2) {
            updateLattice(inst, 2, 0);
            return;
        }
        if (val1 instanceof NullConst || val2 instanceof NullConst) {
            if (val1 instanceof NullConst && val2 instanceof NullConst)
                updateLattice(inst, 1, inst.opCode == ICMPOP.eq ? 1 : 0);
            else
                throw new MyException("Null can only be compared with null");
            return;
        }
        var c1 = getConstInt(val1);
        var c2 = getConstInt(val2);
        int res = switch (inst.opCode) {
            case eq -> c1 == c2 ? 1 : 0;
            case ne -> c1 != c2 ? 1 : 0;
            case sgt -> c1 > c2 ? 1 : 0;
            case sge -> c1 >= c2 ? 1 : 0;
            case slt -> c1 < c2 ? 1 : 0;
            case sle -> c1 <= c2 ? 1 : 0;
        };
        updateLattice(inst, 1, res);
    }

    @Override
    public void visit(JumpInst inst) {
        updateBlock((IRBasicBlock) inst.getOprand(0));
    }

    @Override
    public void visit(PhiInst inst) {
        if (getLatticeStatus(inst) == 2)
            return;

        ArrayList<IRBaseValue> resList = new ArrayList<>();
        for (int i = 0; i < inst.getOprandNum(); i += 2) {
            var preBlock = inst.getOprand(i);
            var val = inst.getOprand(i + 1);
            if (availableBBSet.contains(preBlock))
                resList.add(val);
        }

        // not defined yet
        if (resList.size() == 0)
            return;

        // one is unknown
        for (var res : resList)
            if (getLatticeStatus(res) == 2) {
                updateLattice(inst, 2, 0);
                return;
            }

        // any two are different
        for (var res1 : resList)
            for (var res2 : resList) {
                if (getLatticeStatus(res1) == 1 && getLatticeStatus(res2) == 1
                        && getConstInt(res1) != getConstInt(res2)) {
                    updateLattice(inst, 2, 0);
                    return;
                }
            }

        if (getLatticeStatus(inst) == 1)
            return;

        // all const int are the same or some undefined
        for (var res : resList)
            if (getLatticeStatus(res) == 1) {
                updateLattice(inst, 1, getConstInt(res));
                return;
            }
    }

    @Override
    public void visit(CastInst inst) {
        var latSrc = getLatticeStatus(inst.getOprand(0));
        var latDst = getLatticeStatus(inst);
        if (latSrc == latDst)
            return;
        if (latSrc == 1)
            updateLattice(inst, 1, getConstInt(inst.getOprand(0)));
        else if (latSrc == 2)
            updateLattice(inst, 2, 0);
        else
            throw new MyException("CastInst: lattice status error");
    }

    @Override
    public void visit(CallInst inst) {
    }

    @Override
    public void visit(GEPInst inst) {
    }

    @Override
    public void visit(LoadInst inst) {
    }

    @Override
    public void visit(RetInst inst) {
    }

    @Override
    public void visit(StoreInst inst) {
    }

    @Override
    public void visit(MoveInst inst) {
        // no move inst during SSA optimization
    }

    private int getLatticeStatus(IRBaseValue val) {
        if (val instanceof IntConst || val instanceof NullConst)
            return 1;
        else {
            if (!latticeMap.containsKey(val))
                latticeMap.put(val, 0);
            return latticeMap.get(val);
        }
    }

    private int getConstInt(IRBaseValue val) {
        if (getLatticeStatus(val) != 1)
            throw new MyException("Constant lattice status expected)");
        if (val instanceof IntConst c)
            return c.constValue;
        else if (val instanceof NullConst)
            return 0;
        else if (val instanceof IRBaseInst inst && inst.valueType instanceof IRIntType)
            return constantMap.get(inst);
        else if (val.valueType instanceof IRPtType)
            return constantMap.get(val);
        throw new MyException("Unexpected value type in getConstInt");
    }

    private void updateLattice(IRBaseValue val, int lat, int constValue) {
        // System.err
        // .println("update " + val.formatUse() + " to lat : " + lat + (lat == 1 ? "
        // const : " + constValue : ""));
        // already updated
        if (getLatticeStatus(val) == lat)
            return;
        if (lat == 1) {
            latticeMap.replace(val, lat);
            constantMap.put(val, constValue);
            workList.offer(val);
        } else {
            latticeMap.replace(val, lat);
            constantMap.remove(val);
            workList.offer(val);
        }
    }

    private void updateBlock(IRBasicBlock block) {
        if (availableBBSet.contains(block))
            return;
        // System.err.println("update block " + block.getName());
        availableBBSet.add(block);
        workList.add(block);
    }
}
