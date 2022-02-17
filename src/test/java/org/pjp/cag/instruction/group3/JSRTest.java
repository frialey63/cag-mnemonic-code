package org.pjp.cag.instruction.group3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;

public class JSRTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setControlAddress(12);
        store.setRegister(3, 10);

        JSR instruction = new JSR(false, 100, 3);
        instruction.execute(store);

        assertEquals(13, (int) store.getRegister(Store.LINK));
        assertEquals(110, store.getControlAddress());
    }

}
