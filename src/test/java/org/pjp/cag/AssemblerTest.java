package org.pjp.cag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.pjp.cag.exception.assembly.ParseException;
import org.pjp.cag.exception.assembly.StorageException;
import org.pjp.cag.exception.assembly.UnknownDirectiveException;

public class AssemblerTest {

    private static final String DATA = "data";

    private static final String NUMBERS = DATA + "/numbers.txt";

    private static final String CHARACTERS = DATA + "/characters.txt";

    private static final String QUERY = DATA + "/query.txt";

    private static final String MISSING_STORAGE_DIRECTIVE = DATA + "/missing_storage_directive.txt";

    private static final String UNPARSEABLE_TEXT = DATA + "/unparseable_text.txt";

    private static final String UNKNOWN_DIRECTIVE = DATA + "/unknown_directive.txt";

    @Test
    public void testAssembleNumbers() throws IOException {
        Path path = Paths.get(NUMBERS);

        Store store = new Store();

        new Assembler().assemble(path, store);

        assertEquals(0, store.getLocation(12).number(), TestConstants.PRECISION);
        assertEquals(1, store.getLocation(13).number(), TestConstants.PRECISION);
        assertEquals(-1, store.getLocation(14).number(), TestConstants.PRECISION);
        assertEquals(123.456, store.getLocation(15).number(), TestConstants.PRECISION);
        assertEquals(-321.654, store.getLocation(16).number(), TestConstants.PRECISION);
        assertEquals(+2.0e-04, store.getLocation(17).number(), TestConstants.PRECISION);
    }

    @Test
    public void testAssembleCharacters() throws IOException {
        Path path = Paths.get(CHARACTERS);

        Store store = new Store();

        new Assembler().assemble(path, store);

        assertEquals('H', store.getLocation(12).character());
        assertEquals('E', store.getLocation(13).character());
        assertEquals('L', store.getLocation(14).character());
        assertEquals('L', store.getLocation(15).character());
        assertEquals('O', store.getLocation(16).character());
    }

    @Test
    public void testAssembleQuery() throws IOException {
        Path path = Paths.get(QUERY);

        Store store = new Store();

        new Assembler().assemble(path, store);

        assertTrue(store.getLocation(12).order().query);
        assertFalse(store.getLocation(13).order().query);
        assertFalse(store.getLocation(14).order().query);
        assertTrue(store.getLocation(15).order().query);
        assertTrue(store.getLocation(16).order().query);
    }

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
