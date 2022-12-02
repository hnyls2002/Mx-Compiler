package AST.Stmt;

import java.util.ArrayList;

import AST.ASTNode;
import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class ClassDeclStmtNode extends ASTNode {

    public String classNameString;

    public ArrayList<VarDeclStmtNode> varDeclList = new ArrayList<>();

    public ArrayList<FuncDeclrStmtNode> funcDeclList = new ArrayList<>();

    public SelfConstructNode constructor = null;

    public ClassDeclStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
