package org.pjp.cag.instruction.group3.rev;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.pjp.cag.cpu.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;

public class JLETest {

    @Test
    public void testExecuteGreater() {
        Store store = new Store();
        store.accumulator().set(1);
        store.controlRegister().setAddress(12);
        store.setRegister(3, 10);

        JLE instruction = new JLE(false, 100, 3);
        instruction.execute(store);

        assertEquals(12, store.controlRegister().getAddress());
    }

    @Test
    public void testExecuteEquals() {
        Store store = new Store();
        store.accumulator().set(0);
        store.controlRegister().setAddress(12);
        store.setRegister(3, 10);

        JLE instruction = new JLE(false, 100, 3);
        instruction.execute(store);

        assertEquals(110, store.controlRegister().getAddress());
    }

    @Test
    public void testExecuteLesser() {
        Store store = new Store();
        store.accumulator().set(-1);
        store.controlRegister().setAddress(12);
        store.setRegister(3, 10);

        JLE instruction = new JLE(false, 100, 3);
        instruction.execute(store);

        assertEquals(110, store.controlRegister().getAddress());
    }

    @Test
    public void testJumpOutOfRange() {
        Store store = new Store();

        RunningException exception = assertThrows(RunningException.class, () -> {
           store.accumulator().set(-1);
           store.controlRegister().setAddress(12);

            JLE instruction = new JLE(false, 1000, ZERO);
            instruction.execute(store);
        });

        assertEquals(RunningError.ERR_10, exception.getError());
        assertEquals(12, store.controlRegister().getAddress());
    }

}
