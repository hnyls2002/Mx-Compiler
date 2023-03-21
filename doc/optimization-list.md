### IR optimization list
#### aggressive stack allocation
#### spare conditional constant propagation

Lattice value (only upwards): BOTTOM -> CONST -> TOP

Worklist algorithm
- Block executable worklist
- Variable status worklist

#### constant folding
#### constant condition elimination
#### copy propagation
#### common subexpression elimination
#### Aggressive Dead Code Elimination

Initially, all instructions are marked as dead.

- input, ouput, sotre, call (modified global variable), return are marked as live
- an instruction that defines a live variable
- a branch instruction that controls a alive block

Attention : the algorithm will **remove a infinite loop** if the loop is dead

How to build the control dependence graph?

- reverse the CFG
- build the post dominator tree
- if v is u's post domination frontier, then v -> u is a control dependence edge
- **special case** : if u dominates v and v is connected to u in the reverse CFG, then u -> u is a control dependence edge
  (oh!, because it's not strict domination relationship)

### ASM optimization list

#### coalesce moves
if rd and rs are the same, then the move is redundant
#### const li la simplification
a immediate value (const int) can be directly used in the instruction
```
addi a0 a0 1
```

an address (const pointer) can be directly used in the instruction
```
lui a0 %hi(sum)
lw a0 %lo(sum)(a0)
```

**No constant propagation and folding in this period**