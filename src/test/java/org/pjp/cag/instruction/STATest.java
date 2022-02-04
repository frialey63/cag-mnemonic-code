package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;

public class STATest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(654);
        store.clearLocation(110);
        store.setRegister(3, 10);

        STA instruction = new STA(false, 100, 3);
        instruction.execute(store);

        assertEquals(654, store.getLocation(110).number(), TestConstants.PRECISION);
        assertEquals(654, store.getAccumulator(), TestConstants.PRECISION);
    }

}
