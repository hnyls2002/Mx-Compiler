/*
 * 参考《antlr4权威指南》 一个数组初始化语法的例子
 */
grammar ArrayInit;

init: '{' value (',' value)* '}';

value: init | INT;

INT: [0-9]+;
WS: [ \t\r\n]+ -> skip;