package org.pjp.cag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;

public class InterpreterTest {

    @Test
    public void testGetOrderWasNumber() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();

            store.setLocation(100, Word.create(666));

            Interpreter.getOrder(store, 100);
        });

        assertEquals(RunningError.ERR_11.number(), Integer.parseInt(exception.getMessage().trim()));
    }

    @Test
    public void testGetOrderWasCharacter() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();

            store.setLocation(100, Word.create('A'));

            Interpreter.getOrder(store, 100);
        });

        assertEquals(RunningError.ERR_11.number(), Integer.parseInt(exception.getMessage().trim()));
    }

}
