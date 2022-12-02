package AST.Stmt;

import java.util.ArrayList;

import AST.Util.Position;
import AST.Util.TypeName;
import Share.Visitors.ASTVisitor;

public class VarDeclStmtNode extends StmtNode {

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
