package org.pjp.cag.instruction.group3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.pjp.cag.cpu.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;

public class JUNTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.controlRegister().setAddress(12);
        store.setRegister(3, 10);

        JUN instruction = new JUN(false, 100, 3);
        instruction.execute(store);

        assertEquals(110, store.controlRegister().getAddress());
    }

    @Test
    public void testJumpOutOfRange() {
        RunningException exception = assertThrows(RunningException.class, () -> {
            Store store = new Store();

            JUN instruction = new JUN(false, 1000, ZERO);
            instruction.execute(store);
        });

        assertEquals(RunningError.ERR_10, exception.getError());
    }

}
