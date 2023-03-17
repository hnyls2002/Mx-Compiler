package Backend.RegAllocate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.antlr.v4.runtime.misc.Pair;

import ASM.ASMFn;
import ASM.ASMModule;
import ASM.ASMInst.ASMLoadInst;
import ASM.ASMInst.ASMMoveInst;
import ASM.ASMInst.ASMStoreInst;
import ASM.ASMOprand.PhysicalReg;
import ASM.ASMOprand.Register;
import ASM.ASMOprand.VirtualOffset;
import ASM.ASMOprand.VirtualReg;
import Share.MyException;
import Share.Lang.RV32.BitWidth;
import Share.Lang.RV32.SPLabel;
import Share.Pass.ASMPass.ASMFnPass;
import Share.Pass.ASMPass.ASMModulePass;

public class RegisterColoring implements ASMModulePass, ASMFnPass {
    // each node only belongs to one of the following lists
    final PhysicalReg sp = PhysicalReg.getPhyReg("sp");
    final HashSet<Register> preColored = new HashSet<>(PhysicalReg.assignableSet);
    HashSet<Register> initial = new HashSet<>();
    HashSet<Register> simplifyWorkList = new HashSet<>();
    HashSet<Register> freezeWorkList = new HashSet<>();
    HashSet<Register> spillWorkList = new HashSet<>();
    HashSet<Register> spilledNodes = new HashSet<>();
    HashSet<Register> coalescedNodes = new HashSet<>();
    HashSet<Register> coloredNodes = new HashSet<>();
    Stack<Register> selectStack = new Stack<>();

    // each move instruction only belongs to one of the following lists
    HashSet<ASMMoveInst> coalescedMoves = new HashSet<>();
    HashSet<ASMMoveInst> constrainedMoves = new HashSet<>();
    HashSet<ASMMoveInst> frozenMoves = new HashSet<>();
    HashSet<ASMMoveInst> workListMoves = new HashSet<>();
    HashSet<ASMMoveInst> activeMoves = new HashSet<>();

    // other
    HashSet<Pair<Register, Register>> adjSet = new HashSet<>();

    class NodeInfo {
        int degree;
        double loopCost = 0;
        Register alias, color;
        HashSet<Register> adjList = new HashSet<>();
        HashSet<ASMMoveInst> moveList = new HashSet<>();
        VirtualOffset spillPlace;

        public NodeInfo(Register reg) {
            alias = reg;
            if (reg instanceof PhysicalReg) {
                degree = 1000000;
                color = reg;
            } else {
                degree = 0;
                color = null;
            }
        }
    }

    HashMap<Register, NodeInfo> nodeInfoMap = new HashMap<>();

    static final int K = PhysicalReg.assignableSet.size();

    @Override
    public void runOnASMModule(ASMModule asmModule) {
        asmModule.fnList.forEach(this::runOnASMFn);
    }

    private void InfoInit(ASMFn asmFn) {
        initial.clear();
        simplifyWorkList.clear();
        freezeWorkList.clear();
        spillWorkList.clear();
        spilledNodes.clear();
        coalescedNodes.clear();
        coloredNodes.clear();
        selectStack.clear();
        coalescedMoves.clear();
        constrainedMoves.clear();
        frozenMoves.clear();
        workListMoves.clear();
        activeMoves.clear();
        adjSet.clear();
        nodeInfoMap.clear();
        for (var block : asmFn.blockList)
            for (var inst : block.instList)
                for (var reg : inst.getOprands())
                    if (reg instanceof VirtualReg)
                        initial.add(reg);
    }

    @Override
    public void runOnASMFn(ASMFn asmFn) {
        InfoInit(asmFn);
        run(asmFn);
        // replace virtual register with physical register
        for (var block : asmFn.blockList)
            for (var inst : block.instList) {
                if (inst.rd != null && inst.rd.isAssignable())
                    inst.rd = nodeInfoMap.get(inst.rd).color;
                if (inst.rs1 != null && inst.rs1.isAssignable())
                    inst.rs1 = nodeInfoMap.get(inst.rs1).color;
                if (inst.rs2 != null && inst.rs2.isAssignable())
                    inst.rs2 = nodeInfoMap.get(inst.rs2).color;
            }
    }

    private void nodeInfoInit(ASMFn asmFn) {
        nodeInfoMap.clear();
        for (var reg : initial)
            nodeInfoMap.put(reg, new NodeInfo(reg));
        for (var reg : preColored)
            nodeInfoMap.put(reg, new NodeInfo(reg));
        for (var block : asmFn.blockList)
            for (var inst : block.instList)
                for (var reg : inst.getOprands())
                    nodeInfoMap.get(reg).loopCost += Math.pow(10, block.loopDepth);
    }

