package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Frontend.Util.Position;
import Frontend.Util.TypeIdPair;

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
