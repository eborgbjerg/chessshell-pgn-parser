package com.github.eborgbjerg.chessshell.pgnparser;

import java.util.Objects;

public class PgnParser {

    private final PgnSource source;
    private final PgnParserReceiver receiver;

    private PgnParser(PgnSource source, PgnParserReceiver receiver) {
        this.source = source;
        this.receiver = receiver;
    }

    public void parse() {
        while (!source.isDone()) {
            source.readSymbol();
            source.getSymbolType();
            // todo state objects to handle situation + notify receiver
        }
    }


    public static class Builder {

        private final PgnSource source;
        private final PgnParserReceiver pgnParserReceiver;

        Builder(PgnSource source, PgnParserReceiver receiver) {
            this.source = source;
            this.pgnParserReceiver = receiver;
        }


        public PgnParser build() {
            Objects.requireNonNull(this.source);
            Objects.requireNonNull(this.pgnParserReceiver);

            return new PgnParser(this.source, pgnParserReceiver);
        }
    }


}
