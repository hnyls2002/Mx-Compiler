package AST.Stmt;

import java.util.ArrayList;

import AST.ASTVisitor;
import Frontend.Util.Position;
import Frontend.Util.TypeName;

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
