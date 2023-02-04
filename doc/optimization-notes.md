### 未解决

- phi node 的消除似乎有点假，之前多维数组的创建因为phi node所以出现了环。

###### calling convention
- caller save 和 callee save
  一个由caller保存（到栈上），一个由callee保存（到栈上），用法比较直观。
  理论上来说，所有的寄存器都可以当做caller save 或者 callee save。但是为了平衡内存的开销，caller 和 callee需要分工，这就是calling convention
- 寄存器分配时候的caller 和 callee的分工？
  - 考虑变量的生命周期，是否是临时变量
  - 是否跨越调用过程依然活跃，如果是，就是callee save
- sp，ra 等寄存器：callee-save，因为调用完之后还需要用，并且保持之前的值。
- 既然必定是调用过程活跃，那callee save似乎是一个最优解？

### 图染色寄存器分配

##### 变量的活跃性

- 控制流图：从某条语句到另外一条语句
- 活跃：在控制流图的某个边上活跃

$$
\begin{aligned}
\text{in}(n) &= \text{use}(n) \cup (\text{out}(n) - \text{def}(n))\\
\text{out}(n) &= \bigcup_{s \in \text{succ}(n)} \text{in}(s)
\end{aligned}
$$

- 从后继，以相反的方向来结算减少迭代次数。最后得到不动点。
- 合并基本块（basic block），减少点数。
- 计算方式
  - 用集合来计算
  - 每次只计算单个变量
- 为什么是最小不动点？

##### 冲突
- a 和 b 在程序的同一点均活跃的时候
- 类似calling convention的问题
- move 指令的特殊处理

添加冲突边的方法
- a 非 move，则该节点出口活跃的变量b1,b2,b3...都会和a冲突
- a 是 move，`move a c`，对于出口活跃的b1,b2,b3...，a 和里面不是c的变量都会冲突

##### 步骤
- build : 构建冲突图
- simplify : 假设有K个寄存器，则度数小于K的点可以直接删掉。这里将其压入栈中。
- spill : only significant degree ， 只有度数 $\geq K$ 的节点 。 选择某个变量，将其spill到内存中。
- select :

### SSA 形式

- 一开始memory（alloca）并不是SSA value
- 经过了mem2reg pass后，消除load和store，改为普通的def-use关系，大量使用phi node，得到标准的SSA形式
- 然后再到SSA形式的IR上做优化