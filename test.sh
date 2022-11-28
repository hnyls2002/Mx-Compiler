cd testcases
llvm-link test.ll builtin.ll -o linked.bc
clang linked.bc -o test
./test