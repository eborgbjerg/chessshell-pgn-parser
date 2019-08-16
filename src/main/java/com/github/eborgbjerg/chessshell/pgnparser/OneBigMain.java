package com.github.eborgbjerg.chessshell.pgnparser;

import java.io.*;

import static java.lang.System.*;


public class OneBigMain {

    // for comparing performance, to check whether OO design has an effect
    // with 1G file:
    // with getChar in separate class:
    // ~4min 30s.  (Scid opens in ~ 1 min) after that we read it in ~25 s.
    // In main:
    // 1s

    // 1st time the 1G file is read it takes several minutes.
    // after that it takes about 1 second.
    //

    static boolean sensor;

    public static void main(String[] args) throws IOException {

        out.println("time before " + currentTimeMillis());

        directMethod(args[0]);

        //withParser(args[0]);

        out.println("time after " + currentTimeMillis());
    }

    private static void withParser(String arg) throws IOException {

        PgnParser parser = new PgnParser.Builder(new SymbolGetter(new File(arg)), new TestPgnParserReceiver()).build();

        parser.parse();
    }


    @SuppressWarnings("squid:S2201")
    private static void directMethod(String arg) throws IOException {

        InputStream is = new FileInputStream(new File(arg));
        try (BufferedInputStream bis = new BufferedInputStream(is)) {
            byte[] buffer = new byte[4096];
            boolean done = (bis.read(buffer, 0, 4096) == -1);

            while (!done) {
                for (int index = 0; index < 4096; index++) {
                    // touch each byte
                    //noinspection ResultOfMethodCallIgnored
                    sensor = Character.isDigit(buffer[index]);
                }
                done = (bis.read(buffer, 0, 4096) == -1);
            }
        }

        out.println(sensor);
    }

}
