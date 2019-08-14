package com.github.eborgbjerg.chessshell.pgnparser;

import static java.lang.Character.*;

public class StringSymbolGetter implements SymbolGetter {

    private final CharGetter source;
    private PgnSymbolType symbolType;

    StringSymbolGetter(String source) {
        this.source = new StringCharGetter(source);
    }

    @Override
    public void readSymbol() {

        char next;
        do {
            next = source.getChar();
        } while (isWhitespace(source.getChar()));

        if (next == '[') {

            // todo where to collect tag name + value?

            this.symbolType = PgnSymbolType.MOVE_NUMBER_INDICATOR;
        }
        else if (Character.isDigit(next)) {
            // todo skip any dots
            //
            this.symbolType = PgnSymbolType.MOVE_NUMBER_INDICATOR;
        }
        else {
            this.symbolType = PgnSymbolType.MOVE_TEXT;
        }
    }

    @Override
    public PgnSymbolType getSymbolType() {
        return this.symbolType;
    }
}
