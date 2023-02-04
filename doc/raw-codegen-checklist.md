- [ ] caller and callee saved registers
- [x] `.s`代码中的block，label，常量字段，全局字段等等
- [x] `.s`代码中的`.text`,`.align`等指示

- [x] 栈指针和帧指针 `sp` and `fp`，作用，是否需要设立  ？
- [x] 参数不够用的时候，怎么办？
- [x] 未加优化的代码，到底是靠寄存器传参还是靠栈传参？
    目前看来好像都是靠寄存器传参，那x86架构里面的把参数全部压倒栈里面是怎么回事捏

    a0-a7 寄存器是caller saved 寄存器，caller需要值在call完之后不变。
    s0-s7 寄存器是callee saved 寄存器，这个值应该还会被caller用到，但是caller没有保存，所以callee需要保存再恢复。

LLVM IR 中的结构体是有字节对齐的，并且默认四字节对齐, i8, i8, i8, i32 的占用应该是，中间会空出来一个，如果直接size 设置成7的话，最后一个就会溢出。
    |x|x|x| |xxxx|

- [ ] `%1 = alloca` what is `%1`?
`%1` is an address, which can be described as `offset(sp)`
- [ ] 如何实现 phi node?

首先全部都是虚拟寄存器，暴力寄存器分配的时候把虚拟寄存器改成：先从imm(sp)里面取出来一个数放在物理寄存器里面。imm先记录相对值，在curFn里面记录目前添加了多少个“栈寄存器”。
同理还可以有几个“栈变量”。

### opt

- [ ]  一个变量和一个数相加，数在立即数的范围内的时候可以直接当做立即数而不是放在临时寄存器里面
- [ ]  字面量合并 1 + a + 2 = a + 3
- [ ]  br 的判断条件如果是某个icmp的结果，那么可以直接用 beq, bne, bge, ble, blt 等
- [ ] 感觉还可以用beqz 这种直接和zero比较的指令?
- [ ] li 指令 ， load 一个立即数，较小的时候可以直接用lui/addi
- [ ] la 指令 ， load 一个地址，较小的时候可以直接用lui/addi

### debug

phi node 在创建高维数组的时候会有问题捏，前面调用后面，后面调用前面，只能用某种类似于特判的preload 去解决。

mem2reg的时候新出来的phi node 会不会也有这样的问题？

可以在codegen的时候系统性地解决变量的前向引用问题。