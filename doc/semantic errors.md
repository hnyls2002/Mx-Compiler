### `Main` function
- [x] have and only have one `main` function with return type `int`.
- [ ] no parameters.
- [ ] `return` statement can be ignored.
- [ ] if has `return`, can only return `int`.
- [ ] `main` can't be used as class name or other functions' name.

### `Control Flow`
- [ ] `break` and `continue` can only be used in `for` and `while` loop.
- [ ] `if`'s `condition` can only be bool.
- [ ] `for-loop` condition can only be bool.

### `Function Declaration`
- [ ] `return type` match `declaration`.
- [ ] `non-void` function must have `return` statement.
- [ ] `this` usage : regarded as a `type`.

### `Lambda` function
- [ ] `parameters` match `arguments`.
- [ ] should have a `return` statement.
- [ ] definition : right associative.
- [ ] check the scope's var.

### `Class Declaration`
- [x] name can't be `builtin` types.
- [x] `constructor` : no `return` statement, no `parameters`.
- [x] `constructor` : same name.
- [ ] other `function` name can't be the same as `constructor`.

### Definition Errors
- [ ] no `void` type for vars.
- [ ] `var` can't support back reference.
- [ ] `class`, `function` support back reference.
- [ ] `undefined` being used.
- [ ] `Duplicate` for `vars`,`function`,`class`, which means the three can't have the same name.

### Var declaration
- [ ] `variable` can't be `void`. 
- [ ] `creator` can't be `void`.

### Type Handling
- [ ] every expression has a type.
- [ ] any `conversion` is undefined behavior.
- [ ] `assignment` match.
- [ ] `field`,`arugments`,`method` match `definition`.

### dimension Handling
- [ ] `creator` has a type with dimension.
- [ ] `creator` format (from left to right).
- [ ] `dimension set`.
- [ ] `subscript` expression.

### `Operator` expression
- [ ] `ADD`,`SUB`,`...` can only be used on some specific types.
- [ ] `logic` operator can only be used on `bool`.


### Left Value
- [ ] `literal value`,`function call` can't be left value.
- [ ] `this` can't be assigned.
- [ ] `a++` return a `right value`.
- [ ] `a++`,`++a` : `a` need to be `left value`.
- [ ] function call can't be `left value`.

### Others
- [ ] `subscript` can only be `int`.
- [ ] `null` can only be assigned to reference type.