package org.pjp.cag.instruction.group3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;

public class JSTTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setControlAddress(12);

        JST instruction = new JST(false, 0);
        instruction.execute(store);

        assertEquals(Store.ZERO, store.getControlAddress());
    }

}
