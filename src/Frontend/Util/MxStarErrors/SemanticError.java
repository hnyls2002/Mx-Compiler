package Frontend.Util.MxStarErrors;

import Frontend.Util.Position;

public class SemanticError extends BaseError {
    public SemanticError(String msg, Position pos) {
        super("Semantic Error : " + msg, pos);
    }

}
