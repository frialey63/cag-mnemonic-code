package org.pjp.cag.instruction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.TestConstants;

public class ADDNTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(321);

        ADDN instruction = new ADDN(false, 999);
        instruction.execute(store);

        assertEquals(321 + 999, store.getAccumulator(), TestConstants.PRECISION);
    }

}