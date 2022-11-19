package Frontend.Util.MxStarErrors;

import Frontend.Util.Position;

public class SyntaxError extends BaseError {
    public SyntaxError(String msg, Position pos) {
        super("Syntax Error : " + msg, pos);
    }
}