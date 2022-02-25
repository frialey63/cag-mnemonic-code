package org.pjp.cag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.dev.PaperTape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 * The CAG Mnemonic Code 1964 coordinates the assembly of the program by the Assembler and the execution of the program by the Interpreter.
 * @author developer
 *
 */
public final class CAGMnemonicCode {

    /**
     * The maximum integer 2^17 - 1.
     */
    public static final int MAX_INT = 131071;

    /**
     * The name of the character set.
     */
    public static final String CHARSET = "UTF-8";

    private static final File DIR = new File("data");

    private static final Logger LOGGER = LoggerFactory.getLogger(CAGMnemonicCode.class);

    private static final String USAGE = "usage: org.pjp.cag.CAGMnemonicCode <program-file> [-f data-file] [-Q trace] [-Y 1964 | 1968 | 2022]";

    private static final OptionParser PARSER = new OptionParser("Qf:");

    static InputStream getInputStream(File file) throws FileNotFoundException {
        if (file == null) {
            return System.in;
        }

        return new FileInputStream(file);
    }

    /**
     * @param args The program arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        OptionSet options = PARSER.parse(args);

        List<?> nonOptionArguments = options.nonOptionArguments();

        if (nonOptionArguments.size() == 1) {
            Path path = Paths.get((String) nonOptionArguments.get(0));
            File data = options.has("f") ? new File(DIR, (String) options.valueOf("f")) : null;
            boolean trace = options.has("Q");

            try (InputStreamReader inputStreamReader = new InputStreamReader(getInputStream(data), CAGMnemonicCode.CHARSET)) {
                PaperTape.setIn(inputStreamReader);

                innerMain(path, trace);
            }
        } else {
            System.out.println(USAGE);
        }
    }

    static void innerMain(Path path, boolean trace) {
        Store store = new Store();

        assert store.zero();

        if (new Assembler().assemble(path, store)) {
            assert store.zero();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(store.dump());
            }

            new Interpreter().interpret(store, trace);

            assert store.zero();
        }
    }

    private CAGMnemonicCode() {
        // prevent instantiation
    }

}
