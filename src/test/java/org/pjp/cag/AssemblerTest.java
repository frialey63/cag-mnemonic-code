package org.pjp.cag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.pjp.cag.cpu.Store;
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
    public void testAssembleError() throws URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("missing_storage_directive.txt").toURI());

        Store store = new Store();

        assertFalse(new Assembler().assemble(path, store));
    }

    @Test
    public void testAssembleQuery() throws URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("query.txt").toURI());

        Store store = new Store();

        boolean result = new Assembler().assemble(path, store);

        assertTrue(result);
        assertTrue(store.getLocation(12).instruction().query());
        assertFalse(store.getLocation(13).instruction().query());
        assertFalse(store.getLocation(14).instruction().query());
        assertTrue(store.getLocation(15).instruction().query());
        assertFalse(store.getLocation(16).instruction().query());
    }

    @Test
    public void testAssembleNumber() throws URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("number.txt").toURI());

        Store store = new Store();

        boolean result = new Assembler().assemble(path, store);

        assertTrue(result);
        assertEquals(0, store.getLocation(12).number(), TestConstants.DELTA);
        assertEquals(1.0f, store.getLocation(13).number(), TestConstants.DELTA);
        assertEquals(-1.0f, store.getLocation(14).number(), TestConstants.DELTA);
        assertEquals(123.456f, store.getLocation(15).number(), 0.001f);
        assertEquals(-321.654f, store.getLocation(16).number(), 0.001f);
        assertEquals(+2.0e-04, store.getLocation(17).number(), TestConstants.DELTA);
    }

    @Test
    public void testInnerAssembleNumberWithoutSign() throws URISyntaxException {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("0", store);
        });

        assertEquals(TranslationError.ERR_2, exception.getError());
    }

    @Test
    public void testAssembleUnacceptableCharacter() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("ABC$123", store);
        });

        assertEquals(TranslationError.ERR_2, exception.getError());
    }

    @Test
    public void testAssembleNumberOutOfRange() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("+131072", store);
        });

        assertEquals(TranslationError.ERR_3, exception.getError());
    }

    @Test
    public void testAssembleOutOfStoreRange() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Path path = Paths.get(ClassLoader.getSystemResource("missing_storage_directive.txt").toURI());

            Store store = new Store();

            new Assembler().innerAssemble(path, store);
        });

        assertEquals(TranslationError.ERR_4, exception.getError());
    }

    @Test
    public void testAssembleUnknownDirective() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("(FRED)", store);
        });

        assertEquals(TranslationError.ERR_5, exception.getError());
    }

    @Test
    public void testAssembleIntegerInDirective() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("(TITLE 999)", store);
        });

        assertEquals(TranslationError.ERR_6, exception.getError());
    }

    @Test
    public void testAssembleModifierNotIndex() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("LDA 100,10", store);
        });

        assertEquals(TranslationError.ERR_7, exception.getError());
    }

    @Test
    public void testAssembleErrorInMnemonic() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("ZAP", store);
        });

        assertEquals(TranslationError.ERR_8, exception.getError());
    }

    @Test
    public void testAssembleAddressOutOfRange() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("LDA 1001", store);
        });

        assertEquals(TranslationError.ERR_9, exception.getError());
    }

    @Test
    public void testAssembleAddressDirectiveOutOfRange() {
        TranslationException exception = assertThrows(TranslationException.class, () -> {
            Store store = new Store();

            new Assembler().assemble("(STORE 1000)", store);
        });

        assertEquals(TranslationError.ERR_9, exception.getError());
    }

}
