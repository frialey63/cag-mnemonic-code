package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;

public class LDANTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.clearAccumulator();

        LDAN instruction = new LDAN(false, 999);
        instruction.execute(store);

        assertEquals(999, store.getAccumulator(), TestConstants.PRECISION);
    }

}
