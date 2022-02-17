package org.pjp.cag.instruction.group3;

import static org.junit.Assert.assertEquals;
import static org.pjp.cag.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.Store;

public class JSTTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setControlAddress(12);

        JST instruction = new JST(false, ZERO, ZERO);
        instruction.execute(store);

        assertEquals(Store.ZERO, store.getControlAddress());
    }

}
