package AST.Stmt;

import java.util.ArrayList;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.Util.TypeNode;
import AST.Util.VarSingleDecNode;
import Util.Position;

public class VarDeclStmtNode extends ASTNode {

    public TypeNode typeName;
    public ArrayList<VarSingleDecNode> varList = new ArrayList<>();

    public VarDeclStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
