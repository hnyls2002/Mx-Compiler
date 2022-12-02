cd debug
llc test.ll -march=riscv32 -mattr=+m -O0 -o test_std.s
cd ..