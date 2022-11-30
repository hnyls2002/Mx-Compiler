cd debug
llc test.ll -march=riscv32 -mattr=+m
cd ..