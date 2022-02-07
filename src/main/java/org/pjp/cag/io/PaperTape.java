package org.pjp.cag.io;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * This device is the paper tape reader & writer.
 * @author developer
 *
 */
public final class PaperTape {

    // CHECKSTYLE:OFF encapsulation

    /**
     * The paper tape reader.
     */
    public static InputStream in = System.in;

    /**
     * The paper tape writer.
     */
    public static PrintStream out = System.out;

    // CHECKSTYLE:ON

    /**
     * @param in The InputStream associated with the reader
     */
    public static void setIn(InputStream in) {
        PaperTape.in = in;
    }

    /**
     * @param out The PrintStream associated with the writer
     */
    public static void setOut(PrintStream out) {
        PaperTape.out = out;
    }

    private PaperTape() {
        // prevent instantiation
    }
}
