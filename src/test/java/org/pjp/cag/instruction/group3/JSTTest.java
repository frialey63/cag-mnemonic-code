package org.pjp.cag.instruction.group3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.pjp.cag.cpu.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;

public class JSTTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.controlRegister().setAddress(12);
        store.setRegister(3, 10);

        JST instruction = new JST(false, 100, 3);
        instruction.execute(store);

        assertTrue(store.controlRegister().isWait());
        assertEquals(110, store.controlRegister().getAddress());
    }

    @Test
    public void testExecuteZero() {
        Store store = new Store();
        store.controlRegister().setAddress(12);

        JST instruction = new JST(false, ZERO, ZERO);
        instruction.execute(store);

        assertTrue(store.controlRegister().isWait());
        assertEquals(ZERO, store.controlRegister().getAddress());
    }

    @Test
    public void testJumpOutOfRange() {
        Store store = new Store();

        RunningException exception = assertThrows(RunningException.class, () -> {
            store.controlRegister().setAddress(12);

            JST instruction = new JST(false, 1000, ZERO);
            instruction.execute(store);
        });

        assertEquals(RunningError.ERR_10, exception.getError());
        assertEquals(12, store.controlRegister().getAddress());
    }

}
