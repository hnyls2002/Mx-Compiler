- [x] constInt
- [x] value toString : (type to string + value to string)
- [x] Teminal Creator
    when a basic block ends
  - jump to another basic block
  - ret a value / void
    so I need a terminator to explictly create a terminate instruction for every block
 
- [ ] global variable
 
- [ ] handling basic block creation (and basic block linking ?) 
    basic block creation when :
  - enter a function
  - jump / branch
 
- [ ] a single block have multiple return statement ??? deadcode 
 
- [x] block has a type 
  - lable type!
  - llvm::type is not a abstract class
 
- [ ] instruction's type
  - store to void-type!!!

- [x] pointer type : when (i32*)*, implement to i32**
    that is the atomic type is not a pointer type.

- [ ] global variable initial issue
    - [ ] if constData, just present it
    - [ ] if not, explicitly assign a value to a global variable

- [x] printer type to string
- [x] default value for global variable
    - [x] int
    - [x] pointer
- [x] bool type is i8
- [x] return an constant value
- [x] return void
- [x] literal string transfer

- [ ] string handling
  - [x] string constant : an array, should be defined as a global variable
  - [ ] string s = "hello world", s is a i8* pointer 
        using getelementptr to get the address of the first char
  - [ ] same literal string should only be defined once

- [ ] getelementptr instruction

- [ ] add the new var into scope

- [ ] def-use chain

- store instruction
- add an init block in organizer
- use store instruction to store it
- load the memory first
- check the type

对于内存、函数调用，寄存器的中间表示
将globalvar设置成指针是否合理？

对于字符串的gep初始化问题，直接将一个gep赋值给变量，可以先弄一个函数，最后再优化掉？

只有字面int值不用载入寄存器？

struct 类型
- struct transfer

~~函数的调用先实现了再说~~
~~有个大问题，结构体里面的函数不加this使用自己的变量/函数~~
~~我直接把这个变量和函数加入到当前的curScope里面了~~
~~如何解决这个问题？~~

~~手动记录inClass, inWhichClass，每次找函数/变量，先在which里面找，再在curScope里面找~~

~~isMember, whoseMember 同理~~

~~对于一个类中的函数，我需要知道某个变量是否隐式地调用了this指针，但是classDef的scope可能还在上面几层，所以只能像semantic那样把classDef中的所有变量放到curScope里面。~~
~~不过这个时候这个变量的irValue就是一个index，没有啥问题。~~

~~操，所有的类和函数需要预处理，，，~~
~~所有的信息都有，在IRBuilder一开始预处理？~~

~~先实现struct 变量的定义，然后再实现成员的访问~~
~~成员访问的时候，identifier做什么类型，干些什么事情？~~
~~可以做为intConst类，也可以在member节点直接查找类的信息~~

~~垃圾llvm~~ ~~parameter 和 argument 分不清是吧~~
~~所以llvm::argument 其实是 formal argument, which is actually parameter~~

bitcast 还有问题，裂开

~~函数的参数 需要存下来~~
~~函数加scope~~
~~函数的参数传进来的时候也是有名字的，，，---> llvm::Argument~~
 
~~局部变量 alloca~~

左值和右值的处理。

左值有哪些 ：
- scope可以找的变量 identifier
- new 出来的一堆东西
- 取成员
- 数组取下标操作
- ++a

虚拟寄存器和堆栈的处理完全不会了，，，codegen再来吧

- 基本类型 : int, bool 等
  直接存储在栈上，找的时候直接找到在栈上的地址，**对值进行操作**

- 引用类型 
  存储了某块内存的地址，对地址进行操作。

对int赋值，store i32 to i32*
对struct.A 赋值 store struct.A* to struct.A**

### 双目单目运算符 裂开

##### 单目运算符

- `++`,`--` int独占
- 注意`++a`的左值问题
- `~` `-` 也是int

- `!` bool 独占
  - logical not 用xor来完成，调用logic not的时候i8转i1

#####  store的时候 再转化i8 !!!

##### ++ 和 -- 的处理

不仅需要获取值，还需要获取地址
只能再astnode上再加东西了

##### 双目运算符

- 整数 i32
  - 几乎所有的运算符，`&&`,`||`除外
- 布尔 i1 i8 且需要短路求值
  - bool 类型仅可做 `==` 和 `!=` 运算。
  - `&&` `||` 是短路求值的
- 字符串 i8*
  - `+` : `strcat`
  - `==` `!=` : `strcmp`
  - `<` `<=` `>` `>=` : `strcmp`
- 数组对象 只可以和 `null` 比较
  - `==` `!=`
- 类对象 ： 直接比较内存地址
  - `==` `!=`

##### bool 类型

bool 的处理。
申请内存，i8。
icmp的返回值 i1。

logic 操作在运算的时候，一律转化成i1
store的时候，i1 -> i8

草，还有短路求值

llvm 你怎么这么逆天啊

对于逻辑表达式的短路求值
### 先把值算出来

### 函数开空间存储返回值

函数的block：
不但有空间存储返回值，还有一个专门的return block
一旦遇到return，先把return的值存下来，然后跳转到return block

跳转到return block后，即使是跳转到afterif也没有用，不会覆写。
return后面接了return，也不会覆写。

一旦一个block的terminal存在了，就不能被覆写了，所以terminal 必须最后赋值。

一个block被terminated了，但是还会新建其他的block，这些是dead block，不会被执行到。