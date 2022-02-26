package org.pjp.cag.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.pjp.cag.CAGMnemonicCode;

public class FunctionTest {

    @Test
    public void testCode() {

        assertEquals(0, (int) Function.LDA.code());

        assertEquals(11, (int) Function.ADDN.code());

        assertEquals(20, (int) Function.STA.code());

        assertEquals(32, (int) Function.JEQ.code());

        assertEquals(43, (int) Function.SIN.code());

        assertEquals(54, (int) Function.PNL.code());
    }

    @Test
    public void testRevisedCode() {

        assertEquals(0, (int) Function.LDA.revisedCode());

        assertEquals(11, (int) Function.ADDN.revisedCode());

        assertEquals(20, (int) Function.STA.revisedCode());

        assertEquals(31, (int) Function.JEQ.revisedCode());

        assertEquals(43, (int) Function.SIN.revisedCode());

        assertNull(Function.PNL.revisedCode());
    }

    @Test
    public void testGetCode() {

        assertFalse(CAGMnemonicCode.isRevised());

        assertEquals(0, Function.LDA.getCode());

        assertEquals(11, Function.ADDN.getCode());

        assertEquals(20, Function.STA.getCode());

        assertEquals(32, Function.JEQ.getCode());

        assertEquals(43, Function.SIN.getCode());

        assertEquals(54, Function.PNL.getCode());
    }

    @Test
    public void testGetGroup() {

        assertEquals(0, Function.LDA.getGroup());

        assertEquals(1, Function.ADDN.getGroup());

        assertEquals(2, Function.STA.getGroup());

        assertEquals(3, Function.JEQ.getGroup());

        assertEquals(4, Function.SIN.getGroup());

        assertEquals(5, Function.PNL.getGroup());
    }

    @Test
    public void testGetInstructionClass() {
        assertEquals("org.pjp.cag.instruction.group0.LDA", Function.LDA.getInstructionClass());

        assertEquals("org.pjp.cag.instruction.group1.ADDN", Function.ADDN.getInstructionClass());

        assertEquals("org.pjp.cag.instruction.group2.STA", Function.STA.getInstructionClass());

        assertEquals("org.pjp.cag.instruction.group3.JEQ", Function.JEQ.getInstructionClass());

        assertEquals("org.pjp.cag.instruction.group4.SIN", Function.SIN.getInstructionClass());

        assertEquals("org.pjp.cag.instruction.group5.PNL", Function.PNL.getInstructionClass());
   }

    @Test
    public void testToString() {
        Function function = Function.LDAN;

        assertEquals("10", function.toString());
    }

}
