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
 
- [ ] block has a type 
  - lable type!
  - llvm::type is not a abstract class
 
- [ ] instruction's type
  - store to void-type!!!