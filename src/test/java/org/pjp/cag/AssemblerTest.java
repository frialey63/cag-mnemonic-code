package org.pjp.cag;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.pjp.cag.exception.ParseException;
import org.pjp.cag.exception.StorageException;
import org.pjp.cag.exception.UnknownDirectiveException;

public class AssemblerTest {

    private static final String DATA = "data";

    private static final String MISSING_STORAGE_DIRECTIVE = DATA + "/missing_storage_directive.txt";

    private static final String UNPARSEABLE_TEXT = DATA + "/unparseable_text.txt";

    private static final String UNKNOWN_DIRECTIVE = DATA + "/unknown_directive.txt";

    @Test(expected = StorageException.class)
    public void testAssembleMissingStorageDirective() throws IOException {
        Path path = Paths.get(MISSING_STORAGE_DIRECTIVE);

        Store store = new Store();

        new Assembler().assemble(path, store);
    }

    @Test(expected = ParseException.class)
    public void testAssembleUnparseableText() throws IOException {
        Path path = Paths.get(UNPARSEABLE_TEXT);

        Store store = new Store();

        new Assembler().assemble(path, store);
    }

    @Test(expected = UnknownDirectiveException.class)
    public void testAssembleUnknownDirective() throws IOException {
        Path path = Paths.get(UNKNOWN_DIRECTIVE);

        Store store = new Store();

        new Assembler().assemble(path, store);
    }

}
