package com.github.eborgbjerg.chessshell.pgnparser;

public class StringCharGetter implements CharGetter {

    private final String source;
    private int index;
    private final int maxIndex;

    StringCharGetter(String source) {
        this.source = source;
        this.index = 0;
        this.maxIndex = source.length() - 1;
    }

    @Override
    public char getChar() {
        return source.charAt(index++);
    }

    @Override
    public boolean isDone() {
        return index > maxIndex;
    }
}
