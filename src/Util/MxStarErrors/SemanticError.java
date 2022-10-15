package Util.MxStarErrors;

import Util.Position;

public class SemanticError extends BaseError {
    public SemanticError(String msg, Position pos) {
        super("Semantic Error" + msg, pos);
    }

}
