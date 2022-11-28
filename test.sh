cd testcases
llvm-link test.ll builtin.ll -o linked.bc
lli linked.bc