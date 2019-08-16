package com.github.eborgbjerg.chessshell.pgnparser;

interface PgnSource {

    /**
     * Read a symbol.
     */
    void readSymbol();

    PgnSymbolType getSymbolType();

    /**
     * Check whether the source is exhausted.
     * @return true iff there are no more symbols to read.
     */
    boolean isDone();

}
