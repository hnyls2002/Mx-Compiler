cd debug
ravel --input-file=test.in --output-file=test.out test.s builtin.s > ravel.log 2> ravel-error.log
cat ravel-error.log
echo "-------------------------"
cat ravel.log
echo "-------------------------"
cat test.out