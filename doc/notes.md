### **Stupid Mistakes**

- Using `vscode starter` to learn how to compile java codes.
    - src folder is default folder for java codes.
    - classpath for every java program need to be set.
    - bin folder is default folder for compiled java codes.
- The `.java` file in `.jar` is not visible. When vscode tries to link the source, it will show the wrong code.
  ```java
  public  T visit(org.antlr.v4.runtime.tree.ParseTree tree) {
    return null;
  }
  ```
  `return null` is not the true code!
### Vistor Pattern

- visitor 
  - A `Visitor` interface and some `SomeVisitor` classes.
  - A method `visit` is defined in the `Visitor` interface.
  - For every single `SomeVisitor`, it represents an action( like traversing a Parser Tree). You can define the data structure's behavior **under this action** by define visit function for every type of the node.
- Base and Derived classes
  - An abstract base class `BaseNode` and some derived `SomeNode` classes.
  - A method `accept` is defined in the `BaseNode` class.
  - `accept` method takes a `Visitor` as parameter and call the `visit` method of the `Visitor` with `this` as parameter.
- `Parser Tree` Visitor Pattern
  - You define methods `visitA()`,`visitB()`... in the `BaseVisitor` class.
  - Call the `visit()` method : 
  
    ```java
    public T visit(ParseTree tree) {
        return tree.accept(this);
    }
    ```
  - Every `SomeContext`'s `accept()` writes like this:
  
    ```java
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseVisitor ) return ((BaseVisitor<? extends T>)visitor).visitA(this);
        else return visitor.visitChildren(this);
    }
    ```
    So when you call `visit()` method, an instance of `SomeContext` will call the `visitA()` method.


### `ASTBuilder`

- using visitor pattern.
  - Extended from `BaseVisitor`, which has `visitChildren` method.
  - A vistor class to traverse the `Parser Tree` and build the `AST`.
- **Traverse the `Parser Tree`**.
  - `Context` represents a node in the `Parser Tree`.
  - `TerminalNode` represents a leaf in the `Parser Tree` .
  - Every alternative in a lexer rule should be labeled.
    ```g4
    expression
    : // ...
    | foo # namedMethodInvocation
    ;
    ```
  - `op = ('*' | '/' | '%')` is used to get the operator. Similarly, for any obscure lexer rule, **add an identifier to get the children context**.
  - Just use `ctx.<sublabel>()` to get its children context, `getText()` can get its literal string in input.
  - Some fields of the context I don't know...
- **Build the `AST`**.

### **Build The `AST`**
- Interface `ASTVisitor` for `AST` visitor pattern.
- Abstruce class `ASTNode` for `AST` base and derived classes.
- AST nodes design
  - `Expr` : all expressions
  - `Stmt` : all statements

### `ASTVisitor`
- It's a interface to traverse the `AST`, generate the `IR` and so on.

### `Semantic Checker`

- Traverse the `AST` and check the semantic. Require a class which is extended from `ASTVisitor` and implement the `visit` method for every type of node.

- **Types and Scopes Handling**
  - Recording the `typename` , `varname` , `methodname` and dimentions of multi-dimentional array. Noticing that one class cannot have an inner class, and can only be defined globally.  
  - `BaseType` class for recording the above information.