package Util.MxStarErrors;

import Util.position;

public class InternalError extends BaseError {
    public InternalError(String msg, position pos) {
        super("Internal Error" + msg, pos);
    }
}
