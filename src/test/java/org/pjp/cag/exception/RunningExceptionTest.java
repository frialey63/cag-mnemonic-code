package org.pjp.cag.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RunningExceptionTest {

    @Test
    public void testGetError() {
        RunningException exception = new RunningException(RunningError.ERR_11);

        assertEquals(RunningError.ERR_11, exception.getError());
    }

    @Test
    public void testGetMessage() {
        RunningException exception = new RunningException(RunningError.ERR_11);

        assertEquals("ERR 11 %4d Program obeying number not instruction.", exception.getMessage());
    }

}
