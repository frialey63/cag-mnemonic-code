package org.pjp.cag;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Computer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Computer.class);

    private static final String USAGE = "usage: org.pjp.cag.Computer <filename>";

    public static void main(String[] args) {
        if (args.length == 1) {
            Path path = Paths.get(args[0]);

            innerMain(path);
        } else {
            System.err.println(USAGE);
        }
    }

    static void innerMain(Path program) {
        Store store = new Store();

        try {
            new Assembler().assemble(program, store);

            if (LOGGER.isDebugEnabled()) {
                store.dump();
            }

            new Interpreter().interpret(store);

        } catch (IOException e) {
            LOGGER.error("Caught IOException while attempting assembly", e);
        }
    }

    private Computer() {
        // prevent instantiation
    }

}
