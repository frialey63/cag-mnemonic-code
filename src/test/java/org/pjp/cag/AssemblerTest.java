package org.pjp.cag;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.pjp.cag.exception.ParseException;
import org.pjp.cag.exception.StorageException;

public class AssemblerTest {

    private static final String MISSING_STORAGE_DIRECTIVE = "data/missing_storage_directive.txt";

    private static final String UNPARSEABLE_TEXT = "data/unparseable_text.txt";

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

}
