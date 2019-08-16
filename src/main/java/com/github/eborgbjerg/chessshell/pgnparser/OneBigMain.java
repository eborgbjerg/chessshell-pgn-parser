package com.github.eborgbjerg.chessshell.pgnparser;

import java.io.*;

import static java.lang.System.*;


public class OneBigMain {

    // for comparing performance, to check whether OO design has an effect
    // with 1G file on local file system:
    // + direct: ~3s
    // + parser: ~3s


    private static boolean sensor;

    public static void main(String[] args) throws IOException {
        directMethod(args[0]);
        withParser(args[0]);
    }

    private static void withParser(String arg) throws IOException {
        out.println("withParser - time before " + currentTimeMillis());

        PgnParser parser = new PgnParser.Builder(new SymbolGetter(new File(arg)), new TestPgnParserReceiver()).build();
        parser.parse();

        out.println("withParser - time after " + currentTimeMillis());
    }


    @SuppressWarnings("squid:S2201")
    private static void directMethod(String arg) throws IOException {
        out.println("directMethod - time before " + currentTimeMillis());

        InputStream is = new FileInputStream(new File(arg));
        try (BufferedInputStream bis = new BufferedInputStream(is)) {
            byte[] buffer = new byte[4096];
            boolean done = (bis.read(buffer, 0, 4096) == -1);

            while (!done) {
                for (int index = 0; index < 4096; index++) {
                    // touch each byte
                    sensor = Character.isDigit(buffer[index]);
                }
                done = (bis.read(buffer, 0, 4096) == -1);
            }
        }

        out.println(sensor);

        out.println("directMethod - time after " + currentTimeMillis());
    }

}
