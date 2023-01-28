cd debug
opt -dot-cfg test.ll
dot .main.dot -Tpng -o test.png
eog test.png
rm .main.dot test.png # cleanup