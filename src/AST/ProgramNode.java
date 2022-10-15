package AST;

import java.util.ArrayList;

import AST.Stmt.ClassDeclStmtNode;
import AST.Stmt.FuncDeclrStmtNode;
import AST.Stmt.VarDeclStmtNode;
import Util.Position;

public class ProgramNode extends ASTNode {

    public ArrayList<FuncDeclrStmtNode> funcList = new ArrayList<>();
    public ArrayList<ClassDeclStmtNode> classList = new ArrayList<>();
    public ArrayList<VarDeclStmtNode> globalVarList = new ArrayList<>();

    public ProgramNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
