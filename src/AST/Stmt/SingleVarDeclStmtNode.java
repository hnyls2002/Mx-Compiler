package AST.Stmt;

import AST.Expr.ExprNode;
import AST.Info.TypeIdPair;
import AST.Util.Position;
import Share.Visitors.ASTVisitor;

public class SingleVarDeclStmtNode extends StmtNode {
    public TypeIdPair decl;
    public ExprNode expr;

    public SingleVarDeclStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
