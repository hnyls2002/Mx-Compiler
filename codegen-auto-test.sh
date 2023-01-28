cd src
java Compiler

cd autotestspace
ravel --input-file=input.txt --output-file=output.txt test.s builtin.s > ravel.log 2> ravel-error.log