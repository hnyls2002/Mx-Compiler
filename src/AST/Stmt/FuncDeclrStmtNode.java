package AST.Stmt;

import java.util.ArrayList;

import AST.ASTNode;
import AST.ASTVisitor;
import Frontend.Util.Position;
import Frontend.Util.TypeIdPair;
import Frontend.Util.TypeName;

public class FuncDeclrStmtNode extends ASTNode {

    public String funcNameString;

    public TypeName retType;

    public ArrayList<TypeIdPair> paraList = new ArrayList<>();

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
