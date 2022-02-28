package org.pjp.cag.instruction.group3.rev;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.pjp.cag.cpu.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.exception.RunningError;
import org.pjp.cag.exception.RunningException;

public class LOPTest {

    @Test
    public void testLoop() {
        Store store = new Store();
        store.controlRegister().setAddress(12);
        store.setRegister(Store.LOOP, 10);
        store.setRegister(3, 10);

        LOP instruction = new LOP(false, 100, 3);
        instruction.execute(store);

        assertEquals(110, store.controlRegister().getAddress());
        assertEquals(9, (int) store.getRegister(Store.LOOP));
    }

    @Test
    public void testLoopNo() {
        Store store = new Store();
        store.controlRegister().setAddress(12);
        store.setRegister(Store.LOOP, 1);
        store.setRegister(3, 10);

        LOP instruction = new LOP(false, 100, 3);
        instruction.execute(store);

        assertEquals(12, store.controlRegister().getAddress());
        assertEquals(0, (int) store.getRegister(Store.LOOP));
    }

    @Test
    public void testJumpOutOfRange() {
        Store store = new Store();

        RunningException exception = assertThrows(RunningException.class, () -> {
            store.controlRegister().setAddress(12);
            store.setRegister(Store.LOOP, 10);

            LOP instruction = new LOP(false, 1000, ZERO);
            instruction.execute(store);
        });

        assertEquals(RunningError.ERR_10, exception.getError());
        assertEquals(12, store.controlRegister().getAddress());
    }

}
