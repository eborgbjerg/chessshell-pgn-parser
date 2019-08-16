package com.github.eborgbjerg.chessshell.pgnparser;


import java.io.*;

import static java.lang.Character.*;

/**
 * Getting symbols from a pgn source.
 */
public class SymbolGetter implements PgnSource {

    private PgnSymbolType symbolType;
    private BufferedInputStream bis;
    private byte[] buffer;
    private int index;
    private boolean done;

    /**
     * Use a file as source.
     * @param f the file
     * @throws IOException if something goes wrong reading the file
     */
    SymbolGetter(File f) throws IOException {
        init(f);
    }

    /**
     * Use a string as source.
     * Under the cover uses a temporary file.
     * @param source the string
     * @throws IOException if something goes wrong with file IO.
     */
    SymbolGetter(String source) throws IOException {
        File f = File.createTempFile("XXX", ".pgn");
        try (FileWriter fw = new FileWriter(f)) {
            fw.write(source);
        }
        init(f);
    }

    private void init(File f) throws IOException {
        InputStream is = new FileInputStream(f);
        bis = new BufferedInputStream(is);
        buffer = new byte[4096];
        done = (bis.read(buffer, 0, 4096) == -1);
        index = 0;
    }

    public void readSymbol() {

        char next;
        do {
            next = getChar();
        } while (isWhitespace(getChar()));

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

    private char getChar() {
        if (index >= 4096 ) {
            try {
                done = (bis.read(buffer, 0, 4096) == -1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            index = 0;
        }
        return (char) buffer[index++];
    }

    @Override
    public boolean isDone() {
        return done;
    }

}
