### IR optimization list
#### aggressive stack allocation
#### spare conditional constant propagation
#### constant folding
#### constant condition elimination
#### copy propagation
#### common subexpression elimination
#### aggressive dead code elimination

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