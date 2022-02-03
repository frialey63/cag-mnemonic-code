package org.pjp.cag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OrderTest {

    /*
     * Order without address
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

    /*
     * Order with address
     */

    @Test
    public void testCreateLDAN() {
        Order order = Order.create(false, "LDAN", "123", null);

        assertFalse(order.query);
        assertEquals(OrderNumber.LDAN, order.orderNumber);
        assertTrue(order.hasAddress());
        assertEquals(123, order.address);
        assertFalse(order.hasModifier());
    }

    @Test
    public void testCreateQLDAN() {
        Order order = Order.create(true, "LDAN", "123", null);

        assertTrue(order.query);
        assertEquals(OrderNumber.LDAN, order.orderNumber);
        assertTrue(order.hasAddress());
        assertEquals(123, order.address);
        assertFalse(order.hasModifier());
    }

    /*
     * Order with address and modifier
     */

    @Test
    public void testCreateLDA() {
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
        Order order = Order.create(true, "LDA", "123", "3");

        assertTrue(order.query);
        assertEquals(OrderNumber.LDA, order.orderNumber);
        assertTrue(order.hasAddress());
        assertTrue(order.hasModifier());
        assertEquals(123, order.address);
        assertEquals(3, order.modifier);
    }

}
