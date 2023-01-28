cp lib/builtin.ll debug
cd debug
llvm-link test.ll builtin.ll -o linked.bc
clang linked.bc -o test
./test

# cleanup
rm linked.bc test
rm builtin.ll
cd ..