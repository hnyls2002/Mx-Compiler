package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import AST.Util.Position;

public class ForStmtNode extends StmtNode {

    public VarDeclStmtNode varDecl;
    public ExprNode initExpr, condExpr, incExpr;
    public StmtNode body;

    public ForStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
