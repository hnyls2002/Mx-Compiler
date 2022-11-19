package AST.Expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Stmt.SuiteStmtNode;
import Frontend.Util.Position;
import Frontend.Util.TypeIdPair;

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
