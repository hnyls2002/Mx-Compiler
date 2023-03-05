##### mem2reg

- fake ssa (alloca/load/store)
- 通过支配树（在第一个节点默认有def）的迭代支配边界来确定一个alloca 的join node（经过多个def的use点）
- 在joint node处添加phi，phi的oprand暂时为空。
- 重命名：按照支配树（dfs）的顺序（因为要确定原来的alloca的load是从哪里store过来的，从phi or 从别的store），遍历每一个块。
  - 遍历块的时候确定每个alloca在这个块的末尾的def。
  - 确定了def之后给这个块的succ的phi添加oprand。（也可以最后再统一添加）

##### SSA realated optimization

##### Phi elimination
- 没有phi之前，所有的指令都是SSA并且仅仅从一个def到一个use，没有不确定的def。
- 而use之前必定有def，所以可以推出没有phi的def-use图是没有环的。
- 有了phi之后出现了环，一般在实现代码的时候，user-value 这样的关系也会要求 value 先被def，所以可能需要预加载phi，之后再完善phi的定义。(之前的暴力做法是这样的)

##### calling convention
- 一个由caller保存（到栈上），一个由callee保存（到栈上），用法比较直观。
- 理论上来说，所有的寄存器都可以当做caller save 或者 callee save。
- 为了平衡内存的开销，caller 和 callee需要分工，这就是calling convention
- 寄存器分配时候的caller 和 callee的分工
  - 考虑变量的生命周期，是否是临时变量
  - 是否跨越调用过程依然活跃，如果是，就是callee save

##### 图染色过程的calling convention
- call 指令会用到argument register ($a_x$)， 这是use
- 跨越call依然活跃的变量，希望全部是callee save，所以call指令会def所有的caller save（和跨越的变量冲突）
- 每个函数的开头存下callee save register，函数结束的时候恢复，用 move 的方式来copy到一个新的虚拟寄存器，不用做栈分配。
**TO THINK :**
  - 多用move到虚拟寄存器的思想，反正后面看情况溢出到栈上。
  - 虚拟栈空间分配的思想(现在用的stackOffset)和寄存器结构改变(统一的结构，rd，rs1，rs2)

##### 变量的活跃性

$$
\begin{aligned}
\text{in}(n) &= \text{use}(n) \cup (\text{out}(n) - \text{def}(n))\\
\text{out}(n) &= \bigcup_{s \in \text{succ}(n)} \text{in}(s)
\end{aligned}
$$

按照虎书图染色就好。