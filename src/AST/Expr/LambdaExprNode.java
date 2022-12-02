package AST.Expr;

import java.util.ArrayList;

import AST.Info.TypeIdPair;
import AST.Stmt.SuiteStmtNode;
import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class LambdaExprNode extends ExprNode {

    public ArrayList<TypeIdPair> paraList = new ArrayList<>();
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
