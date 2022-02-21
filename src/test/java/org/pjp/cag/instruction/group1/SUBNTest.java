package org.pjp.cag.instruction.group1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.test.TestConstants;

public class SUBNTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.accumulator().set(321);

        SUBN instruction = new SUBN(false, 999);
        instruction.execute(store);

        assertEquals((float) (321 - 999), store.accumulator().get(), TestConstants.DELTA);
    }

}
