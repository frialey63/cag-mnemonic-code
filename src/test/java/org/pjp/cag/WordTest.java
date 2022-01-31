package org.pjp.cag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.pjp.cag.exception.FaultyWordException;

public class WordTest {

    @Test
    public void testCreateOrder() {
        Order order = Order.create(false, "LDA", "123", "3");

        Word word = Word.create(order);

        assertEquals(order, word.order());
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateOrderNotNumber() {
        Order order = Order.create(false, "LDA", "123", "3");

        Word word = Word.create(order);

        word.number();
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateOrderNotCharacte0() {
        Order order = Order.create(false, "LDA", "123", "3");

        Word word = Word.create(order);

        word.character();
    }

    @Test
    public void testCreateFloat() {
        Word word = Word.create(123);

        assertEquals(123, word.number(), TestConstants.PRECISION);
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateFloatNotOrder() {
        Word word = Word.create(123);

        word.order();
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateFloatNotCharacter() {
        Word word = Word.create(123);

        word.character();
    }

    @Ignore
    @Test
    public void testCreateChar() {
        fail("Not yet implemented");
    }

}
