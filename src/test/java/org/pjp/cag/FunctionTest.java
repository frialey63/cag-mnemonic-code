package org.pjp.cag;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FunctionTest {

    @Test
    public void testCode() {

        assertEquals(0, Function.LDA.group());

        assertEquals(1, Function.ADDN.group());

        assertEquals(2, Function.STA.group());

        assertEquals(3, Function.JEQ.group());

        assertEquals(4, Function.SIN.group());

        assertEquals(5, Function.PNL.group());
    }

    @Test
    public void testArity() {
        Function function = Function.LDAN;

        assertEquals(1, function.arity());
    }

    @Test
    public void testInstructionClass() {
        Function function = Function.LDAN;

        assertEquals("org.pjp.cag.instruction.LDAN", function.instructionClass());
    }

    @Test
    public void testToString() {
        Function function = Function.LDAN;

        assertEquals("10", function.toString());
    }

}
