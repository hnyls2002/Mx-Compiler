package Frontend.MxStar.MxStarErrors;

import AST.Util.Position;

public abstract class BaseError extends RuntimeException {
    private Position pos;
    private String message;

    public BaseError(String msg, Position pos) {
        this.message = msg;
        this.pos = pos;
    }

    public String toString() {
        return message + " " + pos.toString();
    }
}