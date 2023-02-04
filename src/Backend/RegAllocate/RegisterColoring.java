package Backend.RegAllocate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import ASM.ASMFn;
import ASM.ASMModule;
import ASM.ASMInst.ASMBaseInst;
import ASM.ASMInst.ASMBrInst;
import ASM.ASMInst.ASMJInst;
import ASM.ASMInst.ASMLoadInst;
import ASM.ASMInst.ASMMoveInst;
import ASM.ASMInst.ASMStoreInst;
import ASM.ASMOprand.PhysicalReg;
import ASM.ASMOprand.Register;
import ASM.ASMOprand.StackOffset;
import ASM.ASMOprand.VirtualReg;
import ASM.ASMOprand.StackOffset.stackDataKind;
import Share.Lang.RV32;
import Share.Pass.ASMPass.ASMFnPass;
import Share.Pass.ASMPass.ASMModulePass;

public class RegisterColoring implements ASMModulePass, ASMFnPass {

    @Override
    public void runOnASMModule(ASMModule asmModule) {
        asmModule.fnList.forEach(this::runOnASMFn);
    }

    @Override
    public void runOnASMFn(ASMFn asmFn) {
        // init
        preColored.clear();
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

        for (var curBlock : asmFn.blockList)
            for (var curInst : curBlock.instList) {
                for (var reg : curInst.getDef())
                    reg.coloringInit();
                for (var reg : curInst.getUse())
                    reg.coloringInit();
            }

        for (var curBlock : asmFn.blockList) {
            for (var curInst : curBlock.instList) {
                // add edges between block
                if (curInst instanceof ASMBrInst brInst) {
                    curBlock.sucList.add(brInst.trueBlock);
                    brInst.trueBlock.preList.add(curBlock);
                } else if (curInst instanceof ASMJInst jInst) {
                    curBlock.sucList.add(jInst.jumpBlock);
                    jInst.jumpBlock.preList.add(curBlock);
                }

                // initial and precolored worklist
                HashSet<Register> regSet = new HashSet<>();
                regSet.addAll(curInst.getDef());
                regSet.addAll(curInst.getUse());
                for (var reg : regSet) {
                    if (reg instanceof VirtualReg)
                        initial.add(reg);
                    else
                        preColored.add(reg);
                }

                // calculate the loop index
                for (var reg : curInst.getDef())
                    reg.loopIndex += Math.pow(10, curBlock.loopNum);
                for (var reg : curInst.getUse())
                    reg.loopIndex += Math.pow(10, curBlock.loopNum);

            }
        }

        work(asmFn);
    }

    public class Edge {
        Register u, v;

        public Edge(Register u, Register v) {
            this.u = u;
            this.v = v;
        }

        @Override
        public int hashCode() {
            return u.hashCode() ^ v.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Edge e && u.equals(e.u) && v.equals(e.v);
        }
    };

    // register's data structure
    HashSet<Register> preColored = new HashSet<>(); // physical register
    HashSet<Register> initial = new HashSet<>(); // unhandled register
    LinkedList<Register> simplifyWorkList = new LinkedList<>(); // not move related and low-degree
    LinkedList<Register> freezeWorkList = new LinkedList<>(); // move related and low-degree
    LinkedList<Register> spillWorkList = new LinkedList<>(); // to be spilled
    HashSet<Register> spilledNodes = new HashSet<>(); // hi-degree
    HashSet<Register> coalescedNodes = new HashSet<>(); // already coalesced register
    HashSet<Register> coloredNodes = new HashSet<>(); // already colored register
    Stack<Register> selectStack = new Stack<>(); // temporarily removed, waiting to

    // move instruction's structure
    HashSet<ASMMoveInst> coalescedMoves = new HashSet<>();
    HashSet<ASMMoveInst> constrainedMoves = new HashSet<>();
    HashSet<ASMMoveInst> frozenMoves = new HashSet<>();
    LinkedList<ASMMoveInst> workListMoves = new LinkedList<>();
    HashSet<ASMMoveInst> activeMoves = new HashSet<>();

    // other
    HashSet<Edge> adjSet = new HashSet<>();

    static final int K = PhysicalReg.assignableSet.size();

