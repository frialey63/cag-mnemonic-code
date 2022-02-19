package org.pjp.cag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pjp.cag.error.TranslationException;
import org.pjp.cag.exception.FaultyWordException;
import org.pjp.cag.test.TestConstants;

public class WordTest {

    @Test
    public void testCreateOrder() throws TranslationException {
        Order order = Order.create(false, "LDA", "123", "3");

        Word word = Word.create(order);

        assertEquals(order, word.order());
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateOrderNotNumber() throws TranslationException {
        Order order = Order.create(false, "LDA", "123", "3");

        Word word = Word.create(order);

        word.number();
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateOrderNotCharacter() throws TranslationException {
        Order order = Order.create(false, "LDA", "123", "3");

        Word word = Word.create(order);

        word.character();
    }

    @Test
    public void testCreateNumber() {
        Word word = Word.create(123);

        assertEquals(123.0f, word.number(), TestConstants.DELTA);
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateNumberNotOrder() {
        Word word = Word.create(123);

        word.order();
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateNumberNotCharacter() {
        Word word = Word.create(123);

        word.character();
    }

    @Test
    public void testCreateCharacter() {
        Word word = Word.create('A');

        assertEquals('A', word.character());
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateCharacterNotOrder() {
        Word word = Word.create('A');

        word.order();
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateCharacterNotNumber() {
        Word word = Word.create('A');

        word.number();
    }

    @Test
    public void testEmpty() {
        Word word = Word.empty();

        assertTrue(word.isEmpty());
    }

}
