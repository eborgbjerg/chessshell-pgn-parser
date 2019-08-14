package com.github.eborgbjerg.chessshell.pgnparser;

public class PgnSourceString implements PgnSource {

    private final String source;

    PgnSourceString(String source) {
        this.source = source;
    }

    @Override
    public void getSymbol() {

    }
}
