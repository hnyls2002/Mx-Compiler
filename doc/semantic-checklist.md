### semantic checker checklist

##### main function

- [x] have and only have one `main` function with return type `int`.
- [x] no parameters.
- [x] `return` statement can be ignored.
- [x] if has `return`, can only return `int`.
- [x] `main` can't be used as class name or other functions' name.

##### Control Flow

- [x] `break` and `continue` can only be used in `for` and `while` loop.
- [x] `if`'s `condition` can only be bool.
- [x] `for-loop` condition can only be bool.

##### Function Declaration

- [x] `return type` match `declaration`.
- [x] `non-void` function must have `return` statement.
- [x] `this` usage : regarded as a `type`.

##### lambda function

- [x] `parameters` match `arguments`.
- [x] should have a `return` statement.
- [x] definition : right associative.
- [x] check the scope's var.

##### class declaration

- [x] name can't be `builtin` types.
- [x] `constructor` : no `return` statement, no `parameters`.
- [x] `constructor` : same name.
- [x] other `function` name can't be the same as `constructor`.

##### definition errors

- [x] no `void` type for vars.
- [x] `var` can't support back reference.
- [x] `class`, `function` support back reference.
- [x] `undefined` being used.
- [x] `Duplicate` for `vars`,`function`,`class`, which means the three can't have the same name.

##### Var declaration

- [x] `variable` can't be `void`. 
- [x] `creator` can't be `void`.

##### Type Handling

- [x] every expression has a type.
- [x] any `conversion` is undefined behavior.
- [x] `assignment` match.
- [x] `field`,`arugments`,`method` match `definition`.

##### dimension Handling

- [x] `creator` has a type with dimension.
- [x] `creator` format (from left to right).
- [ ] ~~`dimension set`.~~
- [x] `subscript` expression.

##### Operator expression

- [x] `ADD`,`SUB`,`...` can only be used on some specific types.
- [x] `logic` operator can only be used on `bool`.


##### Left Value

- [x] `literal value`,`function call` can't be left value.
- [x] `this` can't be assigned.
- [x] `a++` return a `right value`.
- [x] `a++`,`++a` : `a` need to be `left value`.
- [x] function call can't be `left value`.

##### Others

- [x] `subscript` can only be `int`.
- [x] `null` can only be assigned to reference type.