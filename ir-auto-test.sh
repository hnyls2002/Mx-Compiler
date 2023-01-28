cd src
java Compiler

cd autotestspace
llvm-link test.ll builtin.ll -o linked.bc
clang linked.bc -o test
./test