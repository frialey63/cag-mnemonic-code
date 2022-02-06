package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;

public class JUNTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setControlAddress(12);
        store.setRegister(3, 10);

        JUN instruction = new JUN(false, 100, 3);
        instruction.execute(store);

        assertEquals(110, store.getControlAddress());
    }

}