    public void run(ASMFn asmFn) {
        new LivenessAnalysis().runOnASMFn(asmFn);

        if (!checkInitial(asmFn)) {
            displayReg();
            throw new MyException("checkInitial");
        }

        // Then all nodes are in either initial or preColored
        nodeInfoInit(asmFn);

        build(asmFn);
        makeWorkList();
        while (true) {

            try {
                checkOverlap();
            } catch (MyException e) {
                System.err.println(e.getMessage());
                displayReg();
                throw new MyException("checkOverlap");
            }

            if (!simplifyWorkList.isEmpty())
                simplify();
            else if (!workListMoves.isEmpty())
                coalesce();
            else if (!freezeWorkList.isEmpty())
                freeze();
            else if (!spillWorkList.isEmpty())
                selectSpill();
            if (simplifyWorkList.isEmpty() && workListMoves.isEmpty() && freezeWorkList.isEmpty()
                    && spillWorkList.isEmpty())
                break;
        }
        assignColors();
        if (!spilledNodes.isEmpty()) {
            rewriteProgram(asmFn);
            run(asmFn);
        }
    }

    private void build(ASMFn asmFn) {
        for (var block : asmFn.blockList) {
            var live = new HashSet<Register>(block.liveOutSet);
            for (int i = block.instList.size() - 1; i >= 0; --i) {
                var inst = block.instList.get(i);
                if (inst instanceof ASMMoveInst mvInst) {
                    live.removeAll(mvInst.getUse());
                    HashSet<Register> mvReg = new HashSet<>(mvInst.getUse());
                    mvReg.addAll(mvInst.getDef());
                    mvReg.forEach(reg -> nodeInfoMap.get(reg).moveList.add(mvInst));
                    workListMoves.add(mvInst);
                }
                live.addAll(inst.getDef());
                for (var d : inst.getDef()) // this inst's def
                    for (var l : live) // live out
                        addEdge(d, l);
                live.removeAll(inst.getDef());
                live.addAll(inst.getUse());
            }
        }
    }

    private void addEdge(Register u, Register v) {
        if (u == v || adjSet.contains(new Pair<>(u, v)))
            return;
        adjSet.add(new Pair<>(u, v));
        adjSet.add(new Pair<>(v, u));
        if (!preColored.contains(u)) {
            nodeInfoMap.get(u).adjList.add(v);
            nodeInfoMap.get(u).degree++;
        }
        if (!preColored.contains(v)) {
            nodeInfoMap.get(v).adjList.add(u);
            nodeInfoMap.get(v).degree++;
        }
    }

    private void makeWorkList() {
        for (var n : initial) {
            if (nodeInfoMap.get(n).degree >= K)
                spillWorkList.add(n);
            else if (moveRelated(n))
                freezeWorkList.add(n);
            else
                simplifyWorkList.add(n);
        }
        initial.clear();
    }

    private HashSet<Register> adjacent(Register n) {
        var ret = new HashSet<Register>(nodeInfoMap.get(n).adjList);
        ret.removeAll(selectStack);
        ret.removeAll(coalescedNodes);
        return ret;
    }

    private HashSet<ASMMoveInst> nodeMoves(Register n) {
        var ret = new HashSet<ASMMoveInst>(activeMoves);
        ret.addAll(workListMoves);
        ret.retainAll(nodeInfoMap.get(n).moveList);
        return ret;
    }

    private boolean moveRelated(Register n) {
        return !nodeMoves(n).isEmpty();
    }

    private void simplify() {
        var it = simplifyWorkList.iterator();
        if (!it.hasNext())
            return;
        var n = it.next();
        it.remove();
        selectStack.push(n);
        for (var m : adjacent(n))
            decrementDegree(m);
    }

    private void decrementDegree(Register m) {
        int d = nodeInfoMap.get(m).degree;
        nodeInfoMap.get(m).degree = d - 1;
        if (d == K) {
            var set = new HashSet<>(adjacent(m));
            set.add(m);
            enableMoves(set);
            spillWorkList.remove(m);
            if (moveRelated(m))
                freezeWorkList.add(m);
            else
                simplifyWorkList.add(m);
        }
    }

    private void enableMoves(HashSet<Register> nodes) {
        for (var n : nodes) {
            for (var m : nodeMoves(n)) {
                if (activeMoves.contains(m)) {
                    activeMoves.remove(m);
                    workListMoves.add(m);
                }
            }
        }
    }

