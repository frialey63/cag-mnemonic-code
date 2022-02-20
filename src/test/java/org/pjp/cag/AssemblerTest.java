package org.pjp.cag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.pjp.cag.exception.TranslationError;
import org.pjp.cag.exception.TranslationException;
import org.pjp.cag.test.TestConstants;

public class AssemblerTest {

    @Test
    public void testAssemble() throws URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("simple_test.txt").toURI());

        Store store = new Store();

        Assembler assembler = new Assembler();
        boolean result = assembler.assemble(path, store);

        assertTrue(result);
        assertEquals(3, assembler.getDirectives().size());
    }

    @Test
    public void testAssembleQuery() throws URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("query.txt").toURI());

        Store store = new Store();

        boolean result = new Assembler().assemble(path, store);

        assertTrue(result);
        assertTrue(store.getLocation(12).order().query);
        assertFalse(store.getLocation(13).order().query);
        assertFalse(store.getLocation(14).order().query);
        assertTrue(store.getLocation(15).order().query);
        assertFalse(store.getLocation(16).order().query);
    }

    @Test
    public void testAssembleNumbers() throws URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("number.txt").toURI());

        Store store = new Store();

        boolean result = new Assembler().assemble(path, store);

        assertTrue(result);
        assertEquals(0f, store.getLocation(12).number(), TestConstants.DELTA);
        assertEquals(1.0f, store.getLocation(13).number(), TestConstants.DELTA);
        assertEquals(-1.0f, store.getLocation(14).number(), TestConstants.DELTA);
        assertEquals(123.456f, store.getLocation(15).number(), 0.001f);
        assertEquals(-321.654f, store.getLocation(16).number(), 0.001f);
        assertEquals(+2.0e-04, store.getLocation(17).number(), TestConstants.DELTA);
    }

    @Test
    public void testAssembleUnacceptableCharacter() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("ABC$123", store);
        });

        assertEquals(TranslationError.ERR_2.number(), Integer.parseInt(exception.getMessage().trim()));
    }

    @Test
    public void testAssembleNumberOutOfRange() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("+131072", store);
        });

        assertEquals(TranslationError.ERR_3.number(), Integer.parseInt(exception.getMessage().trim()));
    }

    @Test
    public void testAssembleOutOfStoreRange() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Path path = Paths.get(ClassLoader.getSystemResource("missing_storage_directive.txt").toURI());

            Store store = new Store();

            new Assembler().innerAssemble(path, store);
        });

        assertEquals(TranslationError.ERR_4.number(), Integer.parseInt(exception.getMessage().trim()));
    }

    @Test
    public void testAssembleUnknownDirective() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("(FRED)", store);
        });

        assertEquals(TranslationError.ERR_5.number(), Integer.parseInt(exception.getMessage().trim()));
    }

    @Test
    public void testAssembleIntegerInDirective() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("(TITLE 999)", store);
        });

        assertEquals(TranslationError.ERR_6.number(), Integer.parseInt(exception.getMessage().trim()));
    }

    @Test
    public void testAssembleModifierNotIndex() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("LDA 100,10", store);
        });

        assertEquals(TranslationError.ERR_7.number(), Integer.parseInt(exception.getMessage().trim()));
    }

    @Test
    public void testAssembleErrorInMnemonic() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("ZAP", store);
        });

        assertEquals(TranslationError.ERR_8.number(), Integer.parseInt(exception.getMessage().trim()));
    }

    @Test
    public void testAssembleAddressOutOfRange() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("LDA 1001", store);
        });

        assertEquals(TranslationError.ERR_9.number(), Integer.parseInt(exception.getMessage().trim()));
    }

    @Test
    public void testAssembleAddressDirectiveOutOfRange() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("(STORE 1000)", store);
        });

        assertEquals(TranslationError.ERR_9.number(), Integer.parseInt(exception.getMessage().trim()));
    }

}
