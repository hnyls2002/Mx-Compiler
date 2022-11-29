cd src
javac Compiler.java
java Compiler
rm *.class

cd irtestspace
llvm-link test.ll builtin.ll -o linked.bc
clang linked.bc -o test
./test