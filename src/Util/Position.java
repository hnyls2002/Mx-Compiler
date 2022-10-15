package Util;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Position {
    private int row, column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Position(Token token) {
        this.row = token.getLine();
        this.column = token.getCharPositionInLine();
    }

    public Position(TerminalNode terminal) {
        this(terminal.getSymbol());
    }

    public Position(ParserRuleContext ctx) {
        this(ctx.getStart());
    }

    public int row() {
        return this.row;
    }

    public int column() {
        return this.column;
    }

    public String toString() {
        return this.row + "," + this.column;
    }
}
