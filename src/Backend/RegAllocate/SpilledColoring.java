package Backend.RegAllocate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.antlr.v4.runtime.misc.Pair;

import ASM.ASMFn;
import ASM.ASMInst.ASMMoveInst;
import ASM.ASMOprand.Register;
import ASM.ASMOprand.VirtualOffset;
import Backend.RegAllocate.RegisterColoring.NodeInfo;
import Share.Lang.RV32.SPLabel;

public class SpilledColoring {
    /*
     * For the spilled nodes, we can do the coalescing moves and coloring again.
     * 
     * 1) build the interference graph for the spilled nodes
     * 2) do the coalescing moves
     * 3) simplify : select by degree
     * 4) coloring spilled nodes
     * 
     */

    HashSet<Register> spilledNodes = new HashSet<>();
    HashMap<Register, NodeInfo> nodeInfoMap = new HashMap<>();
    HashSet<ASMMoveInst> waitingMoves = new HashSet<>();
    HashSet<Pair<Register, Register>> adjSet = new HashSet<>();
    ArrayList<Register> selectStack = new ArrayList<>();
    int colorUpperBound = 0;

    public void run(RegisterColoring RCInfo, ASMFn fn) {
        spilledNodes.addAll(RCInfo.spilledNodes);

        for (var reg : RCInfo.nodeInfoMap.keySet()) {
            if (spilledNodes.contains(reg)) {
                nodeInfoMap.put(reg, RCInfo.nodeInfoMap.get(reg));
                rebuildInfo(reg);
            }
        }

        fn.blockList.forEach(block -> {
            block.instList.forEach(inst -> {
                if (inst instanceof ASMMoveInst && spilledNodes.contains(inst.rd) && spilledNodes.contains(inst.rs1))
                    waitingMoves.add((ASMMoveInst) inst);
            });
        });

        while (!waitingMoves.isEmpty())
            coalesce();

        for (var reg : spilledNodes)
            if (getAlias(reg) == reg)
                selectStack.add(reg);

        // sort by degree from large to small
        selectStack.sort((a, b) -> nodeInfoMap.get(b).degree - nodeInfoMap.get(a).degree);

        for (var reg : selectStack) {
            var info = nodeInfoMap.get(reg);
            info.spillPlace = new VirtualOffset(SPLabel.spilledReg, findColor(reg, fn));
        }

        fn.spilledRegCnt += colorUpperBound;

        for (var reg : spilledNodes) {
            var alias = getAlias(reg);
            if (alias == reg)
                continue;
            nodeInfoMap.get(reg).spillPlace = new VirtualOffset(SPLabel.spilledReg,
                    nodeInfoMap.get(alias).spillPlace.rank);
        }
    }

    private int findColor(Register v, ASMFn asmFn) {
        HashSet<Integer> colorSet = new HashSet<>();
        for (var adj : nodeInfoMap.get(v).adjList) {
            var info = nodeInfoMap.get(adj);
            if (info.spillPlace != null)
                colorSet.add(info.spillPlace.rank);
        }
        int color = 0;
        while (colorSet.contains(color))
            ++color;
        colorUpperBound = Math.max(colorUpperBound, color + 1);
        return color + asmFn.spilledRegCnt;
    }

    private void rebuildInfo(Register v) {
        var info = nodeInfoMap.get(v);
        var it = info.adjList.iterator();
        while (it.hasNext()) {
            var adj = it.next();
            if (!spilledNodes.contains(adj))
                it.remove();
            else
                adjSet.add(new Pair<>(v, adj));
        }
        info.degree = info.adjList.size();
        info.alias = v;
        info.color = null;
        info.spillPlace = null;
    }

    private Register getAlias(Register n) {
        var alias = nodeInfoMap.get(n).alias;
        if (alias == n)
            return n;
        else
            alias = getAlias(alias);
        nodeInfoMap.get(n).alias = alias;
        return alias;
    }

    private void addEdge(Register u, Register v) {
        if (u == v || adjSet.contains(new Pair<>(u, v)))
            return;
        adjSet.add(new Pair<>(u, v));
        adjSet.add(new Pair<>(v, u));
        nodeInfoMap.get(u).adjList.add(v);
        nodeInfoMap.get(u).degree++;
        nodeInfoMap.get(v).adjList.add(u);
        nodeInfoMap.get(v).degree++;
    }

    private void removeEdge(Register u, Register v) {
        if (u == v || !adjSet.contains(new Pair<>(u, v)))
            return;
        adjSet.remove(new Pair<>(u, v));
        adjSet.remove(new Pair<>(v, u));
        nodeInfoMap.get(u).adjList.remove(v);
        nodeInfoMap.get(u).degree--;
        nodeInfoMap.get(v).adjList.remove(u);
        nodeInfoMap.get(v).degree--;
    }

    private void coalesce() {
        var it = waitingMoves.iterator();
        if (!it.hasNext())
            return;
        var m = it.next();
        it.remove();
        var u = getAlias(m.rd);
        var v = getAlias(m.rs1);
        if (u == v || adjSet.contains(new Pair<>(u, v)))
            return;
        combine(u, v);
    }

    private void combine(Register u, Register v) {
        nodeInfoMap.get(v).alias = u;
        ArrayList<Pair<Register, Register>> edgeList = new ArrayList<>();
        for (var t : nodeInfoMap.get(v).adjList)
            edgeList.add(new Pair<>(v, t));
        edgeList.forEach(e -> removeEdge(e.a, e.b));
        edgeList.forEach(e -> addEdge(u, e.b));
    }
}
