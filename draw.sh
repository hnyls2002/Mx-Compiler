cd testcases
opt -dot-cfg test.ll
dot .main.dot -Tpng -o test.png
eog test.png