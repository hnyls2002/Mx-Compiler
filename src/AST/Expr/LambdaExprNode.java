package AST.Expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Stmt.SuiteStmtNode;
import AST.Util.ParaNode;
import Util.Position;

public class LambdaExprNode extends ExprNode {

    public ArrayList<ParaNode> paraList = new ArrayList<>();
    public SuiteStmtNode body;
    public ArrayList<ExprNode> argList = new ArrayList<>();

    public LambdaExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
