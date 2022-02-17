package org.pjp.cag.instruction.group3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;

public class JGRTest {

    @Test
    public void testExecuteGreater() {
        Store store = new Store();
        store.setAccumulator(1);
        store.setControlAddress(12);
        store.setRegister(3, 10);

        JGR instruction = new JGR(false, 100, 3);
        instruction.execute(store);

        assertEquals(110, store.getControlAddress());
    }

    @Test
    public void testExecuteEquals() {
        Store store = new Store();
        store.setAccumulator(0);
        store.setControlAddress(12);
        store.setRegister(3, 10);

        JGR instruction = new JGR(false, 100, 3);
        instruction.execute(store);

        assertEquals(12, store.getControlAddress());
    }

    @Test
    public void testExecuteLesser() {
        Store store = new Store();
        store.setAccumulator(-1);
        store.setControlAddress(12);
        store.setRegister(3, 10);

        JGR instruction = new JGR(false, 100, 3);
        instruction.execute(store);

        assertEquals(12, store.getControlAddress());
    }

}
