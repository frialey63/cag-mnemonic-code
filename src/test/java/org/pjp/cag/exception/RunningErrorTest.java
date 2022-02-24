package org.pjp.cag.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RunningErrorTest {

    @Test
    public void testNumber() {
        RunningError err = RunningError.ERR_11;

        assertEquals(11, err.number());
    }

    @Test
    public void testDescription() {
        RunningError err = RunningError.ERR_11;

        assertEquals("Program obeying number not instruction.", err.description());
    }

}
