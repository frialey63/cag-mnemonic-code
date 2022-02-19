package org.pjp.cag.error;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TranslationExceptionTest {

    @Test
    public void testGetMessage() {
        TranslationException exception = new TranslationException(TranslationError.ERR_1);

        assertEquals(" 1", exception.getMessage());
    }

}
