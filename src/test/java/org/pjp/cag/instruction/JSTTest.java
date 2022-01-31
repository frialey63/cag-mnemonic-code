package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;

public class JSTTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setControlAddress(123);

        JST instruction = new JST(false);
        instruction.execute(store);

        assertEquals(Store.ZERO, store.getControlAddress());
    }

}
