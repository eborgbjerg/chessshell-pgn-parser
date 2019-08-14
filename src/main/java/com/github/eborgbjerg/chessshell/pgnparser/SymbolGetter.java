package com.github.eborgbjerg.chessshell.pgnparser;

public interface SymbolGetter {

    void readSymbol();

    PgnSymbolType getSymbolType();

}
