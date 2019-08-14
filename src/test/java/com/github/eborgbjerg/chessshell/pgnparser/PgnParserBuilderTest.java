package com.github.eborgbjerg.chessshell.pgnparser;

import org.junit.Test;

public class PgnParserBuilderTest {

    @Test(expected = RuntimeException.class)
    public void failsWithoutSource() {
        new PgnParser.Builder(null, new TestPgnParserReceiver()).build();
    }

    @Test
    public void withEmptySourceString() {
        PgnParser parser = new PgnParser.Builder(new PgnSourceString(""), new TestPgnParserReceiver()).build();
        parser.parse();
    }

    @Test
    public void withNullSourceString() {
        PgnParser parser = new PgnParser.Builder(new PgnSourceString(null), new TestPgnParserReceiver()).build();
        parser.parse();
    }

    public void withTinySourceString() {
        PgnParser parser = new PgnParser.Builder(new PgnSourceString("1.e4"), new TestPgnParserReceiver()).build();
        parser.parse();
    }


}