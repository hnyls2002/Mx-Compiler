- [ ] `%1 = alloca` 这个虚拟寄存器值是可以在编译的时候知道的。
- [ ] `%3 = add i32 %1, %2`，需要设置虚拟寄存器，找到位置，然后load出来。
- [ ] 如何实现 phi node?
- [ ] caller and callee saved registers

ra 寄存器caller需要先存下来。在调用函数的时候callee会把自己的return address也存在ra里面，所以ra会在函数调用的时候被更改。这就是caller save。

如果一个寄存器被标识为”Caller Save”， 那么在进行子函数调用前，就需要由调用者提前保存好这些寄存器的值，保存方法通常是把寄存器的值压入堆栈中，调用者保存完成后，在被调用者（子函数）中就可以随意覆盖这些寄存器的值了。如果一个寄存被标识为“Callee Save”，那么在函数调用时，调用者就不必保存这些寄存器的值而直接进行子函数调用，进入子函数后，子函数在覆盖这些寄存器之前，需要先保存这些寄存器的值，即这些寄存器的值是由被调用者来保存和恢复的。

- [ ] `.s`代码中的block，label，常量字段，全局字段等等
- [ ] `.s`代码中的`.text`,`.align`等指示

- [ ] 栈指针和帧指针 `sp` and `fp`，作用，是否需要设立  ？
- [ ] 参数不够用的时候，怎么办？
- [ ] 未加优化的代码，到底是靠寄存器传参还是靠栈传参？
    目前看来好像都是靠寄存器传参，那x86架构里面的把参数全部压倒栈里面是怎么回事捏

    a0-a7 寄存器是caller saved 寄存器，caller需要值在call完之后不变。
    s0-s7 寄存器是callee saved 寄存器，这个值应该还会被caller用到，但是caller没有保存，所以callee需要保存再恢复。