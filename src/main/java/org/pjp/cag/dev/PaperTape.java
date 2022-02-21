package org.pjp.cag.dev;

import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.pjp.cag.CAGMnemonicCode1964;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This device is the paper tape reader & writer.
 * @author developer
 *
 */
public final class PaperTape {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaperTape.class);

    // CHECKSTYLE:OFF encapsulation

    /**
     * The paper tape reader.
     */
    public static InputStreamReader in;

    /**
     * The paper tape writer.
     */
    public static PrintStream out = System.out;

    // CHECKSTYLE:ON

    static {
        try {
            in = new InputStreamReader(System.in, CAGMnemonicCode1964.CHARSET);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("caught UnsupportedEncodingException while initialising in", e);
        }
    }

    /**
     * @param in The InputStreamReader associated with the reader
     */
    public static void setIn(InputStreamReader in) {
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
