package org.pjp.cag.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TranslationErrorTest {

    @Test
    public void testNumber() {
        TranslationError err = TranslationError.ERR_1;

        assertEquals(1, err.number());
    }

    @Test
    public void testDescription() {
        TranslationError err = TranslationError.ERR_1;

        assertEquals("Inadmissable character - no decode.", err.description());
    }

}
