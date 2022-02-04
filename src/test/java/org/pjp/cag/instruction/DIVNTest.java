package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;

public class DIVNTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(321);

        DIVN instruction = new DIVN(false, 999);
        instruction.execute(store);

        assertEquals(321.0 / 999, store.getAccumulator(), TestConstants.PRECISION);
    }

}
