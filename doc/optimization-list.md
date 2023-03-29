### IR optimization list
#### function inlining

#### copy propagation

After mem2reg, there is no copy in the IR. Mem2reg pass actually does copy propagation.

#### alias analysis

- local variable : no alias(mem2reg pass has eliminated all the copy)
- global variable : may alias
  - directly load from global variable

#### loop analysis

formal definition
- single entry point : header
- back edge : tail -> head &&  head dominates tail
- **in reducible form : back edges = retreating edges**
  - back edge : in loop's definition
  - retreating edge : in dfs tree, from descendant to ancestor

how to find loops
- build the dominator tree
- identify the back edges
- find the natural loops associated with the back edges

#### loop invariant code motion

In SSA form, how to determine if an instruction is loop invariant?

- if an instruction is not in the loop, then it's loop invariant
- all of its operands are loop invariant and cause no side effect
  - call will use (load or store) an address, as the value in the address may be changed
  - alloca
  - store
  - ret
- unchanged in the loop : **require alias analysis**
  - phi : not considered as loop invariant
  - load from global variable may change
    address is loop invariant, and the address will not been stored in the loop

#### spare conditional constant propagation

Lattice value (only upwards): undefined -> constant -> unknown

Worklist algorithm
- Block executable worklist
- Variable status worklist

constant folding can be done in this phase

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
#### aggressive stack allocation

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