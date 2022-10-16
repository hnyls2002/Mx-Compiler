package AST.Stmt;

import java.util.ArrayList;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.Util.ParaNode;
import AST.Util.TypeNode;
import Util.Position;

public class FuncDeclrStmtNode extends ASTNode {

    public String funcNameString;

    public TypeNode retType;

    public ArrayList<ParaNode> paraList = new ArrayList<>();

    public SuiteStmtNode body;

    public FuncDeclrStmtNode(Position pos, String funNameString) {
        super(pos);
        this.funcNameString = funNameString;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
