package Util.MxStarErrors;

import Util.position;

public class SyntaxError extends BaseError {
    public SyntaxError(String msg, position pos) {
        super("Syntax Error" + msg, pos);
    }
}