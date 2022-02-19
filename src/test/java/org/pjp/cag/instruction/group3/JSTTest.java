package org.pjp.cag.instruction.group3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.pjp.cag.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;

public class JSTTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setControlAddress(12);

        JST instruction = new JST(false, ZERO, ZERO);
        instruction.execute(store);

        assertEquals(Store.ZERO, store.getControlAddress());
    }

    @Test
    public void testJumpOutOfRange() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();

            JST instruction = new JST(false, 1000, ZERO);
            instruction.execute(store);
        });

        assertEquals(RunningError.ERR_10.number(), Integer.parseInt(exception.getMessage().trim()));
    }

}
