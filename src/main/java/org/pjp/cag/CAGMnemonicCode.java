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

import com.google.common.annotations.VisibleForTesting;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 * The CAG Mnemonic Code 1964 coordinates the assembly of the program by the Assembler and the execution of the program by the Interpreter.
 * @author developer
 *
 */
public final class CAGMnemonicCode {

    /**
     * The original language release year of 1964.
     */
    public static final int YEAR_1964 = 1964;

    /**
     * The revised language release year of 1968.
     */
    public static final int YEAR_1968 = 1968;

    /**
     * The maximum integer 2^17 - 1.
     */
    public static final int MAX_INT = 131071;

    /**
     * The name of the character set.
     */
    public static final String CHARSET = "UTF-8";

    private static final File DATA_DIR = new File(System.getProperty("dataDir", "data"));

    private static final OptionParser PARSER = new OptionParser();

    static {
        PARSER.accepts("a", "assemble only");
        PARSER.accepts("d", "dump contents of store");
        PARSER.accepts("f", "file for data").withRequiredArg().ofType(String.class);
        PARSER.accepts("t", "trace enable");
        PARSER.accepts("Y", "year of revision").withRequiredArg().ofType(String.class);
    }

    private static int year = YEAR_1964;

    /**
     * @return True if the language is the revised version
     */
    public static boolean isRevised() {
        return year == YEAR_1968;
    }

    @VisibleForTesting
    public static void setYear(int year) {
        CAGMnemonicCode.year = year;
    }

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
            File data = options.has("f") ? new File(DATA_DIR, (String) options.valueOf("f")) : null;
            boolean assemble = options.has("a");
            boolean dump = options.has("d");
            boolean trace = options.has("t");

            if (options.has("Y")) {
                year = "1968".equals(options.valueOf("Y")) ? YEAR_1968 : YEAR_1964;
            }

            try (InputStreamReader inputStreamReader = new InputStreamReader(getInputStream(data), CAGMnemonicCode.CHARSET)) {
                PaperTape.setIn(inputStreamReader);

                innerMain(path, assemble, dump, trace);
            }
        } else {
            PARSER.printHelpOn(System.out);
        }
    }

    static void innerMain(Path path, boolean assemble, boolean dump, boolean trace) {
        Store store = new Store();

        assert store.zero();

        if (new Assembler().assemble(path, store)) {
            assert store.zero();

            if (dump) {
                System.out.println(store.dump());
            }

            if (!assemble) {
                new Interpreter().interpret(store, trace);
            }

            assert store.zero();
        }
    }

    private CAGMnemonicCode() {
        // prevent instantiation
    }

}
