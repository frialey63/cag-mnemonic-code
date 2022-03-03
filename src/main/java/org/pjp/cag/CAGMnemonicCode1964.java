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
public final class CAGMnemonicCode1964 {

    /**
     * The maximum integer 2^17 - 1.
     */
    public static final int MAX_INT = 131071;

    /**
       The character set for 903 TELECODE is a fore-runner of, and bears a close resemblance to ASCII.

           00   10    20    30    40    50    60    70    80    90   100   110   120

        0 nul   lf                 (     2     <     F     P     Z     d     n     x
        1                          )     3     =     G     Q     [     e     o     y
        2                   sp     *     4     >     H     R     £     f     p     z
        3                    !     +     5     ❿        I     S     ]     g     q
        4                    "     ,     6     ’     J     T     ↑     h     r
        5                    ½     -     7     A     K     U     ←     I     s
        6                    $     .     8     B     L     V     @     j     t
        7 bel                %     /     9     C     M     W     a     k     u   del
        8                    &     0     :     D     N     X     b     l     v
        9 tab                ‘     1     ;     E     O     Y     c     m     w
    */
    public static final String CHARSET = "UTF-8";

    private static final File DATA_DIR = new File(System.getProperty("dataDir", "data"));

    private static final OptionParser PARSER = new OptionParser();

    static {
        PARSER.accepts("a", "assemble only");
        PARSER.accepts("d", "dump contents of store");
        PARSER.accepts("f", "file for data").withRequiredArg().ofType(String.class);
        PARSER.accepts("t", "trace enable");
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

            try (InputStreamReader inputStreamReader = new InputStreamReader(getInputStream(data), CAGMnemonicCode1964.CHARSET)) {
                PaperTape.setIn(inputStreamReader);

                innerMain(path, assemble, dump, trace);
            }
        } else {
            PARSER.printHelpOn(System.out);
        }
    }

    private static void innerMain(Path path, boolean assemble, boolean dump, boolean trace) {
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

    @VisibleForTesting
    static void innerMain(Path path) {
        innerMain(path, false, false, false);
    }

    private CAGMnemonicCode1964() {
        // prevent instantiation
    }

}