    private void work(ASMFn asmFn) {
        new LivenessAnalysis().runOnASMFn(asmFn);
        build(asmFn);
        makeWorkList();
        while (true) {
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
        // System.err.println("K = " + K);
        // for (var reg : coloredNodes) {
        // System.err.println(reg.format() + " " + reg.color.format());
        // }
        // for (var mvI : coalescedMoves)
        // System.err.println(mvI.format());
        if (!spilledNodes.isEmpty()) {
            rewriteProgram(spilledNodes, asmFn);
            work(asmFn);
        }
    }

    private void build(ASMFn asmFn) {
        for (var curBlock : asmFn.blockList) {
            // get the liveOut of the last instruction in this block
            // System.err.println("block id = " + curBlock.blockId);
            // System.err.println("useSet");
            // curBlock.useSet.forEach(reg -> System.err.println(reg.format()));
            // System.err.println("defSet");
            // curBlock.defSet.forEach(reg -> System.err.println(reg.format()));
            // System.err.println("liveIn");
            // curBlock.liveIn.forEach(reg -> System.err.println(reg.format()));
            // System.err.println("liveOut");
            // curBlock.liveOut.forEach(reg -> System.err.println(reg.format()));
            // System.err.println("\n--------------------------------------");
            var live = curBlock.liveOut;
            for (int i = curBlock.instList.size() - 1; i >= 0; --i) {
                var curInst = curBlock.instList.get(i);
                if (curInst instanceof ASMMoveInst mvInst) {
                    live.removeAll(mvInst.getUse());
                    HashSet<Register> moveReg = new HashSet<>();
                    moveReg.addAll(mvInst.getDef());
                    moveReg.addAll(mvInst.getUse());
                    moveReg.forEach(vReg -> vReg.moveList.add(mvInst));
                    workListMoves.add(mvInst);
                }
                live.addAll(curInst.getDef());
                // System.err.println(curInst.format());
                // live.forEach(reg -> System.err.print(reg.format() + ","));
                // System.err.println("\n--------------------------------------------");
                for (var d : curInst.getDef())
                    for (var l : live)
                        addEdge(l, d);

                live.removeAll(curInst.getDef());
                live.addAll(curInst.getUse());
            }
        }
    }

    private void addEdge(Register u, Register v) {
        if (!adjSet.contains(new Edge(u, v)) && u != v) {
            adjSet.add(new Edge(u, v));
            adjSet.add(new Edge(v, u));
            if (!preColored.contains(u)) {
                u.adjList.add(v);
                u.degree++;
            }
            if (!preColored.contains(v)) {
                v.adjList.add(u);
                v.degree++;
            }
        }
    }

    private void makeWorkList() {
        var it = initial.iterator();
        while (it.hasNext()) {
            Register v = it.next();
            it.remove();
            if (v.degree >= K)
                spillWorkList.add(v);
            else if (moveRelated(v))
                freezeWorkList.add(v);
            else
                simplifyWorkList.add(v);
        }
    }

    private HashSet<Register> adjacent(Register v) {
        var ret = new HashSet<Register>();
        ret.addAll(v.adjList);
        ret.removeAll(selectStack);
        ret.removeAll(coalescedNodes);
        return ret;
    }

    private HashSet<ASMMoveInst> NodeMoves(Register vReg) {
        var ret = new HashSet<ASMMoveInst>();
        ret.addAll(vReg.moveList);
        var tmp = new HashSet<ASMMoveInst>();
        tmp.addAll(activeMoves);
        tmp.addAll(workListMoves);
        ret.retainAll(tmp);
        return ret;
    }

    private boolean moveRelated(Register v) {
        return !NodeMoves(v).isEmpty();
    }

    private void simplify() {
        var v = simplifyWorkList.removeFirst();
        selectStack.push(v);
        for (var u : adjacent(v))
            decrementDegree(u);
    }

    private void decrementDegree(Register v) {
        int d = v.degree;
        v.degree--;
        if (d == K) {
            HashSet<Register> tmp = new HashSet<>();
            tmp.add(v);
            tmp.addAll(adjacent(v));
            enableMoves(tmp);
            spillWorkList.remove(v);
            if (moveRelated(v))
                freezeWorkList.add(v);
            else
                simplifyWorkList.add(v);
        }
    }

    private void enableMoves(HashSet<Register> nodes) {
        for (var u : nodes)
            for (var mvI : NodeMoves(u))
                if (activeMoves.contains(mvI)) {
                    activeMoves.remove(mvI);
                    workListMoves.add(mvI);
                }
    }

    private void coalesce() {
        var mvInst = workListMoves.getFirst();
        Register x = mvInst.rd;
        Register y = mvInst.rs;
        x = getAlias(x);
        y = getAlias(y);
        Register u, v;
        if (preColored.contains(y)) {
            u = y;
            v = x;
        } else {
            u = x;
            v = y;
        }
        workListMoves.remove(mvInst);
        if (u == v) {
            coalescedMoves.remove(mvInst);
            addWorkList(u);
        } else if (preColored.contains(v) || adjSet.contains(new Edge(u, v))) {
            constrainedMoves.add(mvInst);
            addWorkList(u);
            addWorkList(v);
        } else {
            boolean flag = false;
            for (var t : adjacent(v))
                if (OK(t, u))
                    flag = true;
            boolean cond1 = preColored.contains(u) && flag;
            HashSet<Register> tmp = new HashSet<>();
            tmp.addAll(adjacent(u));
            tmp.addAll(adjacent(v));
            boolean cond2 = !preColored.contains(u) && conservative(tmp);
            if (cond1 || cond2) {
                coalescedMoves.add(mvInst);
                combine(u, v);
                addWorkList(u);
            } else
                activeMoves.add(mvInst);
        }
    }

    private void addWorkList(Register u) {
        if (!preColored.contains(u) && !moveRelated(u) && u.degree < K) {
            freezeWorkList.remove(u);
            simplifyWorkList.add(u);
        }
    }

    private boolean OK(Register t, Register r) {
        return t.degree < K && preColored.contains(t) && adjSet.contains(new Edge(t, r));
    }

    private boolean conservative(HashSet<Register> nodes) {
        int k = 0;
        for (var v : nodes)
            if (v.degree >= K)
                k = k + 1;
        return k < K;
    }

    // union-set search
    private Register getAlias(Register v) {
        if (coalescedNodes.contains(v))
            return getAlias(v.alias);
        else
            return v;
    }

    private void combine(Register u, Register v) {
        if (freezeWorkList.contains(v))
            freezeWorkList.remove(v);
        else
            spillWorkList.remove(v);
        coalescedNodes.add(v);
        v.alias = u;
        u.moveList.addAll(v.moveList);
        HashSet<Register> nodes = new HashSet<>();
        nodes.add(v);
        enableMoves(nodes);
        for (var t : adjacent(v)) {
            addEdge(t, u);
            decrementDegree(t);
        }
        if (u.degree >= K && freezeWorkList.contains(u)) {
            freezeWorkList.remove(u);
            spillWorkList.add(u);
        }
    }

    private void freeze() {
        var u = freezeWorkList.removeFirst();
        simplifyWorkList.add(u);
        freezeMoves(u);
    }

    private void freezeMoves(Register u) {
        for (var mvInst : NodeMoves(u)) {
            Register x = mvInst.rd;
            Register y = mvInst.rs;
            Register v = getAlias(y) == getAlias(u) ? getAlias(x) : getAlias(y);
            activeMoves.remove(mvInst);
            frozenMoves.add(mvInst);
            if (NodeMoves(v).isEmpty() && v.degree < K) {
                freezeWorkList.remove(v);
                simplifyWorkList.add(v);
            }
        }
    }

    private void selectSpill() {
        double minIndex = Double.POSITIVE_INFINITY;
        Register minReg = null;
        for (var v : spillWorkList) {
            if (v.loopIndex / v.degree < minIndex) {
                minIndex = v.loopIndex / v.degree;
                minReg = v;
            }
        }
        spillWorkList.remove(minReg);
        simplifyWorkList.add(minReg);
        freezeMoves(minReg);
    }

    private void assignColors() {
        while (!selectStack.isEmpty()) {
            var u = selectStack.pop();
            HashSet<PhysicalReg> okColors = new HashSet<>();
            okColors.addAll(PhysicalReg.assignableSet);
            for (var v : u.adjList) {
                HashSet<Register> tmp = new HashSet<>();
                tmp.addAll(coloredNodes);
                tmp.addAll(preColored);
                if (tmp.contains(getAlias(v)))
                    okColors.remove(getAlias(v).color);
            }
            if (okColors.isEmpty())
                spilledNodes.add(u);
            else {
                coloredNodes.add(u);
                var c = okColors.iterator().next();
                u.color = c;
            }
        }
        for (var u : coalescedNodes)
            u.color = getAlias(u).color;
    }

    private void rewriteProgram(HashSet<Register> spilledNodes, ASMFn asmFn) {
        // System.err.println("spilled Reg : ");
        // for (var spillReg : spilledNodes) {
        // System.err.println(spillReg.format());
        // }
        // System.err.println("----------------------");

        for (var reg : spilledNodes)
            reg.spilledPos = new StackOffset(asmFn.spilledRegCnt++, stackDataKind.vReg);

        HashSet<Register> newTemps = new HashSet<>();

        for (var curBlock : asmFn.blockList) {
            ArrayList<ASMBaseInst> rawList = curBlock.instList;
            curBlock.instList = new ArrayList<>();
            for (var curInst : rawList) {
                var useSet = curInst.getUse();
                var defSet = curInst.getDef();
                for (var reg : useSet)
                    if (spilledNodes.contains(reg)) {
                        var newReg = new VirtualReg(asmFn);
                        newTemps.add(newReg);
                        curInst.replaceUse(reg, newReg);
                        new ASMLoadInst(reg.spilledPos, newReg, RV32.BitWidth.w, curBlock);
                    }
                // notice the curInst's def may be changed afterwards
                curBlock.addInst(curInst);
                for (var reg : defSet)
                    if (spilledNodes.contains(reg)) {
                        var newReg = new VirtualReg(asmFn);
                        newTemps.add(newReg);
                        // System.err.println(newReg.format());
                        curInst.replaceDef(reg, newReg);
                        new ASMStoreInst(reg.spilledPos, newReg, RV32.BitWidth.w, curBlock);
                    }
            }
        }
        spilledNodes.clear();
        initial = newTemps;
        initial.addAll(coloredNodes);
        initial.addAll(coalescedNodes);
        coloredNodes.clear();
        coalescedNodes.clear();
    }
}
