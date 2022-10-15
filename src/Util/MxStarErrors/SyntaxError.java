package Util.MxStarErrors;

import Util.Position;

public class SyntaxError extends BaseError {
    public SyntaxError(String msg, Position pos) {
        super("Syntax Error" + msg, pos);
    }
}