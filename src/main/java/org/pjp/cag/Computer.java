package org.pjp.cag;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.pjp.cag.dev.PaperTape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The computer has a store and coordinates the assembly of the program by the Assembler and the execution of the program by the Interpreter.
 * @author developer
 *
 */
public final class Computer {

    /**
     * The name of the character set.
     */
    public static final String CHARSET = "UTF-8";

    private static final Logger LOGGER = LoggerFactory.getLogger(Computer.class);

    private static final String USAGE = "usage: org.pjp.cag.Computer <filename> [trace]";

    private static final File DATA = new File("data", "data.dat");

    /**
     * @param args The program arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length >= 1) {
            Path path = Paths.get(args[0]);

            try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(DATA), Computer.CHARSET)) {
                PaperTape.setIn(inputStreamReader);

                innerMain(path, (args.length == 2));
            }
        } else {
            System.err.println(USAGE);
        }
    }

    static void innerMain(Path path, boolean trace) throws IOException {
        Store store = new Store();

        assert store.zero();

        if (new Assembler().assemble(path, store)) {
            assert store.zero();

            if (LOGGER.isDebugEnabled()) {
                store.dump();
            }

            new Interpreter().interpret(store, trace);

            assert store.zero();
        }
    }

    private Computer() {
        // prevent instantiation
    }

}
