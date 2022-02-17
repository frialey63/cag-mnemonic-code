package org.pjp.cag.dev;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * This device is the punched card reader & writer.
 * @author developer
 *
 */
public final class PunchedCard {

    // CHECKSTYLE:OFF encapsulation

    /**
     * The punched card reader.
     */
    public static InputStream in = System.in;

    /**
     * The punched card writer.
     */
    public static PrintStream out = System.out;

    // CHECKSTYLE:ON

    /**
     * @param in The InputStream associated with the reader
     */
    public static void setIn(InputStream in) {
        PunchedCard.in = in;
    }

    /**
     * @param out The PrintStream associated with the writer
     */
    public static void setOut(PrintStream out) {
        PunchedCard.out = out;
    }

    private PunchedCard() {
        // prevent instantiation
    }
}
