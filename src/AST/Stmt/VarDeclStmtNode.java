package AST.Stmt;

import java.util.ArrayList;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;
import Util.TypeName;

public class VarDeclStmtNode extends ASTNode {

    public TypeName typeName;
    public ArrayList<SingleVarDeclStmtNode> varList = new ArrayList<>();

    public VarDeclStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
