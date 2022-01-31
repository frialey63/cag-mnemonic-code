package org.pjp.cag;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrderNumberTest {

    @Test
    public void testNumericFunction() {
        OrderNumber orderNumber = OrderNumber.LDAN;

        assertEquals(10, orderNumber.numericFunction());
    }

    @Test
    public void testInstructionClass() {
        OrderNumber orderNumber = OrderNumber.LDAN;

        assertEquals("org.pjp.cag.instruction.LDAN", orderNumber.instructionClass());
    }

}