    private void coalesce() {
        var it = workListMoves.iterator();
        if (!it.hasNext())
            return;
        var m = it.next();
        it.remove();
        var u = getAlias(m.rd);
        var v = getAlias(m.rs1);
        if (preColored.contains(v)) {
            var tmp = u;
            u = v;
            v = tmp;
        }
        if (u == v) { // already coalesced
            coalescedMoves.add(m);
            addWorkList(u);
        } else if (preColored.contains(v) || adjSet.contains(new Pair<>(u, v))) {
            // u and v are both precolored or u and v are adjacent
            constrainedMoves.add(m);
            addWorkList(u);
            addWorkList(v);
        } else {
            boolean flag1 = preColored.contains(u);
            boolean flag2 = false;
            for (var t : adjacent(v))
                if (OK(t, u)) {
                    flag2 = true;
                    break;
                }
            boolean flag3 = !preColored.contains(u);
            var set = new HashSet<>(adjacent(u));
            set.addAll(adjacent(v));
            boolean flag4 = conservative(set);
            if (flag1 && flag2 || flag3 && flag4) {
                coalescedMoves.add(m);
                combine(u, v);
                addWorkList(u);
            } else
                activeMoves.add(m);
        }
    }

    private void addWorkList(Register u) {
        if (!preColored.contains(u) && !moveRelated(u) && nodeInfoMap.get(u).degree < K) {
            freezeWorkList.remove(u);
            simplifyWorkList.add(u);
        }
    }

    private boolean OK(Register t, Register r) {
        return nodeInfoMap.get(t).degree < K || preColored.contains(t) || adjSet.contains(new Pair<>(t, r));
    }

    private boolean conservative(HashSet<Register> nodes) {
        int k = 0;
        for (var n : nodes)
            if (nodeInfoMap.get(n).degree >= K)
                k++;
        return k < K;
    }

    private Register getAlias(Register n) {
        if (coalescedNodes.contains(n))
            return getAlias(nodeInfoMap.get(n).alias);
        else
            return n;
    }

    private void combine(Register u, Register v) {
        // combine v into u
        if (freezeWorkList.contains(v))
            freezeWorkList.remove(v);
        else
            spillWorkList.remove(v);
        coalescedNodes.add(v);
        nodeInfoMap.get(v).alias = u;
        nodeInfoMap.get(u).moveList.addAll(nodeInfoMap.get(v).moveList);
        enableMoves(new HashSet<>(Set.of(v)));
        for (var t : adjacent(v)) {
            addEdge(t, u);
            decrementDegree(t);
        }
        if (nodeInfoMap.get(u).degree >= K && freezeWorkList.contains(u)) {
            freezeWorkList.remove(u);
            spillWorkList.add(u);
        }
    }

    private void freeze() {
        var it = freezeWorkList.iterator();
        if (!it.hasNext())
            return;
        var u = it.next();
        it.remove();
        simplifyWorkList.add(u);
        freezeMoves(u);
    }

    private void freezeMoves(Register u) {
        for (var m : nodeMoves(u)) {
            var x = m.rd;
            var y = m.rs1;
            var v = getAlias(y) == getAlias(u) ? getAlias(x) : getAlias(y);
            activeMoves.remove(m);
            frozenMoves.add(m);
            if (nodeMoves(v).isEmpty() && nodeInfoMap.get(v).degree < K) {
                freezeWorkList.remove(v);
                simplifyWorkList.add(v);
            }
        }
    }

    private void selectSpill() {
        double minCost = Double.MAX_VALUE;
        Register minCostNode = null;
        for (var n : spillWorkList) {
            double cost = nodeInfoMap.get(n).loopCost / nodeInfoMap.get(n).degree;
            if (cost < minCost) {
                minCost = cost;
                minCostNode = n;
            }
        }
        spillWorkList.remove(minCostNode);
        simplifyWorkList.add(minCostNode);
        freezeMoves(minCostNode);
    }

    private void assignColors() {
        while (!selectStack.isEmpty()) {
            var n = selectStack.pop();
            var okColors = new HashSet<>(PhysicalReg.assignableSet);
            for (var w : nodeInfoMap.get(n).adjList) {
                if (coloredNodes.contains(getAlias(w)) || preColored.contains(getAlias(w)))
                    okColors.remove(nodeInfoMap.get(getAlias(w)).color);
            }
            if (okColors.isEmpty())
                spilledNodes.add(n);
            else {
                coloredNodes.add(n);
                nodeInfoMap.get(n).color = okColors.iterator().next();
            }
        }
        for (var n : coalescedNodes)
            nodeInfoMap.get(n).color = nodeInfoMap.get(getAlias(n)).color;
    }

