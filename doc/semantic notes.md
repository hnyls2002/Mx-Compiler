### **Stupid Mistakes**

- Using `vscode starter` to learn how to compile java codes.
    - `src/` folder is default folder for java codes.
    - `classpath` for every java program need to be set.
    - bin folder is default folder for compiled java codes.
- The `.java` file in `.jar` is not visible. When vscode tries to link the source, it will show the wrong code.
  ```java
  public  T visit(org.antlr.v4.runtime.tree.ParseTree tree) {
    return null;
  }
  ```
  `return null` is not the true code!
### Visitor Pattern

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
- Abstract class `ASTNode` for `AST` base and derived classes.
- AST nodes design
  - `Expr` : all expressions
  - `Stmt` : all statements

### `ASTVisitor`
- It's a interface to traverse the `AST`, generate the `IR` and so on.

### `Semantic Checker`

- Traverse the `AST` and check the semantic. Require a class which is extended from `ASTVisitor` and implement the `visit` method for every type of node.
- `Scope` handling
- `Types` handling
- `Members` handling
- `Control flow` handling : `break` and `continue`.
- `Return` handling : `return`'s location and type.

**Scopes Handling**
- `GlobalScope` : collect the func def, global var def and class def(as well as var and func in class).
- `Scope` : Scope is a `tree` or `stack` structure, which stores the var local var defs during program execution.
  - `ClassDeclare` : when checking the body of **a function in specific class**, the checker should know the **local** `variables` and `functions` defined in this class. 
  - Further more, the vars and funcs can support `back-reference`.
  - To solve this, checker should add all the `vars` and `funcs` upon entering the declaration of a class.

**Types Handling**
- Semantic checker should check the type of every `expression` and `expression's match`.
  - All `expression` checks : using `TypeName`.
  - `TypeName` : only stores the literal `typeNameString` , `dimension` , `isLeftValue`.
- When calling a `inner function` or `inner field`, we should know what is contained in s specific `class`.
  - A `BaseType`.
  - Then we have `builtinType`, `ArrayType`(array types can have builtin functions), and `ClassType`.
  - The infos were collected during `SymbolCollector` in GlobalScope.
  - `FuncInfo` stores the **inner function's signature**.

**Members Handling**
- The implementation of `member` was not so good now.
- A global flag `isMember` to indicate whether the current `expression` is a `member` or not.
- A global reference `curMaster` to link to the spefic `class`.
- `identifier` or `function` can be called in `memberExpr`
  - `indentifier` : find the `var` in **only** `curMaster`.
  - `function` : find the `func` in **only** `curMaster`.
- Ugly code : `function call`'s arguments are not members, so quit `isMember` mode when visiting `arguments`.

**Control Flow Handling**
- A trick in semantic : `++inLoop` and `--inLoop` to indicate whether the current `statement` is in a `loop`.

**Return Handling**
- A good ideal.
- Similar to every `expression` has a `TypeName`, every `statement` has a `retStmtType`.
- It's a tree-shape collection of `return` statements and `return` type **in a single function**.
- Once out of the function, the `retStmtType` will be `null`.
- So we can easily collect all the `return` statements in a body and check the mathing relationship.

### Maybe can be improved
- `Scope` handling and `Types` handling have similar structure.
  - Just define a more universal `BaseScope` which contains `vars` and `funcs` and `types` and `classes` and so on.
- `Member` handling is not so good.
- `Loop` handling is not so good.