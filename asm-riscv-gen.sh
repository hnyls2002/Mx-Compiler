cd debug
llc test.ll -march=riscv32 -mattr=+m -O0
cd ..