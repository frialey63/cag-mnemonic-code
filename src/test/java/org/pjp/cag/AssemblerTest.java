package org.pjp.cag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.pjp.cag.exception.assembly.ParseException;
import org.pjp.cag.exception.assembly.StorageException;
import org.pjp.cag.exception.assembly.UnknownDirectiveException;
import org.pjp.cag.test.TestConstants;

public class AssemblerTest {

    @Test
    public void testAssembleNumbers() throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource("numbers.txt").toURI());

        Store store = new Store();

        new Assembler().assemble(path, store);

        assertEquals(0f, store.getLocation(12).number(), TestConstants.DELTA);
        assertEquals(1.0f, store.getLocation(13).number(), TestConstants.DELTA);
        assertEquals(-1.0f, store.getLocation(14).number(), TestConstants.DELTA);
        assertEquals(123.456f, store.getLocation(15).number(), 0.001f);
        assertEquals(-321.654f, store.getLocation(16).number(), 0.001f);
        assertEquals(+2.0e-04, store.getLocation(17).number(), TestConstants.DELTA);
    }

    @Test
    public void testAssembleCharacters() throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource("characters.txt").toURI());

        Store store = new Store();

        new Assembler().assemble(path, store);

        assertEquals('H', store.getLocation(12).character());
        assertEquals('E', store.getLocation(13).character());
        assertEquals('L', store.getLocation(14).character());
        assertEquals('L', store.getLocation(15).character());
        assertEquals('O', store.getLocation(16).character());
    }

    @Test
    public void testAssembleQuery() throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource("query.txt").toURI());

        Store store = new Store();

        new Assembler().assemble(path, store);

        assertTrue(store.getLocation(12).order().query);
        assertFalse(store.getLocation(13).order().query);
        assertFalse(store.getLocation(14).order().query);
        assertTrue(store.getLocation(15).order().query);
        assertFalse(store.getLocation(16).order().query);
    }

    @Test(expected = StorageException.class)
    public void testAssembleMissingStorageDirective() throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource("missing_storage_directive.txt").toURI());

        Store store = new Store();

        new Assembler().assemble(path, store);
    }

    @Test(expected = ParseException.class)
    public void testAssembleUnparseableText() throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource("unparseable_text.txt").toURI());

        Store store = new Store();

        new Assembler().assemble(path, store);
    }

    @Test(expected = UnknownDirectiveException.class)
    public void testAssembleUnknownDirective() throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource("unknown_directive.txt").toURI());

        Store store = new Store();

        new Assembler().assemble(path, store);
    }

}
