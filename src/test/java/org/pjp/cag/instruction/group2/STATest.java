package org.pjp.cag.instruction.group2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.test.TestConstants;

public class STATest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(654);
        store.clearLocation(110);
        store.setRegister(3, 10);

        STA instruction = new STA(false, 100, 3);
        instruction.execute(store);

        assertEquals(654.0f, store.getLocation(110).number(), TestConstants.DELTA);
        assertEquals(654.0f, store.getAccumulator(), TestConstants.DELTA);
    }

}
