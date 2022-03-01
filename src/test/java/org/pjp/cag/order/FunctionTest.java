package org.pjp.cag.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

        assertNull(Function.STOP.code());
    }

    @Test
    public void testRevisedCode() {
        assertNull(Function.LDA.revisedCode());

        assertEquals(11, (int) Function.ADDN.code());

        assertNull(Function.STA.revisedCode());

        assertEquals(31, (int) Function.JEQ.revisedCode());

        assertNull(Function.SIN.revisedCode());

        assertNull(Function.PNL.revisedCode());

        assertEquals(99, (int) Function.STOP.revisedCode());
    }

    @Test
    public void testGetCode() {
        assertFalse(CAGMnemonicCode.isRevised());

        assertEquals(0, (int) Function.LDA.getCode());

        assertEquals(11, (int) Function.ADDN.getCode());

        assertEquals(20, (int) Function.STA.getCode());

        assertEquals(32, (int) Function.JEQ.getCode());

        assertEquals(43, (int) Function.SIN.getCode());

        assertEquals(54, (int) Function.PNL.getCode());

        assertNull(Function.STOP.code());
    }

    @Test
    public void testGetCodeRevised() {
        try {
            CAGMnemonicCode.setYear(CAGMnemonicCode.YEAR_1968);

            assertTrue(CAGMnemonicCode.isRevised());

            assertEquals(0, (int) Function.LDA.getCode());

            assertEquals(11, (int) Function.ADDN.getCode());

            assertEquals(20, (int) Function.STA.getCode());

            assertEquals(31, (int) Function.JEQ.getCode());

            assertEquals(43, (int) Function.SIN.getCode());

            assertEquals(54, (int) Function.PNL.getCode());

            assertEquals(99, (int) Function.STOP.revisedCode());
        } finally {
            CAGMnemonicCode.setYear(CAGMnemonicCode.YEAR_1964);
        }
    }

    @Test
    public void testGetGroup() {
        assertEquals(0, (int) Function.LDA.getGroup());

        assertEquals(1, (int) Function.ADDN.getGroup());

        assertEquals(2, (int) Function.STA.getGroup());

        assertEquals(3, (int) Function.JEQ.getGroup());

        assertEquals(4, (int) Function.SIN.getGroup());

        assertEquals(5, (int) Function.PNL.getGroup());

        assertNull(Function.STOP.getGroup());
    }

    @Test
    public void testGetGroupRevised() {
        try {
            CAGMnemonicCode.setYear(CAGMnemonicCode.YEAR_1968);

            assertEquals(0, (int) Function.LDA.getGroup());

            assertEquals(1, (int) Function.ADDN.getGroup());

            assertEquals(2, (int) Function.STA.getGroup());

            assertEquals(3, (int) Function.JEQ.getGroup());

            assertEquals(4, (int) Function.SIN.getGroup());

            assertEquals(5, (int) Function.PNL.getGroup());

            assertEquals(9, (int) Function.STOP.getGroup());
        } finally {
            CAGMnemonicCode.setYear(CAGMnemonicCode.YEAR_1964);
        }
    }

    @Test
    public void testGetInstructionClass() {
        assertEquals("org.pjp.cag.instruction.group0.LDA", Function.LDA.getInstructionClass());

        assertEquals("org.pjp.cag.instruction.group1.ADDN", Function.ADDN.getInstructionClass());

        assertEquals("org.pjp.cag.instruction.group2.STA", Function.STA.getInstructionClass());

        assertEquals("org.pjp.cag.instruction.group3.JEQ", Function.JEQ.getInstructionClass());

        assertEquals("org.pjp.cag.instruction.group4.SIN", Function.SIN.getInstructionClass());

        assertEquals("org.pjp.cag.instruction.group5.PNL", Function.PNL.getInstructionClass());

        assertNull(Function.STOP.getInstructionClass());
    }


    @Test
    public void testGetInstructionClassRevised() {
        try {
            CAGMnemonicCode.setYear(CAGMnemonicCode.YEAR_1968);

            assertTrue(CAGMnemonicCode.isRevised());

            assertEquals("org.pjp.cag.instruction.group0.LDA", Function.LDA.getInstructionClass());

            assertEquals("org.pjp.cag.instruction.group1.rev.ADDN", Function.ADDN.getInstructionClass());

            assertEquals("org.pjp.cag.instruction.group2.STA", Function.STA.getInstructionClass());

            assertEquals("org.pjp.cag.instruction.group3.rev.JEQ", Function.JEQ.getInstructionClass());

            assertEquals("org.pjp.cag.instruction.group4.SIN", Function.SIN.getInstructionClass());

            assertEquals("org.pjp.cag.instruction.group5.PNL", Function.PNL.getInstructionClass());

            assertEquals("org.pjp.cag.instruction.group9.rev.STOP", Function.STOP.getInstructionClass());
        } finally {
            CAGMnemonicCode.setYear(CAGMnemonicCode.YEAR_1964);
        }
    }

    @Test
    public void testToString() {
        Function function = Function.LDAN;

        assertEquals("10", function.toString());
    }

}
