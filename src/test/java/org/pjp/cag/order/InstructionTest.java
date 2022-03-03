package org.pjp.cag.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pjp.cag.exception.TranslationException;

public class InstructionTest {

    @Test
    public void testToString() throws TranslationException {
        Instruction instruction = Instruction.create(false, "PNL", null, null);

        assertEquals("5400000", instruction.toString());

        instruction = Instruction.create(true, "PNL", "12", null);

        assertEquals("5410120", instruction.toString());
    }

    @Test
    public void testToStringWithAddress() throws TranslationException {
        Instruction instruction = Instruction.create(false, "LDAN", "44", null);

        assertEquals("1000440", instruction.toString());

        instruction = Instruction.create(true, "LDAN", "44", null);

        assertEquals("1010440", instruction.toString());
    }

    @Test
    public void testToStringWithModifier() throws TranslationException {
        Instruction instruction = Instruction.create(false, "LDA", "44", "3");

        assertEquals("0000443", instruction.toString());

        instruction = Instruction.create(true, "LDA", "44", "3");

        assertEquals("0010443", instruction.toString());
    }

    /*
     * Instruction without address
     */

    @Test
    public void testCreatePNL() throws TranslationException {
        Instruction instruction = Instruction.create(false, "PNL", null, null);

        assertFalse(instruction.query());
        assertEquals(Function.PNL, instruction.function());
        assertFalse(instruction.hasAddress());
        assertFalse(instruction.hasModifier());
    }

    /*
     * INstruction with address and (optional) modifier
     */

    @Test
    public void testCreateLDA() throws TranslationException {
        Instruction instruction = Instruction.create(false, "LDA", "123", null);

        assertFalse(instruction.query());
        assertEquals(Function.LDA, instruction.function());
        assertTrue(instruction.hasAddress());
        assertFalse(instruction.hasModifier());
        assertEquals(123, instruction.address());
    }

    @Test
    public void testCreateLDAModified() throws TranslationException {
        Instruction instruction = Instruction.create(false, "LDA", "123", "3");

        assertFalse(instruction.query());
        assertEquals(Function.LDA, instruction.function());
        assertTrue(instruction.hasAddress());
        assertTrue(instruction.hasModifier());
        assertEquals(123, instruction.address());
        assertEquals(3, instruction.modifier());
    }

    @Test
    public void testCreateQLDA() throws TranslationException {
        Instruction instruction = Instruction.create(true, "LDA", "123", null);

        assertTrue(instruction.query());
        assertEquals(Function.LDA, instruction.function());
        assertTrue(instruction.hasAddress());
        assertFalse(instruction.hasModifier());
        assertEquals(123, instruction.address());
    }

    @Test
    public void testCreateQLDAModified() throws TranslationException {
        Instruction instruction = Instruction.create(true, "LDA", "123", "3");

        assertTrue(instruction.query());
        assertEquals(Function.LDA, instruction.function());
        assertTrue(instruction.hasAddress());
        assertTrue(instruction.hasModifier());
        assertEquals(123, instruction.address());
        assertEquals(3, instruction.modifier());
    }

}
