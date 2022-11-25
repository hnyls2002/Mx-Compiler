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

函数的调用先实现了再说
有个大问题，结构体里面的函数不加this使用自己的变量/函数
我直接把这个变量和函数加入到当前的curScope里面了
如何解决这个问题？

手动记录inClass, inWhichClass，每次找函数/变量，先在which里面找，再在curScope里面找

isMember, whoseMember 同理

对于一个类中的函数，我需要知道某个变量是否隐式地调用了this指针，但是classDef的scope可能还在上面几层，所以只能像semantic那样把classDef中的所有变量放到curScope里面。
不过这个时候这个变量的irValue就是一个index，没有啥问题。

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
