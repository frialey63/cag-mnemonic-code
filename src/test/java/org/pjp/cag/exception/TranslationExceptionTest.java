package org.pjp.cag.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TranslationExceptionTest {

    @Test
    public void testGetError() {
        TranslationException exception = new TranslationException(TranslationError.ERR_1);

        assertEquals(TranslationError.ERR_1, exception.getError());
    }

    @Test
    public void testGetMessage() {
        TranslationException exception = new TranslationException(TranslationError.ERR_1);

        assertEquals("ERR  1 %4d Inadmissable character - no decode.", exception.getMessage());
    }

}
