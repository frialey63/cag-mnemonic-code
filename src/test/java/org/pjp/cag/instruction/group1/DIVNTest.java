package org.pjp.cag.instruction.group1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.test.TestConstants;

public class DIVNTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.accumulator().set(321);

        DIVN instruction = new DIVN(false, 999);
        instruction.execute(store);

        assertEquals((float) (321.0 / 999), store.accumulator().get(), TestConstants.DELTA);
    }

}
