package org.pjp.cag.error;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.exception.TranslationError;

public class TranslationErrorTest {

    @Test
    public void testNumber() {
        TranslationError err = TranslationError.ERR_1;

        assertEquals(1, err.number());
    }

}
