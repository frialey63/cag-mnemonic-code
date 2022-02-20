package org.pjp.cag.instruction.group4;

import static org.junit.Assert.assertEquals;
import static org.pjp.cag.Store.ZERO;

import org.junit.Test;
import org.pjp.cag.Store;
import org.pjp.cag.test.TestConstants;

public class ENTTest {

    @Test
    public void testExecute() {
        Store store = new Store();
        store.setAccumulator(8.6f);

        ENT instruction = new ENT(false, ZERO, ZERO);
        instruction.execute(store);

        assertEquals(8, store.getAccumulator(), TestConstants.DELTA);
    }

    @Test
    public void testExecuteNegative() {
        Store store = new Store();
        store.setAccumulator(-8.6f);

        ENT instruction = new ENT(false, ZERO, ZERO);
        instruction.execute(store);

        assertEquals(-9, store.getAccumulator(), TestConstants.DELTA);
    }

}
