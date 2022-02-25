package org.pjp.cag.order;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.exception.TranslationException;

public class WaitDirectiveTest {

    @Test
    public void testType() throws TranslationException {
        WaitDirective waitDirective = new WaitDirective();

        assertEquals(WaitDirective.WAIT, waitDirective.type());
    }

}
