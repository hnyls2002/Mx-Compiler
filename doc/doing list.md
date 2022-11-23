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
