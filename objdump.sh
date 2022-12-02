cd debug
riscv32-unknown-elf-as test.s -o test.o
riscv32-unknown-elf-objdump -d -r -S test.o > test.objdump