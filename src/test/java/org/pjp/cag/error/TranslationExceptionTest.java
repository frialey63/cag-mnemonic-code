package org.pjp.cag.error;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.exception.TranslationError;
import org.pjp.cag.exception.TranslationException;

public class TranslationExceptionTest {

    @Test
    public void testGetMessage() {
        TranslationException exception = new TranslationException(TranslationError.ERR_1);

        assertEquals(" 1", exception.getMessage());
    }

}
