package com.github.eborgbjerg.chessshell.pgnparser;

import org.junit.Test;

import java.io.IOException;

public class PgnParserBuilderTest {

    @Test(expected = RuntimeException.class)
    public void failsWithoutSource() {
        new PgnParser.Builder(null, new TestPgnParserReceiver()).build();
    }

    @Test
    public void withEmptySourceString() throws IOException {
        PgnParser parser = new PgnParser.Builder(new SymbolGetter(""), new TestPgnParserReceiver()).build();
        parser.parse();
        // todo assert receivers state
    }

    @Test(expected = RuntimeException.class)
    public void withNullSourceString() throws IOException {
        PgnParser parser = new PgnParser.Builder(new SymbolGetter((String) null), new TestPgnParserReceiver()).build();
        parser.parse();
    }

    @Test
    public void withTinySourceString() throws IOException {
        PgnParser parser = new PgnParser.Builder(new SymbolGetter("1.e4"), new TestPgnParserReceiver()).build();
        parser.parse();
    }


}