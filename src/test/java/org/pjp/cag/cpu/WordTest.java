package org.pjp.cag.cpu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pjp.cag.exception.TranslationException;
import org.pjp.cag.exception.internal.FaultyWordException;
import org.pjp.cag.order.Instruction;
import org.pjp.cag.test.TestConstants;

public class WordTest {

    @Test
    public void testCreateInstruction() throws TranslationException {
        Instruction instruction = Instruction.create(false, "LDA", "123", "3");

        Word word = Word.create(instruction);

        assertEquals(instruction, word.instruction());
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateInstructionNotNumber() throws TranslationException {
        Instruction instruction = Instruction.create(false, "LDA", "123", "3");

        Word word = Word.create(instruction);

        word.number();
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateInstructionNotCharacter() throws TranslationException {
        Instruction instruction = Instruction.create(false, "LDA", "123", "3");

        Word word = Word.create(instruction);

        word.character();
    }

    @Test
    public void testCreateNumber() {
        Word word = Word.create(123);

        assertEquals(123.0f, word.number(), TestConstants.DELTA);
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateNumberNotInstruction() {
        Word word = Word.create(123);

        word.instruction();
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateNumberNotCharacter() {
        Word word = Word.create(123);

        word.character();
    }

    @Test
    public void testCreateCharacter() {
        Word word = Word.create('A');

        assertEquals('A', word.character());
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateCharacterNotInstruction() {
        Word word = Word.create('A');

        word.instruction();
    }

    @Test(expected = FaultyWordException.class)
    public void testCreateCharacterNotNumber() {
        Word word = Word.create('A');

        word.number();
    }

    @Test
    public void testEmpty() {
        Word word = Word.empty();

        assertTrue(word.isEmpty());
    }

}
