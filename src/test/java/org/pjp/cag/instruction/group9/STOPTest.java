package org.pjp.cag.instruction.group9;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pjp.cag.cpu.Store;

public class STOPTest {

    @Test
    public void testExecute() {
        Store store = new Store();

        STOP instruction = new STOP();
        instruction.execute(store);

        assertTrue(store.controlRegister().isStop());
    }

}
