package Frontend.MxStar.MxStarErrors;

import AST.Util.Position;

public class SemanticError extends BaseError {
    public SemanticError(String msg, Position pos) {
        super("Semantic Error : " + msg, pos);
    }

}
