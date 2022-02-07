package org.pjp.cag;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrderNumberTest {

    @Test
    public void testArity() {
        OrderNumber orderNumber = OrderNumber.LDAN;

        assertEquals(1, orderNumber.arity());
    }

    @Test
    public void testInstructionClass() {
        OrderNumber orderNumber = OrderNumber.LDAN;

        assertEquals("org.pjp.cag.instruction.LDAN", orderNumber.instructionClass());
    }

    @Test
    public void testToString() {
        OrderNumber orderNumber = OrderNumber.LDAN;

        assertEquals("10", orderNumber.toString());
    }

}
