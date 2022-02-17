package org.pjp.cag;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FunctionTest {

    @Test
    public void testGroup() {

        assertEquals(0, Function.LDA.group());

        assertEquals(1, Function.ADDN.group());

        assertEquals(2, Function.STA.group());

        assertEquals(3, Function.JEQ.group());

        assertEquals(4, Function.SIN.group());

        assertEquals(5, Function.PNL.group());
    }

    @Test
    public void testInstructionClass() {
        assertEquals("org.pjp.cag.instruction.group0.LDA", Function.LDA.instructionClass());

        assertEquals("org.pjp.cag.instruction.group1.ADDN", Function.ADDN.instructionClass());

        assertEquals("org.pjp.cag.instruction.group2.STA", Function.STA.instructionClass());

        assertEquals("org.pjp.cag.instruction.group3.JEQ", Function.JEQ.instructionClass());

        assertEquals("org.pjp.cag.instruction.group4.SIN", Function.SIN.instructionClass());

        assertEquals("org.pjp.cag.instruction.group5.PNL", Function.PNL.instructionClass());
   }

    @Test
    public void testToString() {
        Function function = Function.LDAN;

        assertEquals("10", function.toString());
    }

}
