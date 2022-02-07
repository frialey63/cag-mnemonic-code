package org.pjp.cag;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The computer has a store and coordinates the assembly of the program by the Assembler and the execution of the program by the Interpreter.
 * @author developer
 *
 */
public final class Computer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Computer.class);

    private static final String USAGE = "usage: org.pjp.cag.Computer <filename>";

    /**
     * @param args The program arguments
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            Path path = Paths.get(args[0]);

            Store store = new Store();

            assert store.zero();

            try {
                new Assembler().assemble(path, store);

                assert store.zero();

                if (LOGGER.isDebugEnabled()) {
                    store.dump();
                }

                if (true) {
                    new Interpreter().interpret(store);

                    assert store.zero();
                }

            } catch (IOException e) {
                LOGGER.error("Caught IOException while attempting assembly", e);
            }
        } else {
            System.err.println(USAGE);
        }
    }

    private Computer() {
        // prevent instantiation
    }

}
