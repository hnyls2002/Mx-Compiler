package Util.MxStarErrors;

import Util.position;

public class SemanticError extends BaseError {
    public SemanticError(String msg, position pos) {
        super("Semantic Error" + msg, pos);
    }

}
