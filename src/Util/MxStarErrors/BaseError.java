package Util.MxStarErrors;

import Util.position;

public abstract class BaseError extends RuntimeException {
    private position pos;
    private String message;

    public BaseError(String msg, position pos) {
        this.message = msg;
        this.pos = pos;
    }

    public String toString() {
        return message + ":" + pos.toString();
    }
}