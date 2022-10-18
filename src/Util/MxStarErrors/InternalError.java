package Util.MxStarErrors;

import Util.Position;

public class InternalError extends BaseError {
    public InternalError(String msg, Position pos) {
        super("Internal Error : " + msg, pos);
    }
}
