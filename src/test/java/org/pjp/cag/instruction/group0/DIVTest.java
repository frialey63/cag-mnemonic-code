package org.pjp.cag.instruction.group0;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.pjp.cag.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.Order;
import org.pjp.cag.Store;
import org.pjp.cag.Word;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;
import org.pjp.cag.test.TestConstants;

public class DIVTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.accumulator().set(123);
        store.setLocation(110, Word.create(456));
        store.setRegister(3, 10);

        DIV instruction = new DIV(false, 100, 3);
        instruction.execute(store);

        assertEquals((float) (123.0 / 456), store.accumulator().get(), TestConstants.DELTA);
    }

    @Test
    public void testExecuteNotNumber() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();
            store.setLocation(100, Word.create(Order.create(false, "LDA", "123", "3")));

            DIV instruction = new DIV(false, 100, ZERO);
            instruction.execute(store);
        });

        assertEquals(RunningError.ERR_15.number(), Integer.parseInt(exception.getMessage().trim()));
    }

}
