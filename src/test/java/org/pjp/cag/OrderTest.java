package org.pjp.cag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pjp.cag.exception.IncorrectArityException;
import org.pjp.cag.exception.UnknownOrderException;

public class OrderTest {

    /*
     * Order without address
     */

    @Test
    public void testCreateJST() {
        Order order = Order.create(false, "JST", null, null);

        assertFalse(order.query);
        assertEquals(OrderNumber.JST, order.orderNumber);
        assertFalse(order.hasAddress());
        assertFalse(order.hasModifier());
    }

    @Test
    public void testCreateQJST() {
        Order order = Order.create(true, "JST", null, null);

        assertTrue(order.query);
        assertEquals(OrderNumber.JST, order.orderNumber);
        assertFalse(order.hasAddress());
        assertFalse(order.hasModifier());
    }

    @Test(expected = IncorrectArityException.class)
    public void testCreateJSTTooManyArgs() {
        Order.create(false, "JST", "666", null);
    }

    /*
     * Order with address
     */

    @Test
    public void testCreateARC() {
        Order order = Order.create(false, "ARC", "999", null);

        assertFalse(order.query);
        assertEquals(OrderNumber.ARC, order.orderNumber);
        assertTrue(order.hasAddress());
        assertFalse(order.hasModifier());
    }

    @Test
    public void testCreateQARC() {
        Order order = Order.create(true, "ARC", "999", null);

        assertTrue(order.query);
        assertEquals(OrderNumber.ARC, order.orderNumber);
        assertTrue(order.hasAddress());
        assertFalse(order.hasModifier());
    }

    @Test(expected = IncorrectArityException.class)
    public void testCreateARCMissingArg() {
        Order.create(false, "ARC", null, null);
    }

    @Test(expected = IncorrectArityException.class)
    public void testCreateARCMissingTooManyArgs() {
        Order.create(false, "ARC", "999", "9");
    }

    /*
     * Order with address and (optional) modifier
     */

    @Test
    public void testCreateLDA() {
        Order order = Order.create(false, "LDA", "123", null);

        assertFalse(order.query);
        assertEquals(OrderNumber.LDA, order.orderNumber);
        assertTrue(order.hasAddress());
        assertFalse(order.hasModifier());
        assertEquals(123, order.address);
    }

    @Test
    public void testCreateLDAModified() {
        Order order = Order.create(false, "LDA", "123", "3");

        assertFalse(order.query);
        assertEquals(OrderNumber.LDA, order.orderNumber);
        assertTrue(order.hasAddress());
        assertTrue(order.hasModifier());
        assertEquals(123, order.address);
        assertEquals(3, order.modifier);
    }

    @Test
    public void testCreateQLDA() {
        Order order = Order.create(true, "LDA", "123", null);

        assertTrue(order.query);
        assertEquals(OrderNumber.LDA, order.orderNumber);
        assertTrue(order.hasAddress());
        assertFalse(order.hasModifier());
        assertEquals(123, order.address);
    }

    @Test
    public void testCreateQLDAModified() {
        Order order = Order.create(true, "LDA", "123", "3");

        assertTrue(order.query);
        assertEquals(OrderNumber.LDA, order.orderNumber);
        assertTrue(order.hasAddress());
        assertTrue(order.hasModifier());
        assertEquals(123, order.address);
        assertEquals(3, order.modifier);
    }

    @Test(expected = IncorrectArityException.class)
    public void testCreateLDAMissingArg() {
        Order.create(false, "LDA", null, null);
    }

    /*
     * Unknown Order
     */
    @Test(expected = UnknownOrderException.class)
    public void testCreateUnknown() {
        Order.create(false, "ABC", null, null);
    }


}
