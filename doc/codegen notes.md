### [RISC-V Assembly Programmer's Manual](https://github.com/riscv-non-isa/riscv-asm-manual/blob/master/riscv-asm.md#user-content-fn-3-90707126b2de7d6b13c295ec772ff888)
### Calling conventions

- `ra` : return address
- `sp` : stack pointer
- `fp` : frame pointer
- `a0` : return value or first argument
- `a1 - a7` : arguments

##### How does the `call`/`ret` works and the `ra` register

`call` just uses `auipc` and `jalr`.

```
  78:	00000097          	auipc	ra,0x0
  7c:	000080e7          	jalr	ra # 78 <main+0x18>
```

Why the `auipc` offset is always `0x0` ?

[See this stack-overflow](https://stackoverflow.com/questions/43956612/understanding-the-auipcjalr-sequence-used-for-function-calls)

Add `-r` to `objdump` to see the relocation info.

```objdump
  78:	00000097          	auipc	ra,0x0
			78: R_RISCV_CALL	fn
			78: R_RISCV_RELAX	*ABS*
  7c:	000080e7          	jalr	ra # 78 <main+0x18>
```

##### `auipc`,`jal` and `jalr`

- `auipc` : add upper immediate to pc
- `jal` : jump and link
- `jalr` : jump and link register

What's the difference between `jal` and `jalr` ?
They all save the `pc + 4` in a register, and jump to a new address.
- `jal` : `pc + offset`
- `jalr` : `x[rs1] + offset`

That is because **the offset is not large enough**.

### Caller saved and callee saved registers

### Call frame infromation