    private void rewriteProgram(ASMFn asmFn) {
        for (var v : spilledNodes)
            nodeInfoMap.get(v).spillPlace = new VirtualOffset(SPLabel.spilledReg, asmFn.spilledRegCnt++);

        HashSet<Register> newTemps = new HashSet<>();

        for (var block : asmFn.blockList) {
            var rawList = new ArrayList<>(block.instList);
            block.instList.clear();
            for (var inst : rawList) {
                var useSet = new HashSet<>(inst.getUse());
                var defSet = new HashSet<>(inst.getDef());
                for (var v : useSet)
                    if (spilledNodes.contains(v)) {
                        var nv = new VirtualReg(asmFn);
                        newTemps.add(nv);
                        inst.replaceUse(v, nv);
                        new ASMLoadInst(nv, sp, nodeInfoMap.get(v).spillPlace, BitWidth.w, block);
                    }
                block.instList.add(inst);
                for (var v : defSet)
                    if (spilledNodes.contains(v)) {
                        var nv = new VirtualReg(asmFn);
                        newTemps.add(nv);
                        inst.replaceDef(v, nv);
                        new ASMStoreInst(sp, nv, nodeInfoMap.get(v).spillPlace, BitWidth.w, block);
                    }
            }
        }

        // reorganize initial and other work lists
        spilledNodes.clear();
        initial.addAll(newTemps);
        initial.addAll(coalescedNodes);
        initial.addAll(coloredNodes);
        coloredNodes.clear();
        coalescedNodes.clear();

        // clear the move lists
        coalescedMoves.clear();
        constrainedMoves.clear();
        frozenMoves.clear();
        workListMoves.clear();
        activeMoves.clear();

        // clear other information
        adjSet.clear();

        // then all the information should be prepared for the next round of coloring

        System.err.println("spilled " + asmFn.spilledRegCnt + " registers");
    }

    private void printRegSet(HashSet<Register> set, String name) {
        System.err.println("\n***********************");
        System.err.println(name + " " + set.size());
        set.forEach(reg -> System.err.print(reg.format() + " "));
        System.err.println("\n-----------------------\n\n");
    }

    private void displayReg() {
        printRegSet(coalescedNodes, "coalescedNodes");
        printRegSet(coloredNodes, "coloredNodes");
        printRegSet(initial, "initial");
        printRegSet(spilledNodes, "spilledNodes");
        printRegSet(preColored, "preColored");
        printRegSet(new HashSet<>(selectStack), "selectStack");
        printRegSet(simplifyWorkList, "simplifyWorkList");
        printRegSet(freezeWorkList, "freezeWorkList");
        printRegSet(spillWorkList, "spillWorkList");
    }

    private boolean checkUnique(HashSet<Register> set1, HashSet<Register> set2) {
        for (var reg : set1)
            if (set2.contains(reg))
                return false;
        return true;
    }

    private void checkOverlap() {
        HashSet<Register> set = new HashSet<>(PhysicalReg.assignableSet);
        if (!checkUnique(set, initial))
            throw new MyException("initial");
        set.addAll(initial);
        if (!checkUnique(set, simplifyWorkList))
            throw new MyException("simplifyWorkList");
        set.addAll(simplifyWorkList);
        if (!checkUnique(set, freezeWorkList))
            throw new MyException("freezeWorkList");
        set.addAll(freezeWorkList);
        if (!checkUnique(set, spillWorkList))
            throw new MyException("spillWorkList");
        set.addAll(spillWorkList);
        if (!checkUnique(set, spilledNodes))
            throw new MyException("spilledNodes");
        set.addAll(spilledNodes);
        if (!checkUnique(set, coalescedNodes))
            throw new MyException("coalescedNodes");
        set.addAll(coalescedNodes);
        if (!checkUnique(set, coloredNodes))
            throw new MyException("coloredNodes");
        set.addAll(coloredNodes);
        if (!checkUnique(set, new HashSet<>(selectStack)))
            throw new MyException("selectStack");
        set.addAll(selectStack);
    }

    private boolean checkInitial(ASMFn asmFn) {
        HashSet<Register> set = new HashSet<>();
        for (var block : asmFn.blockList)
            for (var inst : block.instList)
                for (var reg : inst.getOprands())
                    if (reg instanceof VirtualReg)
                        set.add(reg);
        if (!set.equals(initial))
            return false;
        if (simplifyWorkList.size() != 0)
            return false;
        if (freezeWorkList.size() != 0)
            return false;
        if (spillWorkList.size() != 0)
            return false;
        if (spilledNodes.size() != 0)
            return false;
        if (coalescedNodes.size() != 0)
            return false;
        if (coloredNodes.size() != 0)
            return false;
        if (selectStack.size() != 0)
            return false;
        return true;
    }

}
