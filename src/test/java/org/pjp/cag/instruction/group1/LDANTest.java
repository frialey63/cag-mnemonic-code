package org.pjp.cag.instruction.group1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pjp.cag.cpu.Store;
import org.pjp.cag.test.TestConstants;

public class LDANTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.accumulator().clear();

        LDAN instruction = new LDAN(false, 999);
        instruction.execute(store);

        assertEquals(999.0f, store.accumulator().get(), TestConstants.DELTA);
    }

}
