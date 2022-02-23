package org.pjp.cag.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pjp.cag.exception.TranslationException;

public class OrderTest {

    @Test
    public void testToString() throws TranslationException {
        Order order = Order.create(false, "PNL", null, null);

        assertEquals("5400000", order.toString());

        order = Order.create(true, "PNL", "12", null);

        assertEquals("5410120", order.toString());
    }

    @Test
    public void testToStringWithAddress() throws TranslationException {
        Order order = Order.create(false, "LDAN", "44", null);

        assertEquals("1000440", order.toString());

        order = Order.create(true, "LDAN", "44", null);

        assertEquals("1010440", order.toString());
    }

    @Test
    public void testToStringWithModifier() throws TranslationException {
        Order order = Order.create(false, "LDA", "44", "3");

        assertEquals("0000443", order.toString());

        order = Order.create(true, "LDA", "44", "3");

        assertEquals("0010443", order.toString());
    }

    /*
     * Order without address
     */

    @Test
    public void testCreatePNL() throws TranslationException {
        Order order = Order.create(false, "PNL", null, null);

        assertFalse(order.query());
        assertEquals(Function.PNL, order.function());
        assertFalse(order.hasAddress());
        assertFalse(order.hasModifier());
    }

    /*
     * Order with address and (optional) modifier
     */

    @Test
    public void testCreateLDA() throws TranslationException {
        Order order = Order.create(false, "LDA", "123", null);

        assertFalse(order.query());
        assertEquals(Function.LDA, order.function());
        assertTrue(order.hasAddress());
        assertFalse(order.hasModifier());
        assertEquals(123, order.address());
    }

    @Test
    public void testCreateLDAModified() throws TranslationException {
        Order order = Order.create(false, "LDA", "123", "3");

        assertFalse(order.query());
        assertEquals(Function.LDA, order.function());
        assertTrue(order.hasAddress());
        assertTrue(order.hasModifier());
        assertEquals(123, order.address());
        assertEquals(3, order.modifier());
    }

    @Test
    public void testCreateQLDA() throws TranslationException {
        Order order = Order.create(true, "LDA", "123", null);

        assertTrue(order.query());
        assertEquals(Function.LDA, order.function());
        assertTrue(order.hasAddress());
        assertFalse(order.hasModifier());
        assertEquals(123, order.address());
    }

    @Test
    public void testCreateQLDAModified() throws TranslationException {
        Order order = Order.create(true, "LDA", "123", "3");

        assertTrue(order.query());
        assertEquals(Function.LDA, order.function());
        assertTrue(order.hasAddress());
        assertTrue(order.hasModifier());
        assertEquals(123, order.address());
        assertEquals(3, order.modifier());
    }

}
